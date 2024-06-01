package DoCo.ServerB.domain.mentoring.presentation

import DoCo.ServerB.domain.mentoring.application.MentoringService
import DoCo.ServerB.domain.mentoring.dto.req.MentoringPostReq
import DoCo.ServerB.domain.mentoring.dto.req.MentoringPutReq
import DoCo.ServerB.domain.mentoring.dto.res.MentoringGetElementRes
import DoCo.ServerB.domain.mentoring.dto.res.MentoringGetRes
import DoCo.ServerB.domain.mentoring.dto.res.MentoringRequestGetElementRes
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
import org.springframework.web.bind.annotation.*

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

    @PutMapping
    @Operation(summary = "과외 수정 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf()),
        ApiResponse(responseCode = "404", description = "해당 데이터를 찾을 수 없음")
    )
    fun put(@RequestBody mentoringPutReq: MentoringPutReq, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus>{
        return mentoringService.put(mentoringPutReq, authentication)
    }

    @DeleteMapping
    @Operation(summary = "과외 삭제 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공")
    )
    fun delete(@RequestParam id: Int, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus>{
        return mentoringService.delete(id, authentication)
    }

    @PostMapping("/request")
    @Operation(summary = "과외 요청 API")
    @ApiResponses(
        ApiResponse(responseCode = "200")
    )
    fun postRequest(@RequestParam id: Int, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus>{
        return mentoringService.postRequest(id, authentication)
    }

    @GetMapping("/request")
    @Operation(summary = "과외 조회 API")
    @ApiResponses(
        ApiResponse(responseCode = "200")
    )
    fun getRequest(@RequestParam id: Int, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<List<MentoringRequestGetElementRes>>{
        return mentoringService.getRequest(id, authentication)
    }

    @PatchMapping("/request")
    @Operation(summary = "과외 요청 수락 API")
    @ApiResponses(

    )
    fun patchRequest(@RequestParam mentoringId: Int, mentoringRequestId: Int, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus>{
        return mentoringService.patchRequest(mentoringId, mentoringRequestId, authentication)
    }
}