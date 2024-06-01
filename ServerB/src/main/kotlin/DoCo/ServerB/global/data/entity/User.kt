package DoCo.ServerB.global.data.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class User(
    @Id
    var id: Long,
    var name: String?
){
    constructor(id: Long): this(id = id, name = null)
}