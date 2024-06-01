package DoCo.ServerA.domain.chat.application

import DoCo.ServerA.domain.chat.dto.res.ChatGetElementRes
import DoCo.ServerA.domain.chat.dto.res.ChatRoomGetElementRes
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication

interface ChatService {
    fun getList(authentication: Authentication): ResponseEntity<List<ChatRoomGetElementRes>>
    fun get(chatRoomId: Int, authentication: Authentication): ResponseEntity<List<ChatGetElementRes>>
}