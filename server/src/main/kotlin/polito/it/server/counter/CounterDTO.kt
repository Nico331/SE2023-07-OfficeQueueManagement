package polito.it.server.counter

data class CounterDTO(
    val id: Long,
    val number: Int
)

fun Counter.toDTO(): CounterDTO {
    return CounterDTO(
        id = this.id,
        number = this.number
    )
}

