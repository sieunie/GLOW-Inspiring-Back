package DoCo.ServerB.global.repository

import DoCo.ServerB.global.data.entity.Mentoring
import org.springframework.data.jpa.repository.JpaRepository

interface MentoringRepository: JpaRepository<Mentoring, Int> {
}