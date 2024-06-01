package DoCo.ServerB.domain.mentoring.dto.res

import DoCo.ServerB.domain.image.dto.res.ImageGetRes
import DoCo.ServerB.global.data.entity.Image
import DoCo.ServerB.global.data.entity.Mentoring
import DoCo.ServerB.global.data.enum.MentoringCategory
import java.time.LocalDateTime

data class MentoringGetElementRes(
    val id: Int,
    val title: String,
    val category: MentoringCategory,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val image: ImageGetRes
){
    constructor(mentoring: Mentoring, image: Image): this(
        id = mentoring.id ?: 0,
        title = mentoring.title,
        category = mentoring.category,
        startTime = mentoring.startTime,
        endTime = mentoring.endTime,
        image = ImageGetRes(image)
    )
}
