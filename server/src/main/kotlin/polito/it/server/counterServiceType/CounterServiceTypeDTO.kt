package polito.it.server.counterServiceType

data class CounterServiceTypeDTO(
    val id: Long,
    val counterId: Long,
    val serviceTypeId: Long
)

fun CounterServiceType.toDTO(): CounterServiceTypeDTO {
    return CounterServiceTypeDTO(
        id = this.id,
        counterId = this.counter.id,
        serviceTypeId = this.serviceType.id
    )
}