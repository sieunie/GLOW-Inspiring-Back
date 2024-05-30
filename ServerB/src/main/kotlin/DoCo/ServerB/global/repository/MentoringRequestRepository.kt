package DoCo.ServerB.global.repository

import DoCo.ServerB.global.data.entity.MentoringRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MentoringRequestRepository: JpaRepository<MentoringRequest, Int> {
}