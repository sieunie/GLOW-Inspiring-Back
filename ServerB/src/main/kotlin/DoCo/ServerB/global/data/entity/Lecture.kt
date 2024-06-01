package DoCo.ServerB.global.data.entity

import DoCo.ServerB.domain.lecture.data.dto.req.LecturePutReq
import DoCo.ServerB.global.data.enum.LectureCategory
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Lecture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @ManyToOne
    var user: User,
    var title: String,
    var startTime: LocalDateTime,
    var endTime: LocalDateTime,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var latitude: Float,
    var longitude: Float,
    var address: String,
    var description: String?,
    var topic: String,
    @Enumerated(EnumType.STRING)
    var category: LectureCategory,
    var accepted: Boolean = false
) {
    fun put(lecturePutReq: LecturePutReq): Lecture {
        this.topic = lecturePutReq.topic
        this.startTime = lecturePutReq.startTime
        this.endTime = lecturePutReq.endTime
        this.address = lecturePutReq.address
        this.title = lecturePutReq.title
        this.category = lecturePutReq.category
        this.latitude = lecturePutReq.latitude
        this.description = lecturePutReq.description
        return this
    }
}
