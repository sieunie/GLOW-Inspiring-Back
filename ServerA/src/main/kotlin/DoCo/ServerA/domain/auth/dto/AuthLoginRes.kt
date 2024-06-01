package DoCo.ServerA.domain.auth.dto

import io.swagger.v3.oas.annotations.media.Schema

data class AuthLoginRes(
    @Schema(description = "AccessToken")
    val accessToken: String,
    @Schema(description = "RefreshToken")
    val refreshToken: String
)
