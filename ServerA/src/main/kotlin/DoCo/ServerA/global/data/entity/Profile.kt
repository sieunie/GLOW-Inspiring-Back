package DoCo.ServerA.global.data.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Profile(
    @Id
    var userId: Long,
    var description: String?,
    var image: String?,
    var education: String?
)
