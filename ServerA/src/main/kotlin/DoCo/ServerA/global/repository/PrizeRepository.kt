package DoCo.ServerA.global.repository

import DoCo.ServerA.global.data.entity.Prize
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PrizeRepository: JpaRepository<Prize, Int> {
}