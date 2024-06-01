package DoCo.ServerA.domain.profile.application.impl

import DoCo.ServerA.domain.profile.application.ProfileService
import DoCo.ServerA.domain.profile.data.dto.req.ProfilePostReq
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
            imageId = profilePostReq.imageId,
            education = profilePostReq.education,
        ))

        for (prize in profilePostReq.prizeList) {
            prizeRepository.save(Prize(
                id = null,
                user = user,
                content = prize
            ))
        }

        for (career in profilePostReq.careerList) {
            careerRepository.save(Career(
                id = null,
                user = user,
                content = career
            ))
        }

        return ResponseEntity(HttpStatus.OK)
    }
}