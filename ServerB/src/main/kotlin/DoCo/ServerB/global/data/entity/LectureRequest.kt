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
    @ManyToOne
    var user: User,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var accepted: Boolean = false
)
