package DoCo.ServerA.global.data.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class Chat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @ManyToOne
    var chatRoom: ChatRoom,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var content: String,
    var isSender: Boolean
)
