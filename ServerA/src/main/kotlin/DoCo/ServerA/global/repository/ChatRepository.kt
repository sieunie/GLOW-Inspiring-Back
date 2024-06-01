package DoCo.ServerA.global.repository

import DoCo.ServerA.global.data.entity.Chat
import DoCo.ServerA.global.data.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository: JpaRepository<Chat, Int> {

    fun findByChatRoom(chatRoom: ChatRoom): List<Chat>
}