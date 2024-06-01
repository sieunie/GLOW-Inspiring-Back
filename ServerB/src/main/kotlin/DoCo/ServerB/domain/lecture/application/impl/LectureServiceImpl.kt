package DoCo.ServerB.domain.lecture.application.impl

import DoCo.ServerB.domain.lecture.application.LectureService
import DoCo.ServerB.domain.lecture.data.dto.req.LecturePostReq
import DoCo.ServerB.domain.lecture.data.dto.req.LecturePutReq
import DoCo.ServerB.domain.lecture.data.dto.res.LectureGetElementRes
import DoCo.ServerB.domain.lecture.data.dto.res.LectureGetRes
import DoCo.ServerB.global.data.entity.Lecture
import DoCo.ServerB.global.data.entity.User
import DoCo.ServerB.global.repository.ImageRepository
import DoCo.ServerB.global.repository.LectureRepository
import DoCo.ServerB.global.repository.LectureRequestRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.lang.NullPointerException
import java.util.concurrent.CompletableFuture

@Service
class LectureServiceImpl(
    private val lectureRepository: LectureRepository,
    private val lectureRequestRepository: LectureRequestRepository,
    private val imageRepository: ImageRepository
): LectureService {

    override fun post(lecturePostReq: LecturePostReq, authentication: Authentication): ResponseEntity<HttpStatus> {
        val lecture = Lecture(
            id = null,
            user = User(authentication.name.toLong()),
            title = lecturePostReq.title,
            startTime = lecturePostReq.startTime,
            endTime = lecturePostReq.endTime,
            latitude = lecturePostReq.latitude,
            longitude = lecturePostReq.longitude,
            address = lecturePostReq.address,
            description = lecturePostReq.description,
            topic = lecturePostReq.topic,
            category = lecturePostReq.category
        )

        lectureRepository.save(lecture)

        CompletableFuture.runAsync {
            lecturePostReq.imageIdList.map {
                    imageId ->
                val image = imageRepository.findById(imageId).orElseThrow { NullPointerException() }
                image.lecture = lecture
                imageRepository.save(image)
            }
        }
        return ResponseEntity.ok().build()
    }

    override fun get(id: Int): ResponseEntity<LectureGetRes> {
        return try{
            val lecture = lectureRepository.findById(id).orElseThrow { NullPointerException() }
            ResponseEntity.ok(
                LectureGetRes(lecture, imageRepository.findByLecture(lecture))
            )
        } catch(nullPointerException: NullPointerException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    override fun getList(pageNumber: Int, pageSize: Int): ResponseEntity<Page<LectureGetElementRes>> {
        return ResponseEntity.ok(lectureRepository.findAll(PageRequest.of(pageNumber, pageSize)).map{
                lecture -> LectureGetElementRes(lecture, imageRepository.findByLectureOrderByIdDesc(lecture, PageRequest.of(0, 1)))
        })
    }

    override fun put(lecturePutReq: LecturePutReq, authentication: Authentication): ResponseEntity<HttpStatus> {
        val user = User(authentication.name.toLong())
        try{
            val lecture = lectureRepository.findByIdAndUser(lecturePutReq.id, user).orElseThrow { NullPointerException() }

            CompletableFuture.supplyAsync {
                println(lectureRepository.save(lecture.put(lecturePutReq)))
            }.thenApplyAsync {
                lecturePutReq.imageIdList.map{
                        imageId ->
                    val image = imageRepository.findById(imageId).orElseThrow()
                    image.lecture = lecture
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
        return if(lectureRepository.deleteByIdAndUser(id, User(authentication.name.toLong())) > 0)
            ResponseEntity.ok().build()
        else
            ResponseEntity(HttpStatus.NOT_FOUND)
    }
}