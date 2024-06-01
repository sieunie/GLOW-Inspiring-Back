package DoCo.ServerB.domain.image.application

import DoCo.ServerB.domain.image.dto.res.ImagePostElementRes
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile

interface ImageService {
    fun post(multipartFileList: List<MultipartFile>): ResponseEntity<List<ImagePostElementRes>>
}