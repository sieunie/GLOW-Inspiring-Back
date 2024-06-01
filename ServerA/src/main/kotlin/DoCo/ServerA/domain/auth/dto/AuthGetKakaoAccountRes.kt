package DoCo.ServerA.domain.auth.dto

data class AuthGetKakaoAccountRes (
    val profile_nickname_needs_agreement: Boolean,
    val profile: AuthGetKakaoProfileRes
)