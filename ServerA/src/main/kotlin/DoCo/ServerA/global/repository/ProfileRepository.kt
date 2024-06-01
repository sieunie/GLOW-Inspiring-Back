package DoCo.ServerA.global.repository

import DoCo.ServerA.global.data.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ProfileRepository: JpaRepository<Profile, Long> {
    @Transactional
    fun deleteByUserId(userId: Long)
}