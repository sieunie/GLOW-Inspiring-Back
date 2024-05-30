package DoCo.ServerB.global.data.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class MentoringRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @ManyToOne
    var mentoring: Mentoring,
    var userId: Long,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var accepted: Boolean = false
)
