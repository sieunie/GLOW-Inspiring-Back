package DoCo.ServerB.domain.mentoring.presentation

import DoCo.ServerB.domain.mentoring.application.MentoringService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/mentoring")
@Tag(name = "과외")
class MentoringController(
    private val mentoringService: MentoringService
) {
}