package DoCo.ServerB.global.data.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class LectureRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @ManyToOne
    var lecture: Lecture,
    var userId: Long,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var accepted: Boolean = false
)
