package DoCo.ServerB.global.data.dto.res

import DoCo.ServerB.global.data.entity.User

data class UserRes(
    val id: Long,
    val name: String
){
    constructor(user: User): this(id = user.id, name = user.name ?: "")
}