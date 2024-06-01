package DoCo.ServerB.domain.mentoring.dto.res

import DoCo.ServerB.global.data.dto.res.UserRes
import DoCo.ServerB.global.data.entity.MentoringRequest
import java.time.LocalDateTime

data class MentoringRequestGetElementRes(
    val id: Int,
    val userRes: UserRes,
    val createdAt: LocalDateTime,
    val accepted: Boolean
){
    constructor(mentoringRequest: MentoringRequest): this(
        id = mentoringRequest.id ?: 0, userRes = UserRes(mentoringRequest.user), createdAt = mentoringRequest.createdAt, accepted = mentoringRequest.accepted
    )
}
