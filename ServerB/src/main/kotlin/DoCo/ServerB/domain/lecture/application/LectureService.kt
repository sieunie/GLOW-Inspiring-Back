package DoCo.ServerB.domain.lecture.application

import DoCo.ServerB.domain.lecture.data.dto.req.LecturePostReq
import DoCo.ServerB.domain.lecture.data.dto.req.LecturePutReq
import DoCo.ServerB.domain.lecture.data.dto.res.LectureGetElementRes
import DoCo.ServerB.domain.lecture.data.dto.res.LectureGetRes
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication

interface LectureService {
    fun post(lecturePostReq: LecturePostReq, authentication: Authentication): ResponseEntity<HttpStatus>
    fun get(id: Int): ResponseEntity<LectureGetRes>
    fun getList(pageNumber: Int, pageSize: Int): ResponseEntity<Page<LectureGetElementRes>>
    fun put(lecturePutReq: LecturePutReq, authentication: Authentication): ResponseEntity<HttpStatus>
    fun delete(id: Int, authentication: Authentication): ResponseEntity<HttpStatus>
}