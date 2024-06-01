package DoCo.ServerB.domain.image.dto.res

import DoCo.ServerB.global.data.entity.Image

data class ImageGetRes (
    val id: Int,
    val img: String
){
    constructor(image: Image): this(
        id = image.id ?: 0,
        img = image.path
    )
}