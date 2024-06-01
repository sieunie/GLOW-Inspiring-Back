package DoCo.ServerB.global.repository

import DoCo.ServerB.global.data.entity.Lecture
import DoCo.ServerB.global.data.entity.Mentoring
import DoCo.ServerB.global.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface LectureRepository: JpaRepository<Lecture, Int> {
    fun findByIdAndUser(id: Int, user: User): Optional<Lecture>
    @Transactional
    fun deleteByIdAndUser(id: Int, user: User): Int
}