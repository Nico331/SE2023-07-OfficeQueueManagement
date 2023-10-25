package polito.it.server.ticket

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/API")
class TicketController (private val ticketService: TicketService) {

    data class Ticket(val id: Long, val title: String, val description: String, val status: String, val created_at: String)

    @GetMapping("/gettickets")
    fun getTickets(): List<TicketDTO> {
        return ticketService.getMainboard()
    }

    @GetMapping("/ticket/counter/{counterId}")
    fun getCurrentCounterTicket(@PathVariable counterId: String): TicketDTO {
        return ticketService.getCurrentCounterTicket(counterId.toLong())
    }

    @PostMapping("/ticket")
    fun addTicket(@RequestBody serviceType: Long) : ResponseEntity<TicketDTO> {
        return ticketService.addTicket(serviceType)
    }

}