package polito.it.server.counter

data class CounterDTO(
    val id: Long,
    val number: Int,
    val working: Boolean,
)

fun Counter.toDTO(): CounterDTO {
    return CounterDTO(
        id = this.id,
        number = this.number,
        working = this.working
    )
}

