package DoCo.ServerB.global.repository

import DoCo.ServerB.global.data.entity.LectureRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LectureRequestRepository: JpaRepository<LectureRequest, Int> {
}