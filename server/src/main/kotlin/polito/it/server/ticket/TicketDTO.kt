package polito.it.server.ticket

import java.sql.Timestamp
import java.time.LocalDate

data class TicketDTO(
    val id: Long,
    val number: Int,
    val timestamp: Timestamp,
    val dateIssued: LocalDate,
    val serviceTypeId: Long,
    val counterId: Long,
    val status: String
)

fun Ticket.toDTO(): TicketDTO {
    return TicketDTO(
        id = this.id,
        number = this.number,
        timestamp = this.timestamp,
        dateIssued = this.dateIssued,
        serviceTypeId = this.serviceType.id,
        counterId = this.counter.id,
        status = this.status
    )
}
