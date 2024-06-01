package DoCo.ServerA.domain.chat.dto.res

import DoCo.ServerA.global.data.entity.ChatRoom
import DoCo.ServerA.global.data.entity.User
import java.time.LocalDateTime

data class ChatRoomGetElementRes(
    val id: Long?,
    val name: String?,
    val lastChat: String,
    val updatedAt: LocalDateTime?
){
    constructor(chatRoom: ChatRoom, user: User): this(
        id = if(user.id == chatRoom.user1.id) chatRoom.user2.id else chatRoom.user1.id,
        name = if(user.id == chatRoom.user1.id) chatRoom.user2.name else chatRoom.user1.name,
        lastChat = chatRoom.lastContent ?: "", updatedAt = chatRoom.updatedAt
    )
}
