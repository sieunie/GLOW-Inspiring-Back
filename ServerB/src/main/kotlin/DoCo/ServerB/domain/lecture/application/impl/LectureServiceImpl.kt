package DoCo.ServerB.domain.lecture.application.impl

import DoCo.ServerB.domain.lecture.application.LectureService
import DoCo.ServerB.global.repository.LectureRepository
import DoCo.ServerB.global.repository.LectureRequestRepository
import org.springframework.stereotype.Service

@Service
class LectureServiceImpl(
    private val lectureRepository: LectureRepository,
    private val lectureRequestRepository: LectureRequestRepository
): LectureService {
}