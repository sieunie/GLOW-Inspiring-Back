package DoCo.ServerA.global.repository

import DoCo.ServerA.global.data.entity.Chat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository: JpaRepository<Chat, Int> {
}