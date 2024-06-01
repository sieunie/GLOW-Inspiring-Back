package DoCo.ServerA.global.repository.custom

interface CareerRepositoryCustom {
    fun findAllByUserId(id: Long): List<String>
}