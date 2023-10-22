package polito.it.server.serviceType

data class ServiceTypeDTO(
    val id: Long,
    val tag: String,
    val serviceTime: Int
)
fun ServiceType.toDTO(): ServiceTypeDTO {
    return ServiceTypeDTO(
        id = this.id,
        tag = this.tag,
        serviceTime = this.serviceTime
    )
}
