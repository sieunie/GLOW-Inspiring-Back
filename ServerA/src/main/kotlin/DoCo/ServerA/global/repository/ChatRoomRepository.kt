package DoCo.ServerA.global.repository

import DoCo.ServerA.global.data.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomRepository: JpaRepository<ChatRoom, Int> {
}