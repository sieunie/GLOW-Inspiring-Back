package DoCo.ServerB.global.repository

import DoCo.ServerB.global.data.entity.Mentoring
import DoCo.ServerB.global.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

interface MentoringRepository: JpaRepository<Mentoring, Int> {
    fun findByIdAndUser(id: Int, user: User): Optional<Mentoring>
    @Transactional
    fun deleteByIdAndUser(id: Int, user: User): Int
}