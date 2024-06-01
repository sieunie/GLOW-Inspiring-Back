package DoCo.ServerA.global.repository.impl

import DoCo.ServerA.global.data.entity.QCareer.career
import DoCo.ServerA.global.repository.custom.CareerRepositoryCustom
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class CareerRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
): CareerRepositoryCustom {
    override fun findAllByUserId(id: Long): List<String> {
        return jpaQueryFactory
            .select(career.content)
            .from(career)
            .where(career.user.id.eq(id))
            .fetch()
    }
}