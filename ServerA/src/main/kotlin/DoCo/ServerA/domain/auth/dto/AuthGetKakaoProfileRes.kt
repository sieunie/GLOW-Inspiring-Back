package DoCo.ServerA.domain.auth.dto

data class AuthGetKakaoProfileRes(
    val nickname: String,
    val is_default_nickname: Boolean,
    val profile_image_url: String
)
