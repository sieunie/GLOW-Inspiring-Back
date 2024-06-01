package DoCo.ServerA.global.repository

import DoCo.ServerA.global.data.entity.Prize
import DoCo.ServerA.global.repository.custom.PrizeRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PrizeRepository: JpaRepository<Prize, Int>, PrizeRepositoryCustom {
}