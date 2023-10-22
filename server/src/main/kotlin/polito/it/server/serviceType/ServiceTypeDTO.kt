package polito.it.server.serviceType

data class ServiceTypeDTO(
    val id: Long,
    val tag: String,
    val serviceTime: Int,
    val working: Boolean,
)
fun ServiceType.toDTO(): ServiceTypeDTO {
    return ServiceTypeDTO(
        id = this.id,
        tag = this.tag,
        serviceTime = this.serviceTime,
        working = this.working,
    )
}
