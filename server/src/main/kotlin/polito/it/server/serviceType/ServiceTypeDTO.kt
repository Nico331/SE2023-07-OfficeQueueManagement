package polito.it.server.serviceType

data class ServiceTypeDTO(
    val id: Long,
    val tag: String,
    val code: String,
    val serviceTime: Int,
    val working: Boolean,
)
fun ServiceType.toDTO(): ServiceTypeDTO {
    return ServiceTypeDTO(
        id = this.id,
        tag = this.tag,
        code = this.code,
        serviceTime = this.serviceTime,
        working = this.working,
    )
}
