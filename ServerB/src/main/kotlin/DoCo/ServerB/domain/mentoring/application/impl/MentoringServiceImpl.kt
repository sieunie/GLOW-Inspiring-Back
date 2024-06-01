package DoCo.ServerB.domain.mentoring.application.impl

import DoCo.ServerB.domain.mentoring.application.MentoringService
import DoCo.ServerB.domain.mentoring.dto.req.MentoringPostReq
import DoCo.ServerB.domain.mentoring.dto.res.MentoringGetRes
import DoCo.ServerB.global.data.entity.Mentoring
import DoCo.ServerB.global.data.entity.User
import DoCo.ServerB.global.repository.ImageRepository
import DoCo.ServerB.global.repository.MentoringRepository
import DoCo.ServerB.global.repository.MentoringRequestRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.lang.NullPointerException
import java.util.concurrent.CompletableFuture

@Service
class MentoringServiceImpl(
    private val mentoringRepository: MentoringRepository,
    private val mentoringRequestRepository: MentoringRequestRepository,
    private val imageRepository: ImageRepository
): MentoringService {
    override fun post(mentoringPostReq: MentoringPostReq, authentication: Authentication): ResponseEntity<HttpStatus> {
        val mentoring = Mentoring(
            id = null,
            user = User(authentication.name.toLong()),
            title = mentoringPostReq.title,
            startTime = mentoringPostReq.startTime,
            endTime = mentoringPostReq.endTime,
            latitude = mentoringPostReq.latitude,
            longitude = mentoringPostReq.longitude,
            address = mentoringPostReq.address,
            description = mentoringPostReq.description,
            topic = mentoringPostReq.topic,
            category = mentoringPostReq.category
        )

        mentoringRepository.save(mentoring)

        CompletableFuture.runAsync {
            mentoringPostReq.imageIdList.map {
                imageId ->
                    val image = imageRepository.findById(imageId).orElseThrow { NullPointerException() }
                    image.mentoring = mentoring
                    imageRepository.save(image)
            }
        }

        return ResponseEntity.ok().build()
    }

    override fun get(id: Int): ResponseEntity<MentoringGetRes> {
        return try{
            val mentoring = mentoringRepository.findById(id).orElseThrow { NullPointerException() }
            ResponseEntity.ok(

            ).build()
        } catch(nullPointerException: NullPointerException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}