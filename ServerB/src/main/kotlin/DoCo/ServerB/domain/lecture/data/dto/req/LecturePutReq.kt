package DoCo.ServerB.domain.lecture.data.dto.req

import DoCo.ServerB.global.data.enum.LectureCategory
import DoCo.ServerB.global.data.enum.MentoringCategory
import java.time.LocalDateTime

data class LecturePutReq(
    val id: Int,
    val topic: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val address: String,
    val title: String,
    val category: LectureCategory,
    val imageIdList: List<Int>,
    val latitude: Float,
    val longitude: Float,
    val description: String
)
