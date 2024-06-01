package DoCo.ServerB.global.config.jwt

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret-key}")
    val secretKey: String
) {

    fun getId(token: String): Long?{
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body.get("id", java.lang.Long::class.java).toLong()
    }

    fun isAccessToken(token: String): Boolean{
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .header["type"].toString() == "access"
    }
}