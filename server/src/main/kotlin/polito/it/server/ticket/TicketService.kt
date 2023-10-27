package polito.it.server.ticket

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import polito.it.server.serviceType.ServiceType
import polito.it.server.serviceType.ServiceTypeDTO

interface TicketService {

    fun getAll(): List<TicketDTO>

    fun getTicket(id: Long): TicketDTO?

    fun addTicket(serviceType: Long): ResponseEntity<TicketDTO>

    // Determino i tipi di servizio che il counter può gestire.
    // Identifico la coda più lunga tra quelle che il counter può gestire.
    // Se ho più code hanno la stessa lunghezza, scelgo quella con il tempo di servizio più breve.
    // Seleziona il primo ticket da quella coda.
    fun getNextTicket(counterId: Long): TicketDTO?

    fun updateTicket(updatedTicket: Ticket): ResponseEntity<TicketDTO> // per aggiornare lo stato del ticket
    fun estimateWaitTimeForTicket(serviceType: ServiceType): Int
    fun estimate(serviceType: ServiceType): Int
    fun getTicketsInQueueForServiceType(serviceTypeTag: String): List<TicketDTO>
    fun getCountOfWaitingCustomersPerServiceType(): Map<ServiceTypeDTO, Int>

    fun getCurrentCounterTicket(counterId: Long): TicketDTO
    fun stopTicket(counterId: Long): TicketDTO?
    fun getLatestTicketByCounterId(counterId: Long): TicketDTO?

    fun getTicketsWithInProgressOrServedStatus(): List<TicketDTO>

}