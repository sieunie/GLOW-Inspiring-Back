package DoCo.ServerB.global.repository

import DoCo.ServerB.global.data.entity.Lecture
import DoCo.ServerB.global.data.entity.LectureRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LectureRequestRepository: JpaRepository<LectureRequest, Int> {
    fun findByLecture(lecture: Lecture): List<LectureRequest>
    fun findByIdAndLecture(id: Int, lecture: Lecture): Optional<LectureRequest>
}