package DoCo.ServerA.global.repository

import DoCo.ServerA.global.data.entity.ChatRoom
import DoCo.ServerA.global.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomRepository: JpaRepository<ChatRoom, Int> {

    @Query(
        "SELECT chatRoom FROM ChatRoom chatRoom " +
                "where chatRoom.user1 = :user AND chatRoom.user2 = :user " +
                "ORDER BY chatRoom.updatedAt DESC"
    )
    fun findByUser(user: User): List<ChatRoom>

    @Query(
        "SELECT chatRoom FROM ChatRoom chatRoom " +
                "where (chatRoom.user1 = :user1 AND chatRoom.user2 = :user2) OR " +
                "(chatRoom.user2 = :user1 AND chatRoom.user1 = :user2)"
    )
    fun findByUserAndUser(user1: User, user2: User): ChatRoom?
}