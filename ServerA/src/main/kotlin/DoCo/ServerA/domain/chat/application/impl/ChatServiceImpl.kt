package DoCo.ServerA.domain.chat.application.impl

import DoCo.ServerA.domain.chat.application.ChatService
import DoCo.ServerA.domain.chat.dto.res.ChatGetElementRes
import DoCo.ServerA.domain.chat.dto.res.ChatRoomGetElementRes
import DoCo.ServerA.global.data.entity.User
import DoCo.ServerA.global.repository.ChatRepository
import DoCo.ServerA.global.repository.ChatRoomRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class ChatServiceImpl(
    private val chatRepository: ChatRepository,
    private val chatRoomRepository: ChatRoomRepository
): ChatService {
    override fun getList(authentication: Authentication): ResponseEntity<List<ChatRoomGetElementRes>> {
        val user = User(authentication.name.toLong())
        return ResponseEntity(chatRoomRepository.findByUser(user).map {
            chatRoom ->
                ChatRoomGetElementRes(chatRoom, user)
        }, HttpStatus.OK)
    }

    override fun get(chatRoomId: Int, authentication: Authentication): ResponseEntity<List<ChatGetElementRes>> {
        val user = User(authentication.name.toLong())
        val chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow()
        return ResponseEntity.ok(chatRepository.findByChatRoom(chatRoom).map {
                chat -> ChatGetElementRes(chat, user)
        })
    }
}