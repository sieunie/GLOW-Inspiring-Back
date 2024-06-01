package DoCo.ServerB.domain.image.application

import DoCo.ServerB.domain.image.dto.res.ImagePostElementRes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.multipart.MultipartFile

interface ImageService {

    fun delete(imageIdList: List<Int>, authentication: Authentication): ResponseEntity<HttpStatus>
    fun post(
        multipartFileList: List<MultipartFile>,
        authentication: Authentication
    ): ResponseEntity<List<ImagePostElementRes>>
}