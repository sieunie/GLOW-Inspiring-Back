package DoCo.ServerB.global.repository

import DoCo.ServerB.global.data.entity.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository: JpaRepository<Image, Int> {
}