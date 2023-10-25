package polito.it.server.ticket

import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import polito.it.server.counterServiceType.CounterServiceTypeRepository
import polito.it.server.serviceType.ServiceType
import polito.it.server.serviceType.ServiceTypeDTO
import polito.it.server.serviceType.ServiceTypeRepository
import polito.it.server.serviceType.toDTO
import java.sql.Timestamp
import kotlin.time.Duration

@Service
@Transactional
class TicketServiceImpl(
    private val ticketRepository: TicketRepository,
    private val serviceTypeRepository: ServiceTypeRepository,
    private val counterServiceTypeRepository: CounterServiceTypeRepository,
): TicketService {
    override fun getAll(): List<TicketDTO> {
        return ticketRepository.findAll().map {
            it.toDTO()
        }
    }

    override fun getTicket(id: Long): TicketDTO? {
        return ticketRepository.findByIdOrNull(id)?.toDTO()
    }

    override fun addTicket(newTicket: Ticket): ResponseEntity<TicketDTO> {
        val savedTicket = ticketRepository.save(newTicket)

        return ResponseEntity(savedTicket.toDTO(), HttpStatus.CREATED)
    }

    override fun getNextTicket(counterId: Long): TicketDTO {
        // 1. Ottengo i tipi di servizio che il counter può gestire
        val serviceTypesForCounter = counterServiceTypeRepository.findByCounterId(counterId).map { it.serviceType }

        // 2. e 3. Trovo la coda più lunga con il minor tempo di servizio
        val selectedServiceType = serviceTypesForCounter
            .sortedWith(compareBy({ -ticketRepository.countByServiceTypeTagAndStatus(it.tag, "waiting") }, { it.serviceTime }))
            .firstOrNull()

        // 4. Seleziono il primo ticket da quella coda
        return ticketRepository.findFirstByServiceTypeTagAndStatusOrderByTimestampAsc(selectedServiceType!!.tag, "waiting").toDTO()
    }
    override fun updateTicket(updatedTicket: Ticket): ResponseEntity<TicketDTO> {
        val savedTicket = ticketRepository.save(updatedTicket)
        return ResponseEntity(savedTicket.toDTO(), HttpStatus.OK)
    }

    override fun estimateWaitTimeForTicket(ticketId: Long): Int {
        // Da fare. Ora restituisco 10 minuti
        return 10
    }

    override fun getTicketsInQueueForServiceType(serviceTypeTag: String): List<TicketDTO> {
        return ticketRepository.findByServiceTypeTagAndStatus(serviceTypeTag, "waiting").map { ticket -> ticket.toDTO() }
    }
    override fun getCountOfWaitingCustomersPerServiceType(): Map<ServiceTypeDTO, Int> {
        return serviceTypeRepository.findAll().map { it.toDTO() }.associateWith { serviceType ->
            ticketRepository.countByServiceTypeTagAndStatus(serviceType.tag, "waiting")
        }
    }

    override fun getCurrentCounterTicket(counterId: Long): TicketDTO {
        return ticketRepository.findFirstByCounterIdAndTimestampNotNullOrderByTimestampDesc(counterId).toDTO();
    }
    override fun getMainboard(): List<TicketDTO>{
        val pageRequest = PageRequest.of(0,5)
        return ticketRepository.findByCounterIsNotNullOrderByDateIssuedDesc(pageRequest).map { it.toDTO() };
    }
}