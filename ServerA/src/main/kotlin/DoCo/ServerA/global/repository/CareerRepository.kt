package DoCo.ServerA.global.repository

import DoCo.ServerA.global.data.entity.Career
import DoCo.ServerA.global.repository.custom.CareerRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CareerRepository: JpaRepository<Career, Int>, CareerRepositoryCustom {
}