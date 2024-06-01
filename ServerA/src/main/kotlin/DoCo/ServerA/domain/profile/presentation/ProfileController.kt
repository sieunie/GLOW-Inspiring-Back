package DoCo.ServerA.domain.profile.presentation

import DoCo.ServerA.domain.profile.application.ProfileService
import DoCo.ServerA.domain.profile.data.dto.req.ProfilePostReq
import DoCo.ServerA.domain.profile.data.dto.res.ProfileGetRes
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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
        return profileService.post(profilePostReq, authentication)
    }

    @GetMapping
    @Operation(summary = "프로필 조회")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content())),
        ApiResponse(responseCode = "400", description = "프로필 없음")
    )
    fun get(@RequestParam id: Long): ResponseEntity<ProfileGetRes> {
        return profileService.get(id)
    }

    @GetMapping("/my")
    @Operation(summary = "본인 프로필 조회")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content())),
        ApiResponse(responseCode = "400", description = "프로필 없음")
    )
    fun get(@Parameter(hidden = true) authentication: Authentication): ResponseEntity<ProfileGetRes>{
        return profileService.get(authentication)
    }

    @PutMapping
    @Operation(summary = "프로필 수정")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공")
    )
    fun put(@RequestBody profilePostReq: ProfilePostReq, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus>{
        return profileService.put(profilePostReq, authentication)
    }

    @DeleteMapping
    @Operation(summary = "프로필 삭제")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공")
    )
    fun delete(@Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus>{
        return profileService.delete(authentication)
    }
}