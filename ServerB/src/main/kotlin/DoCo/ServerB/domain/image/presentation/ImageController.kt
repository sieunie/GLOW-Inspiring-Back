package DoCo.ServerB.domain.image.presentation

import DoCo.ServerB.domain.image.application.ImageService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/image")
@Tag(name = "이미지")
class ImageController(
    private val imageService: ImageService
) {
}