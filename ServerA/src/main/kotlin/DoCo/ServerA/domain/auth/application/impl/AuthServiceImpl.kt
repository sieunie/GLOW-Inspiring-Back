package DoCo.ServerA.domain.auth.application.impl

import DoCo.ServerA.domain.auth.application.AuthService
import DoCo.ServerA.global.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository
): AuthService {
}