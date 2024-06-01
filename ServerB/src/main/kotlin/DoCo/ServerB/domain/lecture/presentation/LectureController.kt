package DoCo.ServerB.domain.lecture.presentation

import DoCo.ServerB.domain.lecture.application.LectureService
import DoCo.ServerB.domain.lecture.data.dto.req.LecturePostReq
import DoCo.ServerB.domain.lecture.data.dto.req.LecturePutReq
import DoCo.ServerB.domain.lecture.data.dto.res.LectureGetElementRes
import DoCo.ServerB.domain.lecture.data.dto.res.LectureGetRes
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
@RequestMapping("/api/lecture")
@Tag(name = "강연")
class LectureController(
    private val lectureService: LectureService
) {

    @PostMapping
    @Operation(summary = "강연 업로드 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공")
    )
    fun post(@RequestBody lecturePostReq: LecturePostReq, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus> {
        return lectureService.post(lecturePostReq, authentication)
    }

    @GetMapping
    @Operation(summary = "강연 조회 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "404", description = "찾지 못함")
    )
    fun get(@RequestParam id: Int): ResponseEntity<LectureGetRes> {
        return lectureService.get(id)
    }

    @GetMapping("/list")
    @Operation(summary = "강연 리스트 조회 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content(schema = Schema(implementation = LectureGetElementRes::class))))
    )
    fun getList(@RequestParam pageSize: Int, @RequestParam pageNumber: Int): ResponseEntity<Page<LectureGetElementRes>> {
        return lectureService.getList(pageNumber, pageSize)
    }

    @PutMapping
    @Operation(summary = "강연 수정 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf()),
        ApiResponse(responseCode = "404", description = "해당 데이터를 찾을 수 없음")
    )
    fun put(@RequestBody lecturePutReq: LecturePutReq, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus> {
        return lectureService.put(lecturePutReq, authentication)
    }

    @DeleteMapping
    @Operation(summary = "강연 삭제 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공")
    )
    fun delete(@RequestParam id: Int, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<HttpStatus> {
        return lectureService.delete(id, authentication)
    }
}