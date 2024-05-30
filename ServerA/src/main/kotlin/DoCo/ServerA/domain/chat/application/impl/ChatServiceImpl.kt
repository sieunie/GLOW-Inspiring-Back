package DoCo.ServerA.domain.chat.application.impl

import DoCo.ServerA.domain.chat.application.ChatService
import DoCo.ServerA.global.repository.ChatRepository
import DoCo.ServerA.global.repository.ChatRoomRepository
import org.springframework.stereotype.Service

@Service
class ChatServiceImpl(
    private val chatRepository: ChatRepository,
    private val chatRoomRepository: ChatRoomRepository
): ChatService {
}