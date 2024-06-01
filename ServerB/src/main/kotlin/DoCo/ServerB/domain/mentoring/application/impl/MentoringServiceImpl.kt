package DoCo.ServerB.domain.mentoring.application.impl

import DoCo.ServerB.domain.mentoring.application.MentoringService
import DoCo.ServerB.domain.mentoring.dto.req.MentoringPostReq
import DoCo.ServerB.domain.mentoring.dto.req.MentoringPutReq
import DoCo.ServerB.domain.mentoring.dto.res.MentoringGetElementRes
import DoCo.ServerB.domain.mentoring.dto.res.MentoringGetRes
import DoCo.ServerB.global.data.entity.Mentoring
import DoCo.ServerB.global.data.entity.User
import DoCo.ServerB.global.repository.ImageRepository
import DoCo.ServerB.global.repository.MentoringRepository
import DoCo.ServerB.global.repository.MentoringRequestRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
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
                MentoringGetRes(mentoring, imageRepository.findByMentoring(mentoring))
            )
        } catch(nullPointerException: NullPointerException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    override fun put(mentoringPutReq: MentoringPutReq, authentication: Authentication): ResponseEntity<HttpStatus> {
        val user = User(authentication.name.toLong())
        try{
            val mentoring = mentoringRepository.findByIdAndUser(mentoringPutReq.id, user).orElseThrow { NullPointerException() }

            CompletableFuture.supplyAsync {
                println(mentoringRepository.save(mentoring.put(mentoringPutReq)))
            }.thenApplyAsync {
                mentoringPutReq.imageIdList.map{
                    imageId ->
                        val image = imageRepository.findById(imageId).orElseThrow()
                        image.mentoring = mentoring
                        imageRepository.save(image)
                }
            }

            return ResponseEntity.ok().build()
        }
        catch(nullPointerException: NullPointerException){
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    override fun delete(id: Int, authentication: Authentication): ResponseEntity<HttpStatus> {
        return if(mentoringRepository.deleteByIdAndUser(id, User(authentication.name.toLong())) > 0)
            ResponseEntity.ok().build()
        else
            ResponseEntity(HttpStatus.NOT_FOUND)
    }

    override fun getList(pageNumber: Int, pageSize: Int): ResponseEntity<Page<MentoringGetElementRes>> {
        return ResponseEntity.ok(mentoringRepository.findAll(PageRequest.of(pageNumber, pageSize)).map{
                mentoring -> MentoringGetElementRes(mentoring, imageRepository.findByMentoringOrderByIdDesc(mentoring, PageRequest.of(0, 1)))
        })
    }


}