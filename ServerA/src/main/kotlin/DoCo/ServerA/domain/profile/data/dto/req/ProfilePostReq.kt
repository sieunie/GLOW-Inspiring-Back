package DoCo.ServerA.domain.profile.data.dto.req

data class ProfilePostReq(
    val description: String?,
    val imageId: Int?,
    val education: String?,
    val careerList: List<String>,
    val prizeList: List<String>
)
