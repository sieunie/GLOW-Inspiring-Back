package DoCo.ServerA.domain.profile.application.impl

import DoCo.ServerA.domain.profile.application.ProfileService
import DoCo.ServerA.domain.profile.data.dto.req.ProfilePostReq
import DoCo.ServerA.domain.profile.data.dto.res.ProfileGetRes
import DoCo.ServerA.global.data.entity.Career
import DoCo.ServerA.global.data.entity.Prize
import DoCo.ServerA.global.data.entity.Profile
import DoCo.ServerA.global.data.entity.User
import DoCo.ServerA.global.repository.CareerRepository
import DoCo.ServerA.global.repository.PrizeRepository
import DoCo.ServerA.global.repository.ProfileRepository
import DoCo.ServerA.global.repository.UserRepository
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
): ProfileService {

    override fun post(profilePostReq: ProfilePostReq, authentication: Authentication): ResponseEntity<HttpStatus> {
        val user = try {
            userRepository.findById(authentication.name.toLong())
                .orElseThrow{throw NoSuchElementException("일치하는 사용자가 없습니다.") }
        } catch (noSuchElementException: NoSuchElementException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        profileRepository.save(Profile(
            userId = user.id,
            description = profilePostReq.description,
            imagePath = null,
            education = profilePostReq.education,
        ))

        profilePostReq.prizeList.map {
            prize ->
            prizeRepository.save(Prize(
                id = null,
                user = user,
                content = prize
            ))
        }

        profilePostReq.careerList.map {
            career ->
            careerRepository.save(Career(
                id = null,
                user = user,
                content = career
            ))
        }

        return ResponseEntity(HttpStatus.OK)
    }

    override fun put(profilePostReq: ProfilePostReq, authentication: Authentication): ResponseEntity<HttpStatus> {
        val userId = authentication.name.toLong()
        val user = try {
            profileRepository.deleteByUserId(userId)
            User(authentication.name.toLong())
        } catch (noSuchElementException: NoSuchElementException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        CompletableFuture.supplyAsync {
            profileRepository.save(Profile(
                userId = userId,
                description = profilePostReq.description,
                imagePath = null,
                education = profilePostReq.education,
            ))
        }.thenApplyAsync {
            profilePostReq.prizeList.map {
                    prize ->
                prizeRepository.save(Prize(
                    id = null,
                    user = user,
                    content = prize
                ))
            }
        }.thenApplyAsync {
            profilePostReq.careerList.map {
                    career ->
                careerRepository.save(Career(
                    id = null,
                    user = user,
                    content = career
                ))
            }
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
                imagePath = profile.imagePath,
                education = profile.education,
                careerList = careerRepository.findAllByUserId(id),
                prizeList = prizeRepository.findAllByUserId(id)
        ), HttpStatus.OK)
    }
}