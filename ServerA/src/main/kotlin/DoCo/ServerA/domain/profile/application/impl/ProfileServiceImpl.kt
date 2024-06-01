package DoCo.ServerA.domain.profile.application.impl

import DoCo.ServerA.domain.profile.application.ProfileService
import DoCo.ServerA.domain.profile.data.dto.req.ProfilePostReq
import DoCo.ServerA.domain.profile.data.dto.res.ProfileGetRes
import DoCo.ServerA.global.data.entity.Career
import DoCo.ServerA.global.data.entity.Prize
import DoCo.ServerA.global.data.entity.Profile
import DoCo.ServerA.global.repository.CareerRepository
import DoCo.ServerA.global.repository.PrizeRepository
import DoCo.ServerA.global.repository.ProfileRepository
import DoCo.ServerA.global.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class ProfileServiceImpl(
    private val profileRepository: ProfileRepository,
    private val prizeRepository: PrizeRepository,
    private val careerRepository: CareerRepository,
    private val userRepository: UserRepository,
): ProfileService {

    override fun post(profilePostReq: ProfilePostReq, authentication: Authentication): ResponseEntity<HttpStatus> {
        val user = userRepository.findById(authentication.name.toLong())
            .orElseThrow{throw NoSuchElementException("일치하는 사용자가 없습니다.") }

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

    override fun get(id: Long): ResponseEntity<ProfileGetRes> {
        val profile = profileRepository.findById(id)
            .orElseThrow{throw NoSuchElementException("일치하는 사용자가 없습니다.")}

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