package DoCo.ServerA.domain.profile.presentation

import DoCo.ServerA.domain.profile.application.ProfileService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/profile")
@Tag(name = "프로필")
class ProfileController(
    private val profileService: ProfileService
) {
}