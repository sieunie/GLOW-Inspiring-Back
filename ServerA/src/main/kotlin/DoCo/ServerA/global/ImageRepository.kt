package DoCo.ServerA.global

import DoCo.ServerA.global.data.entity.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

interface ImageRepository: JpaRepository<Image, Int> {
    fun findByUserId(userId: Long): Image
    @Transactional
    fun deleteByUserId(id: Long)
}