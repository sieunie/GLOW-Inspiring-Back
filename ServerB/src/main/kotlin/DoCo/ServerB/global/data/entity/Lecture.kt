package DoCo.ServerB.global.data.entity

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
)
