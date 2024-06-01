package DoCo.ServerB.domain.mentoring.application

import DoCo.ServerB.domain.mentoring.dto.req.MentoringPostReq
import DoCo.ServerB.domain.mentoring.dto.res.MentoringGetRes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication

interface MentoringService {
    fun post(mentoringPostReq: MentoringPostReq, authentication: Authentication): ResponseEntity<HttpStatus>
    fun get(id: Int): ResponseEntity<MentoringGetRes>
}