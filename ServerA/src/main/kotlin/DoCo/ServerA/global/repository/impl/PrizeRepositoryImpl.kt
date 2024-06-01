package DoCo.ServerA.global.repository.impl

import DoCo.ServerA.global.data.entity.QPrize.prize
import DoCo.ServerA.global.repository.custom.PrizeRepositoryCustom
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class PrizeRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
): PrizeRepositoryCustom {
    override fun findAllByUserId(id: Long): List<String> {
        return jpaQueryFactory
            .select(prize.content)
            .from(prize)
            .where(prize.user.id.eq(id))
            .fetch()
    }
}