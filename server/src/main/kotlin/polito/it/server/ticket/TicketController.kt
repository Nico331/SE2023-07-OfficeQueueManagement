package polito.it.server.ticket

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/API")
class TicketController (private val ticketService: TicketService) {

    data class Ticket(val id: Long, val title: String, val description: String, val status: String, val created_at: String)

    @GetMapping("/gettickets")
    fun getTickets(): List<Ticket> {
        val ticketList = listOf(
            Ticket(1, "Delivery", "Managing delivery.", "Waiting", "2023-10-20 19:00"),
            Ticket(2, "Payments", "Managing payments.", "Waiting", "2023-10-20 19:15"),
            Ticket(3, "Cards", "Managing cards.", "Closed", "2023-10-19 18:00")
        )
        return ticketList
    }

    @GetMapping("/ticket/counter/{counterId}")
    fun getCurrentCounterTicket(@PathVariable counterId: String): TicketDTO {
        return ticketService.getCurrentCounterTicket(counterId.toLong())
    }
}