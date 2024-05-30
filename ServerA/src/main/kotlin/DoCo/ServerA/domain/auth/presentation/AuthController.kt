package DoCo.ServerA.domain.auth.presentation

import DoCo.ServerA.domain.auth.application.AuthService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@Tag(name = "인증")
class AuthController(
    private val authService: AuthService
) {
}