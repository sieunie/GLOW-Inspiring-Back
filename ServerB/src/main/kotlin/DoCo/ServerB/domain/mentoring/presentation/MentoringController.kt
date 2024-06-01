package DoCo.ServerB.domain.mentoring.presentation

import DoCo.ServerB.domain.mentoring.application.MentoringService
import DoCo.ServerB.domain.mentoring.dto.req.MentoringPostReq
import DoCo.ServerB.domain.mentoring.dto.res.MentoringGetElementRes
import DoCo.ServerB.domain.mentoring.dto.res.MentoringGetRes
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/mentoring")
@Tag(name = "과외")
class MentoringController(
    private val mentoringService: MentoringService
) {

    @PostMapping
    @Operation(summary = "과외 업로드 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공")
    )
    fun post(@RequestBody mentoringPostReq: MentoringPostReq, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus>{
        return mentoringService.post(mentoringPostReq, authentication)
    }

    @GetMapping
    @Operation(summary = "과외 조회 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "404", description = "찾지 못함")
    )
    fun get(@RequestParam id: Int): ResponseEntity<MentoringGetRes>{
        return mentoringService.get(id)
    }

    @GetMapping("/list")
    @Operation(summary = "과외 리스트 조회 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content(schema = Schema(implementation = MentoringGetElementRes::class))))
    )
    fun getList(@RequestParam pageSize: Int, @RequestParam pageNumber: Int): ResponseEntity<Page<MentoringGetElementRes>>{
        return mentoringService.getList(pageNumber, pageSize)
    }
}