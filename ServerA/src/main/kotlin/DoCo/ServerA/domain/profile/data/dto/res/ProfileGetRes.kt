package DoCo.ServerA.domain.profile.data.dto.res

data class ProfileGetRes(
    val id: Long,
    val description: String?,
    val imagePath: String?,
    val education: String?,
    val careerList: List<String>,
    val prizeList: List<String>
)
