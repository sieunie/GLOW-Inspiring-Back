package DoCo.ServerA.global.repository.custom

interface PrizeRepositoryCustom {
    fun findAllByUserId(id: Long): List<String>
}