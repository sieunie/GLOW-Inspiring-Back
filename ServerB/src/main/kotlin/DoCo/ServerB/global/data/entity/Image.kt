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
    var path: String
){
    constructor(path: String): this(id=null, mentoring = null, lecture = null, path = path)
}
