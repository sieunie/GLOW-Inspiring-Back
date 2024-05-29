package DoCo.ServerA.global.data.entity

import jakarta.persistence.*

@Entity
data class ChatRoom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @ManyToOne
    var user1: User,
    @ManyToOne
    var user2: User,
    var lastContent: String
)
