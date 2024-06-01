package DoCo.ServerB.domain.image.application.impl

import DoCo.ServerB.domain.image.application.ImageService
import DoCo.ServerB.domain.image.dto.res.ImagePostElementRes
import DoCo.ServerB.global.data.entity.Image
import DoCo.ServerB.global.repository.ImageRepository
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.lang.NullPointerException
import java.util.*
import java.util.concurrent.CompletableFuture

@Service
class ImageServiceImpl(
    private val imageRepository: ImageRepository,
    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String,
    @Value("\${cloud.aws.s3.path}")
    private val path: String,
    private val amazonS3Client: AmazonS3Client
): ImageService {

    private final val s3Prefix = "GLOW/"
    override fun post(multipartFileList: List<MultipartFile>, authentication: Authentication): ResponseEntity<List<ImagePostElementRes>> {
        return try{
            val imagePostElementResList = multipartFileList.map { multipartFile ->
                val image = imageRepository.save(Image(path + uploadToS3(multipartFile), authentication.name.toLong()))
                ImagePostElementRes(image.id ?: throw NullPointerException())
            }
            ResponseEntity.ok(imagePostElementResList)
        } catch(nullPointerException: NullPointerException){
            ResponseEntity(HttpStatus.BAD_GATEWAY)
        }
    }

    override fun delete(imageIdList: List<Int>, authentication: Authentication): ResponseEntity<HttpStatus> {
        imageIdList.map {
            imageId ->
                if(!imageRepository.existsByIdAndUserId(imageId, authentication.name.toLong()))
                    return ResponseEntity(HttpStatus.FORBIDDEN)
        }

        CompletableFuture.runAsync {
            imageRepository.deleteAllById(
                imageIdList.map {
                        imageId -> imageId
                }
            )
        }

        return ResponseEntity.ok().build()
    }

    private fun uploadToS3(multipartFile: MultipartFile): String{
        val fileName = s3Prefix + UUID.randomUUID() + "." + StringUtils.getFilenameExtension(multipartFile.originalFilename)
        val metadata = ObjectMetadata()
        metadata.contentType = multipartFile.contentType
        metadata.contentLength = multipartFile.size
        amazonS3Client.putObject(bucket, fileName, multipartFile.inputStream, metadata)
        return fileName
    }
}