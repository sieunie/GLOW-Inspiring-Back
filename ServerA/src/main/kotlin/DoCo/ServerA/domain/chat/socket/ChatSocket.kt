package DoCo.ServerA.domain.chat.socket

import DoCo.ServerA.global.config.jwt.JwtTokenProvider
import DoCo.ServerA.global.data.entity.Chat
import DoCo.ServerA.global.data.entity.ChatRoom
import DoCo.ServerA.global.data.entity.User
import DoCo.ServerA.global.repository.ChatRepository
import DoCo.ServerA.global.repository.ChatRoomRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.io.IOException

@Component
class ChatSocket(
    private val chatRepository: ChatRepository,
    private val chatRoomRepository: ChatRoomRepository,
    private val objectMapper: ObjectMapper,
    private val jwtTokenProvider: JwtTokenProvider): TextWebSocketHandler(){

    private val chatWebSocketHandler: MutableList<ChatWebSocketSession> = ArrayList()
    private val webSocketHandler: MutableList<WebSocketSession> = ArrayList()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        webSocketHandler.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        webSocketHandler.remove(session)
        chatWebSocketHandler.map {
                chatWebSocketSession ->
            if(chatWebSocketSession.webSocketSession == session) chatWebSocketHandler.remove(chatWebSocketSession)
        }
        super.afterConnectionClosed(session, status)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        val isEstablished = objectMapper.readTree(payload).get("isEstablished").asBoolean()
        if(isEstablished){
            val sender = objectMapper.readTree(payload).get("accessToken").asText()
            val receiver = objectMapper.readTree(payload).get("receiver").asLong()
            chatWebSocketHandler.add(ChatWebSocketSession(session, User(jwtTokenProvider.getId(sender) ?: 0), User(receiver)))
        }
        else{
            val sender = objectMapper.readTree(payload).get("accessToken").asText()
            val senderId = jwtTokenProvider.getId(sender)?:0
            val receiver = objectMapper.readTree(payload).get("receiver").asLong()
            val content = objectMapper.readTree(payload).get("content").asText()
            println(senderId)
            println(receiver)
            println(content)
            val user1 = User(senderId)
            val user2 = User(receiver)
            val chatRoom = chatRoomRepository.findByUserAndUser(user1, user2) ?: ChatRoom(user1, user2)
            val chat = Chat(id = null, chatRoom = chatRoom, content = content, sender = user1)
            chatRepository.save(chat)
            for(chatWebSocketSession in chatWebSocketHandler){
                println("receiver: ${chatWebSocketSession.receiver.id}, sender: ${chatWebSocketSession.sender.id}")
                if(chatWebSocketSession.receiver.id == receiver && chatWebSocketSession.sender.id == senderId){
                    try{
                        chatWebSocketSession.webSocketSession.sendMessage(
                            TextMessage(
                            objectMapper.writeValueAsBytes(content)
                        )
                        )
                    }catch (e: IOException){
                        throw RuntimeException(e)
                    }
                }
            }
        }
        super.handleTextMessage(session, message)
    }
    }