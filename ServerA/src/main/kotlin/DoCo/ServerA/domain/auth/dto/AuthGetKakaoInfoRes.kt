package DoCo.ServerA.domain.auth.dto

data class AuthGetKakaoInfoRes(
    val id: Long,
    val connected_at: String,
    val properties: AuthGetKakaoUserPropertiesRes,
    val kakao_account: AuthGetKakaoAccountRes
)
