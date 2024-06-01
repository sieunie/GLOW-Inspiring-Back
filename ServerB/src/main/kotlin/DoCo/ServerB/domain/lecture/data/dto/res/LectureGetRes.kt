package DoCo.ServerB.domain.lecture.data.dto.res

import DoCo.ServerB.domain.image.dto.res.ImageGetRes
import DoCo.ServerB.global.data.dto.res.UserRes
import DoCo.ServerB.global.data.entity.Image
import DoCo.ServerB.global.data.entity.Lecture
import DoCo.ServerB.global.data.enum.LectureCategory
import java.time.LocalDateTime

data class LectureGetRes(
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
    val category: LectureCategory,
    val accepted: Boolean,
    val imageList: List<ImageGetRes>
) {
    constructor(lecture: Lecture, imageList: List<Image>): this(
        id = lecture.id ?: 0,
        user = UserRes(lecture.user),
        title = lecture.title,
        startTime = lecture.startTime,
        endTime = lecture.endTime,
        createdAt = lecture.createdAt,
        latitude = lecture.latitude,
        longitude = lecture.longitude,
        address = lecture.address,
        description = lecture.description ?: "",
        topic = lecture.topic,
        category = lecture.category,
        accepted = lecture.accepted,
        imageList = imageList.map {
                image ->
            ImageGetRes(image)
        }
    )
}
