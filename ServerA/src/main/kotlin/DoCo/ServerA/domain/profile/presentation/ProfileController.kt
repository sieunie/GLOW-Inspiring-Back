package DoCo.ServerA.domain.profile.presentation

import DoCo.ServerA.domain.profile.application.ProfileService
import DoCo.ServerA.domain.profile.data.dto.req.ProfilePostReq
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/profile")
@Tag(name = "프로필")
class ProfileController(
    private val profileService: ProfileService
) {

    @PostMapping
    @Operation(summary = "프로필 게시")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content()))
    )
    fun post(@RequestBody profilePostReq: ProfilePostReq, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus> {
        return profileService.post(profilePostReq, authentication);
    }
}