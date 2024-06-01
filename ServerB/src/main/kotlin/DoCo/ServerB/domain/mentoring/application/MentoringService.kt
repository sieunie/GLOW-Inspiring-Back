package DoCo.ServerB.domain.mentoring.application

import DoCo.ServerB.domain.mentoring.dto.req.MentoringPostReq
import DoCo.ServerB.domain.mentoring.dto.req.MentoringPutReq
import DoCo.ServerB.domain.mentoring.dto.res.MentoringGetElementRes
import DoCo.ServerB.domain.mentoring.dto.res.MentoringGetRes
import DoCo.ServerB.domain.mentoring.dto.res.MentoringRequestGetElementRes
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication

interface MentoringService {
    fun post(mentoringPostReq: MentoringPostReq, authentication: Authentication): ResponseEntity<HttpStatus>
    fun get(id: Int): ResponseEntity<MentoringGetRes>
    fun getList(pageNumber: Int, pageSize: Int): ResponseEntity<Page<MentoringGetElementRes>>
    fun put(mentoringPutReq: MentoringPutReq, authentication: Authentication): ResponseEntity<HttpStatus>
    fun delete(id: Int, authentication: Authentication): ResponseEntity<HttpStatus>
    fun postRequest(id: Int, authentication: Authentication): ResponseEntity<HttpStatus>
    fun getRequest(id: Int, authentication: Authentication): ResponseEntity<List<MentoringRequestGetElementRes>>
    fun patchRequest(
        mentoringId: Int,
        mentoringRequestId: Int,
        authentication: Authentication
    ): ResponseEntity<HttpStatus>
}