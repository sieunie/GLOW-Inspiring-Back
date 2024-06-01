package DoCo.ServerA.domain.profile.application.impl

import DoCo.ServerA.domain.profile.application.ProfileService
import DoCo.ServerA.domain.profile.data.dto.req.ProfilePostReq
import DoCo.ServerA.domain.profile.data.dto.res.ProfileGetRes
import DoCo.ServerA.global.ImageRepository
import DoCo.ServerA.global.data.entity.*
import DoCo.ServerA.global.repository.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.NoSuchElementException
import java.util.concurrent.CompletableFuture

@Service
class ProfileServiceImpl(
    private val profileRepository: ProfileRepository,
    private val prizeRepository: PrizeRepository,
    private val careerRepository: CareerRepository,
    private val userRepository: UserRepository,
    private val educationRepository: EducationRepository,
    private val imageRepository: ImageRepository
): ProfileService {

    override fun post(profilePostReq: ProfilePostReq, authentication: Authentication): ResponseEntity<HttpStatus> {
        val user = try {
            userRepository.findById(authentication.name.toLong())
                .orElseThrow{throw NoSuchElementException("일치하는 사용자가 없습니다.") }
        } catch (noSuchElementException: NoSuchElementException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val profile = profileRepository.save(Profile(
            userId = user.id,
            description = profilePostReq.description,
            imagePath = null
        ))

        CompletableFuture.runAsync {
            profilePostReq.prizeList.map {
                    prize ->
                prizeRepository.save(Prize(
                    id = null,
                    user = user,
                    content = prize
                ))
            }
        }

        CompletableFuture.runAsync {
            profilePostReq.careerList.map {
                    career ->
                careerRepository.save(Career(
                    id = null,
                    user = user,
                    content = career
                ))
            }
        }

        CompletableFuture.runAsync {
            profilePostReq.educationList.map{
                education ->
                educationRepository.save(Education(id = null, user = user, content = education))
            }
        }

        CompletableFuture.runAsync {
            val image = imageRepository.findById(profilePostReq.imageId ?: 0).orElseThrow()
            image.profileId = profile.userId
            imageRepository.save(image)
        }

        return ResponseEntity(HttpStatus.OK)
    }

    override fun put(profilePostReq: ProfilePostReq, authentication: Authentication): ResponseEntity<HttpStatus> {
        val userId = authentication.name.toLong()
        val user = try {
            profileRepository.deleteByUserId(userId)
            val user = User(authentication.name.toLong())
            prizeRepository.deleteByUser(user)
            careerRepository.deleteByUser(user)
            educationRepository.deleteByUser(user)
            user
        } catch (noSuchElementException: NoSuchElementException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        CompletableFuture.supplyAsync {
            profileRepository.save(Profile(
                userId = userId,
                description = profilePostReq.description,
                imagePath = null
            ))
        }.thenRunAsync {
            profilePostReq.prizeList.map {
                    prize ->
                prizeRepository.save(Prize(
                    id = null,
                    user = user,
                    content = prize
                ))
            }
        }.thenRunAsync {
            profilePostReq.careerList.map {
                    career ->
                careerRepository.save(Career(
                    id = null,
                    user = user,
                    content = career
                ))
            }
        }.thenRunAsync {
            profilePostReq.educationList.map {
                education -> educationRepository.save(Education(id = null, user = user, content = education))
            }
        }.thenRunAsync {
            val image = imageRepository.findById(profilePostReq.imageId ?: 0).orElseThrow()
            image.profileId = userId
            imageRepository.save(image)
        }

        return ResponseEntity.ok().build()
    }

    override fun get(authentication: Authentication): ResponseEntity<ProfileGetRes> {
        return this.get(authentication.name.toLong())
    }

    override fun delete(authentication: Authentication): ResponseEntity<HttpStatus> {
        val user = User(authentication.name.toLong())
        profileRepository.deleteById(authentication.name.toLong())
        prizeRepository.deleteByUser(user)
        careerRepository.deleteByUser(user)
        educationRepository.deleteByUser(user)
        imageRepository.deleteByUserId(user.id)
        return ResponseEntity.ok().build()
    }

    override fun get(id: Long): ResponseEntity<ProfileGetRes> {
        val profile = try {
            profileRepository.findById(id)
                .orElseThrow{throw NoSuchElementException("일치하는 사용자가 없습니다.")}
        } catch (noSuchElementException: NoSuchElementException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(
            ProfileGetRes(
                id = id,
                description = profile.description,
                imagePath = imageRepository.findByUserId(profile.userId).path,
                education = educationRepository.findAllByUser(User(profile.userId)).map { education -> education.content },
                careerList = careerRepository.findAllByUserId(id),
                prizeList = prizeRepository.findAllByUserId(id)
        ), HttpStatus.OK)
    }
}