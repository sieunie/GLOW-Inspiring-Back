package DoCo.ServerA.domain.chat.presentation

import DoCo.ServerA.domain.chat.application.ChatService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chat")
@Tag(name = "채팅")
class ChatController(
    private val chatService: ChatService
) {
}