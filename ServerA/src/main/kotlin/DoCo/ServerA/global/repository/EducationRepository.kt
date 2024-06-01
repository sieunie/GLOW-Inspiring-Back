package DoCo.ServerA.global.repository

import DoCo.ServerA.global.data.entity.Education
import DoCo.ServerA.global.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

interface EducationRepository: JpaRepository<Education, Int> {
    fun findAllByUser(user: User): List<Education>
    @Transactional
    fun deleteByUser(user: User): Int
}