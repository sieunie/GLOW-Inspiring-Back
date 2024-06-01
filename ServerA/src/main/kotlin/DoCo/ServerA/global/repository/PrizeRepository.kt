package DoCo.ServerA.global.repository

import DoCo.ServerA.global.data.entity.Prize
import DoCo.ServerA.global.data.entity.User
import DoCo.ServerA.global.repository.custom.PrizeRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PrizeRepository: JpaRepository<Prize, Int>, PrizeRepositoryCustom {
    @Transactional
    fun deleteByUser(user: User)
}