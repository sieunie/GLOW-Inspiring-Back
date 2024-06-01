package DoCo.ServerA.domain.chat.dto.res

import DoCo.ServerA.global.data.entity.Chat
import DoCo.ServerA.global.data.entity.User
import java.time.LocalDateTime

data class ChatGetElementRes(
    val content: String,
    val isSend: Boolean,
    val createdAt: LocalDateTime
){
    constructor(chat: Chat, user: User): this(
        content = chat.content,
        isSend = (chat.sender == user),
        createdAt = chat.createdAt
    )
}