package DoCo.ServerB.domain.mentoring.dto.req

import DoCo.ServerB.global.data.enum.MentoringCategory
import java.time.LocalDateTime

data class MentoringPostReq(
    val topic: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val address: String,
    val title: String,
    val category: MentoringCategory,
    val imageIdList: List<Int>,
    val latitude: Float,
    val longitude: Float,
    val description: String
)
