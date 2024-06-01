package DoCo.ServerB.domain.mentoring.dto.res

import DoCo.ServerB.global.data.dto.res.UserRes
import DoCo.ServerB.global.data.enum.MentoringCategory
import java.time.LocalDateTime

data class MentoringGetRes(
    val id: Int,
    val user: UserRes,
    val title: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val createdAt: LocalDateTime,
    val latitude: Float,
    val longitude: Float,
    val address: String,
    val description: String,
    val topic: String,
    val category: MentoringCategory,
    val accepted: Boolean
){
}
