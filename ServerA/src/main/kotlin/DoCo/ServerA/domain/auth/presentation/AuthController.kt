package DoCo.ServerA.domain.auth.presentation

import DoCo.ServerA.domain.auth.application.AuthService
import DoCo.ServerA.domain.auth.dto.AuthLoginRes
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestHeader
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
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content(schema = Schema(implementation = AuthLoginRes::class)))),
        ApiResponse(responseCode = "404", description = "카카오에서 해당 사용자를 찾을 수 없음"),
        ApiResponse(responseCode = "502", description = "카카오 인증서버 연결 실패")
    )
    fun getLogin(@RequestParam code: String): ResponseEntity<AuthLoginRes>{
        return authService.getLogin(code)
    }

    @PatchMapping("/login")
    @Operation(summary = "accessToken 재발급")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content(schema = Schema(implementation = AuthLoginRes::class)))),
        ApiResponse(responseCode = "401", description = "토큰 없음"),
        ApiResponse(responseCode = "404", description = "토큰으로 사용자를 찾을 수 없음")
    )
    fun patchLogin(@Parameter(hidden = true) @RequestHeader(HttpHeaders.AUTHORIZATION) refreshToken: String): ResponseEntity<AuthLoginRes>{
        return authService.patchLogin(refreshToken)
    }

}