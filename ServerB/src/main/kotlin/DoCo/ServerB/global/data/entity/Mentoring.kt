package DoCo.ServerB.global.data.entity

import DoCo.ServerB.domain.mentoring.dto.req.MentoringPutReq
import DoCo.ServerB.global.data.enum.MentoringCategory
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Mentoring(
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
    var category: MentoringCategory,
    var accepted: Boolean = false
) {
    fun put(mentoringPutReq: MentoringPutReq): Mentoring {
        this.topic = mentoringPutReq.topic
        this.startTime = mentoringPutReq.startTime
        this.endTime = mentoringPutReq.endTime
        this.address = mentoringPutReq.address
        this.title = mentoringPutReq.title
        this.category = mentoringPutReq.category
        this.latitude = mentoringPutReq.latitude
        this.description = mentoringPutReq.description
        return this
    }
}
