package DoCo.ServerB.domain.lecture.presentation

import DoCo.ServerB.domain.lecture.application.LectureService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lecture")
@Tag(name = "강연")
class LectureController(
    private val lectureService: LectureService
) {
}