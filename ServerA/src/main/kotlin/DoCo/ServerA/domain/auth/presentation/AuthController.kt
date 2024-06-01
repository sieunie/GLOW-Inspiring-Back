package DoCo.ServerA.domain.auth.presentation

import DoCo.ServerA.domain.auth.application.AuthService
import DoCo.ServerA.domain.auth.dto.AuthGetLoginRes
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@Tag(name = "인증")
class AuthController(
    private val authService: AuthService) {

    @GetMapping("/login")
    @ApiResponses(
        ApiResponse()
    )
    fun getLogin(@RequestParam code: String): ResponseEntity<AuthGetLoginRes>{
        return authService.getLogin(code)
    }

}