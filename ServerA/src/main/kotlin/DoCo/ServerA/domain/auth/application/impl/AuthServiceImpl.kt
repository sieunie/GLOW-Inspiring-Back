package DoCo.ServerA.domain.auth.application.impl

import DoCo.ServerA.domain.auth.application.AuthService
import DoCo.ServerA.domain.auth.dto.AuthGetKakaoAccessTokenRes
import DoCo.ServerA.domain.auth.dto.AuthGetKakaoInfoRes
import DoCo.ServerA.domain.auth.dto.AuthGetLoginRes
import DoCo.ServerA.global.config.jwt.JwtTokenProvider
import DoCo.ServerA.global.data.entity.User
import DoCo.ServerA.global.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.lang.NullPointerException
import java.util.concurrent.CompletableFuture

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    @Value("\${kakao.login.client_id}")
    private val client_id: String,
    @Value("\${kakao.login.redirect_uri}")
    private val redirect_uri: String
): AuthService {

    private val kakaoAccessTokenServerUrl = "https://kauth.kakao.com/oauth/token"
    private val kakaoInfoServerUrl = "https://kapi.kakao.com/v2/user/me"
    override fun getLogin(code: String): ResponseEntity<AuthGetLoginRes> {

        val kakaoAccessTokenRes = try{
            getKakaoAccessToken(code)
        }catch(httpClientErrorException: HttpClientErrorException){
            return ResponseEntity(HttpStatus.BAD_GATEWAY)
        }catch(nullPointerException: NullPointerException){
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val kakaoUserInfoRes = try{
            getKakaoUserInfo(kakaoAccessTokenRes)
        }catch(nullPointerException: NullPointerException){
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        CompletableFuture.runAsync {
            if(userRepository.findById(kakaoUserInfoRes.id).isEmpty){
                register(kakaoUserInfoRes)
            }
        }

        val accessToken = jwtTokenProvider.createAccessToken(kakaoUserInfoRes.id)
        val refreshToken = jwtTokenProvider.createRefreshToken(kakaoUserInfoRes.id)

        return ResponseEntity.ok(AuthGetLoginRes(accessToken, refreshToken))
    }

    private fun register(kakaoUserInfoRes: AuthGetKakaoInfoRes){
        val user = User(kakaoUserInfoRes.id, kakaoUserInfoRes.properties.nickname)
        userRepository.save(user)
    }

    private fun getKakaoUserInfo(kakaoAccessTokenRes: String): AuthGetKakaoInfoRes {
        val restTemplate = RestTemplate()

        val httpHeaders = HttpHeaders()
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
        httpHeaders.add("Authorization", "Bearer $kakaoAccessTokenRes")
        return restTemplate.postForObject(kakaoInfoServerUrl, HttpEntity(null, httpHeaders), AuthGetKakaoInfoRes::class.java)!!
    }

    private fun getKakaoAccessToken(code: String): String{

        val restTemplate = RestTemplate()

        //Header
        val httpHeaders = HttpHeaders()
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")

        //Body
        val httpBody = LinkedMultiValueMap<String, String>()
        httpBody.add("grant_type","authorization_code")
        httpBody.add("client_id", client_id)
        httpBody.add("redirect_uri", redirect_uri)
        httpBody.add("code", code)

         val authGetKakaoAccessTokenRes = restTemplate.postForObject(
            kakaoAccessTokenServerUrl, HttpEntity(httpBody, httpHeaders), AuthGetKakaoAccessTokenRes::class.java)
        return authGetKakaoAccessTokenRes!!.access_token
    }
}