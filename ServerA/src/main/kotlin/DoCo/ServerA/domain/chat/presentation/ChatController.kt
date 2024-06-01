package DoCo.ServerA.domain.chat.presentation

import DoCo.ServerA.domain.chat.application.ChatService
import DoCo.ServerA.domain.chat.dto.res.ChatGetElementRes
import DoCo.ServerA.domain.chat.dto.res.ChatRoomGetElementRes
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chat")
@Tag(name = "채팅")
class ChatController(
    private val chatService: ChatService
) {
    @GetMapping("/list")
    @Operation(summary = "채팅방 리스트 조회")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content(schema = Schema(implementation = ChatRoomGetElementRes::class))))
    )
    fun getList(@Parameter(hidden = true) authentication: Authentication): ResponseEntity<List<ChatRoomGetElementRes>>{
        return chatService.getList(authentication)
    }

    @GetMapping
    @Operation(summary = "채팅방 상세 조회")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content(schema = Schema(implementation = ChatGetElementRes::class))))
    )
    fun get(@RequestParam chatRoomId: Int, @Parameter(hidden = true) authentication: Authentication): ResponseEntity<List<ChatGetElementRes>>{
        return chatService.get(chatRoomId, authentication)
    }
}