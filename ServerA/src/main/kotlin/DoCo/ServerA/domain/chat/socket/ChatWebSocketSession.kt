package DoCo.ServerA.domain.chat.socket

import DoCo.ServerA.global.data.entity.User
import org.springframework.web.socket.WebSocketSession

class ChatWebSocketSession(
    val webSocketSession: WebSocketSession,
    val receiver: User,
    val sender: User
)