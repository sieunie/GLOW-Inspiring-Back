package DoCo.ServerA.domain.auth.application

import DoCo.ServerA.domain.auth.dto.AuthLoginRes
import org.springframework.http.ResponseEntity

interface AuthService {
    fun getLogin(code: String): ResponseEntity<AuthLoginRes>
    fun patchLogin(refreshToken: String): ResponseEntity<AuthLoginRes>
}