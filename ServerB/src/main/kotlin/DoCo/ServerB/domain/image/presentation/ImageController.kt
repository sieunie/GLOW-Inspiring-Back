package DoCo.ServerB.domain.image.presentation

import DoCo.ServerB.domain.image.application.ImageService
import DoCo.ServerB.domain.image.dto.res.ImagePostElementRes
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/image")
@Tag(name = "이미지")
class ImageController(
    private val imageService: ImageService) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(summary = "이미지 업로드 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf()),
        ApiResponse(responseCode = "502", description = "이미지 업로드 중 에러 발생")
    )
    fun post(@RequestPart multipartFileList: List<MultipartFile>, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<List<ImagePostElementRes>>{
        return imageService.post(multipartFileList, authentication)
    }

    @DeleteMapping
    @Operation(summary = "이미지 삭제 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "404", description = "권한 없음")
    )
    fun delete(@RequestParam imageIdList: List<Int>, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus>{
        return imageService.delete(imageIdList, authentication)
    }
}