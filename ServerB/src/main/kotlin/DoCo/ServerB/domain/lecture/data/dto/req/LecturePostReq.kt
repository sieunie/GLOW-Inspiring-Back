package DoCo.ServerB.domain.lecture.data.dto.req

import io.swagger.v3.oas.annotations.Parameter
import java.time.LocalDateTime

data class LecturePostReq(
    val title: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val latitude: Float,
    val longitude: Float,
    val address: String,
    val description: String?,
    val topic: String,
    @Parameter(description = "IT, HUMANITY, SOCIETY, SCIENCE, DESIGN, ETC")
    val category: String,
    val imageId: List<Int>
)
