package polito.it.server.ticket

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/API")
class TicketController (private val ticketService: TicketService) {

    data class Ticket(val id: Long, val title: String, val description: String, val status: String, val created_at: String)

    @GetMapping("/gettickets")
    fun getTickets(): List<TicketDTO> {
        return ticketService.getTicketsWithInProgressOrServedStatus()
    }

    @GetMapping("/ticket/counter/{counterId}")
    fun getCurrentCounterTicket(@PathVariable counterId: String): TicketDTO? {
        return ticketService.getLatestTicketByCounterId(counterId.toLong())
    }

    @PostMapping("/ticket/servicetype/{serviceType}")
    fun addTicket(@PathVariable serviceType: Long) : ResponseEntity<TicketDTO> {
        return ticketService.addTicket(serviceType)
    }


    @PutMapping("/counter/{counterId}/next")
    fun nextTicket(@PathVariable counterId: Long) : TicketDTO {
        return ticketService.getNextTicket(counterId);
    }
    @PutMapping("/counter/{counterId}/stop")
    fun stopTicket(@PathVariable counterId: Long) : TicketDTO? {
        return ticketService.stopTicket(counterId)
    }

}