package DoCo.ServerB.domain.lecture.data.dto.res

import DoCo.ServerB.global.data.dto.res.UserRes
import DoCo.ServerB.global.data.entity.LectureRequest
import java.time.LocalDateTime

data class LectureRequestGetElementRes(
    val id: Int,
    val userRes: UserRes,
    val createdAt: LocalDateTime,
    val accepted: Boolean
) {
    constructor(lectureRequest: LectureRequest): this(
        id = lectureRequest.id ?: 0, userRes = UserRes(lectureRequest.user), createdAt = lectureRequest.createdAt, accepted = lectureRequest.accepted
    )
}
