package DoCo.ServerA.domain.auth.application

import DoCo.ServerA.domain.auth.dto.AuthGetLoginRes
import org.springframework.http.ResponseEntity

interface AuthService {
    fun getLogin(code: String): ResponseEntity<AuthGetLoginRes>
}