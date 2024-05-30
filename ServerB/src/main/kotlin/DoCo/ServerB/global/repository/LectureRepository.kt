package DoCo.ServerB.global.repository

import DoCo.ServerB.global.data.entity.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LectureRepository: JpaRepository<Lecture, Int> {
}