package DoCo.ServerA.domain.profile.application

import DoCo.ServerA.domain.profile.data.dto.req.ProfilePostReq
import DoCo.ServerA.domain.profile.data.dto.res.ProfileGetRes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication

interface ProfileService {
    fun post(profilePostReq: ProfilePostReq, authentication: Authentication): ResponseEntity<HttpStatus>
    fun get(id: Long): ResponseEntity<ProfileGetRes>
}