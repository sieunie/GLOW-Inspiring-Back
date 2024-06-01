package DoCo.ServerB.global.repository

import DoCo.ServerB.global.data.entity.Image
import DoCo.ServerB.global.data.entity.Lecture
import DoCo.ServerB.global.data.entity.Mentoring
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository: JpaRepository<Image, Int> {
    fun existsByIdAndUserId(imageId: Int, toLong: Long): Boolean
    fun findByMentoring(mentoring: Mentoring?): List<Image>
    fun findByMentoringOrderByIdDesc(mentoring: Mentoring, of: PageRequest): Image
    fun findByLecture(lecture: Lecture?): List<Image>
    fun findByLectureOrderByIdDesc(lecture: Lecture, of: PageRequest): Image
}