package DoCo.ServerA.global.data.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne

@Entity
data class Profile(
    @Id
    @OneToOne
    var user: User,
    var description: String?,
    var image: String?,
    var education: String?
)
