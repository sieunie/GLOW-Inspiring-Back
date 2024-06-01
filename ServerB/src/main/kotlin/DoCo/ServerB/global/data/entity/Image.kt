package DoCo.ServerB.global.data.entity

import jakarta.persistence.*

@Entity
data class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @ManyToOne
    var mentoring: Mentoring?,
    @ManyToOne
    var lecture: Lecture?,
    var path: String,
    var userId: Long
){
    constructor(path: String, userId: Long): this(id=null, mentoring = null, lecture = null, path = path, userId = userId)
}
