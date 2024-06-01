package DoCo.ServerB.domain.lecture.data.dto.res

import DoCo.ServerB.domain.image.dto.res.ImageGetRes
import DoCo.ServerB.global.data.entity.Image
import DoCo.ServerB.global.data.entity.Lecture
import DoCo.ServerB.global.data.enum.LectureCategory
import java.time.LocalDateTime

data class LectureGetElementRes(
    val id: Int,
    val title: String,
    val category: LectureCategory,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val image: ImageGetRes
) {
    constructor(lecture: Lecture, image: Image): this(
        id = lecture.id ?: 0,
        title = lecture.title,
        category = lecture.category,
        startTime = lecture.startTime,
        endTime = lecture.endTime,
        image = ImageGetRes(image)
    )
}
