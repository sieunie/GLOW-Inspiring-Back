package DoCo.ServerB.global.repository

import DoCo.ServerB.global.data.entity.Mentoring
import DoCo.ServerB.global.data.entity.MentoringRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MentoringRequestRepository: JpaRepository<MentoringRequest, Int> {
    fun findByMentoring(mentoring: Mentoring): List<MentoringRequest>
    fun findByIdAndMentoring(id: Int, mentoring: Mentoring): Optional<MentoringRequest>
}