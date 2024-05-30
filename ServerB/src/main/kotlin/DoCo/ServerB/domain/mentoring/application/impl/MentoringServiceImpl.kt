package DoCo.ServerB.domain.mentoring.application.impl

import DoCo.ServerB.domain.mentoring.application.MentoringService
import DoCo.ServerB.global.repository.MentoringRepository
import DoCo.ServerB.global.repository.MentoringRequestRepository
import org.springframework.stereotype.Service

@Service
class MentoringServiceImpl(
    private val mentoringRepository: MentoringRepository,
    private val mentoringRequestRepository: MentoringRequestRepository
): MentoringService {
}