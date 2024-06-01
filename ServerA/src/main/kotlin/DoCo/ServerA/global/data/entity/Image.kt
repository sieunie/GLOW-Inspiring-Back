package DoCo.ServerA.global.data.entity

import jakarta.persistence.*

@Entity
data class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    var mentoringId: Int?,
    var lectureId: Int?,
    var profileId: Long?,
    var path: String,
    var userId: Long
){
    constructor(path: String, userId: Long): this(id=null, mentoringId = null, lectureId = null, profileId = null, path = path, userId = userId)
}
