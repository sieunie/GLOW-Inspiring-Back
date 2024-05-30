package DoCo.ServerB.domain.image.application.impl

import DoCo.ServerB.domain.image.application.ImageService
import DoCo.ServerB.global.repository.ImageRepository
import org.springframework.stereotype.Service

@Service
class ImageServiceImpl(
    private val imageRepository: ImageRepository
): ImageService {
}