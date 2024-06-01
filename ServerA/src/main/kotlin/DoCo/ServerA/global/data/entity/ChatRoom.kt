package DoCo.ServerA.global.data.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class ChatRoom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @ManyToOne
    var user1: User,
    @ManyToOne
    var user2: User,
    var lastContent: String?,
    var updatedAt: LocalDateTime?
){
    constructor(user1: User, user2: User): this(id = null, user1 = user1, user2 = user2, lastContent = null, updatedAt = null)
}
