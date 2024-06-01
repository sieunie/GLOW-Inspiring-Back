package DoCo.ServerB.domain.mentoring.dto.res

import DoCo.ServerB.domain.image.dto.res.ImageGetRes
import DoCo.ServerB.global.data.dto.res.UserRes
import DoCo.ServerB.global.data.entity.Image
import DoCo.ServerB.global.data.entity.Mentoring
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
    val accepted: Boolean,
    val imageList: List<ImageGetRes>
){

    constructor(mentoring: Mentoring, imageList: List<Image>): this(
        id = mentoring.id ?: 0,
        user = UserRes(mentoring.user),
        title = mentoring.title,
        startTime = mentoring.startTime,
        endTime = mentoring.endTime,
        createdAt = mentoring.createdAt,
        latitude = mentoring.latitude,
        longitude = mentoring.longitude,
        address = mentoring.address,
        description = mentoring.description ?: "",
        topic = mentoring.topic,
        category = mentoring.category,
        accepted = mentoring.accepted,
        imageList = imageList.map {
            image ->
            ImageGetRes(image)
        }
    )
}
