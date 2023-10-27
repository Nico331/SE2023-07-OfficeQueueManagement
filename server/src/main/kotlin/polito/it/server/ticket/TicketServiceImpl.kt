package polito.it.server.ticket

import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

import org.springframework.web.bind.annotation.RequestBody
import polito.it.server.counterServiceType.CounterServiceTypeRepository
import polito.it.server.serviceType.ServiceType
import polito.it.server.serviceType.ServiceTypeDTO
import polito.it.server.serviceType.ServiceTypeRepository
import polito.it.server.serviceType.toDTO
import java.sql.Timestamp
import java.time.Duration
import org.springframework.data.domain.Pageable
import polito.it.server.counter.CounterRepository
import java.time.LocalDate
import java.time.Instant
import kotlin.math.roundToInt
import kotlin.time.ExperimentalTime
import kotlin.time.minutes

@Service
@Transactional
class TicketServiceImpl(
    private val ticketRepository: TicketRepository,
    private val counterRepository: CounterRepository,
    private val serviceTypeRepository: ServiceTypeRepository,
    private val counterServiceTypeRepository: CounterServiceTypeRepository,
) : TicketService {
    override fun getAll(): List<TicketDTO> {
        return ticketRepository.findAll().map {
            it.toDTO()
        }
    }

    override fun getTicket(id: Long): TicketDTO? {
        return ticketRepository.findByIdOrNull(id)?.toDTO()
    }
/*
    override fun addTicket(serviceType: Long): ResponseEntity<TicketDTO> {
        val service= serviceTypeRepository.findById(serviceType).get();
        val nextAcc = service.accumulator;
        val newTicket =
            Ticket(id = 0L, number = nextAcc, serviceType = serviceTypeRepository.findById(serviceType).get())
        val savedTicket = ticketRepository.save(newTicket)
        val savedService = serviceTypeRepository.save(service.apply { accumulator = (accumulator + 1)  })

        return ResponseEntity(savedTicket.toDTO(), HttpStatus.CREATED)
    }*/
    @OptIn(ExperimentalTime::class)
    override fun addTicket(serviceType: Long): ResponseEntity<TicketDTO> {
    val number = ticketRepository.countByServiceTypeIdAndDateIssued(serviceType,LocalDate.now());
    val newTicket = Ticket(id = 0L, number = number+1, serviceType = serviceTypeRepository.findById(serviceType).get(),
        timestamp = Timestamp(System.currentTimeMillis()), dateIssued = LocalDate.now(), waitingTime =
        Duration.ofMinutes( estimate(serviceTypeRepository.findById(serviceType).get()).toLong()))
    val savedTicket = ticketRepository.save(newTicket)



    return ResponseEntity(savedTicket.toDTO(), HttpStatus.CREATED)
}
    override fun getNextTicket(counterId: Long): TicketDTO? {
        // 1. Ottengo i tipi di servizio che il counter può gestire
        val serviceTypesForCounter = counterServiceTypeRepository.findByCounterId(counterId).map { it.serviceType }

        // 2. e 3. Trovo la coda più lunga con il minor tempo di servizio
        val selectedServiceType = serviceTypesForCounter
            .sortedWith(
                compareBy(
                    { -ticketRepository.countByServiceTypeTagAndStatus(it.tag, "waiting") },
                    { it.serviceTime })
            )
            .firstOrNull()
        println("ci sono fin qui")
        val count= ticketRepository.countByServiceTypeTagAndStatus(serviceTypeTag = selectedServiceType!!.tag, "waiting")
        println(count)
        if(count==0){
            return null
        } else{
            // 4. Seleziono il primo ticket da quella coda
            return ticketRepository.save(ticketRepository.findFirstByServiceTypeTagAndStatusOrderByTimestampAsc(
                selectedServiceType!!.tag,
                "waiting"
            ).copy(status="in progress", counter = counterRepository.findByIdOrNull(counterId), timestampCalled =  Timestamp(System.currentTimeMillis()), waitingTime = Duration.ofSeconds(0) )).toDTO()

        }
    }

    override fun updateTicket(updatedTicket: Ticket): ResponseEntity<TicketDTO> {
        val savedTicket = ticketRepository.save(updatedTicket)
        return ResponseEntity(savedTicket.toDTO(), HttpStatus.OK)
    }

    override fun estimateWaitTimeForTicket(serviceType: ServiceType): Int {
        val serviceTime = serviceType.serviceTime
        val numberPeopleInQueue = getTicketsInQueueForServiceType(serviceType.tag).count()
        val counterListThatSatisfyRequestedService = counterServiceTypeRepository.findDistinctByServiceType(serviceType)

        val sumFactors = counterListThatSatisfyRequestedService.map { 1.0/ counterServiceTypeRepository.countAllByCounter(it.counter).toFloat() }

        val time = serviceTime * (numberPeopleInQueue / sumFactors.sum() + 0.5)

        return time.roundToInt()

    }
    override fun estimate(serviceType: ServiceType): Int {
        // Check for null serviceType
        if(serviceType == null) throw IllegalArgumentException("ServiceType cannot be null.")

        val tr = serviceType.serviceTime
        // Check for invalid serviceTime
        if(tr <= 0) throw IllegalArgumentException("ServiceTime must be positive.")
        val nr = ticketRepository.countByServiceTypeTagAndStatus(serviceType.tag, "waiting")
            ?: throw RuntimeException("Failed to retrieve tickets in queue.")
        println("test")
        println(nr)
        val countersForService = counterServiceTypeRepository.findDistinctByServiceType(serviceType)
            ?: throw RuntimeException("Failed to retrieve counters that satisfy requested service.")
        println(countersForService)
        val sumFactors = countersForService.map {
            val ki = counterServiceTypeRepository.countAllTypesByCounter(it.counter)
            // Indicator variable si,r
            val sir = if(it.serviceType == serviceType) 1 else 0
            sir / ki.toFloat()
        }.sum()
        println(sumFactors)
        return if(sumFactors.toDouble() == 0.0) {
            0
        } else {
            val time = tr * (nr / sumFactors + 0.5)
            println(time)
            time.roundToInt()
        }
    }

    override fun getTicketsInQueueForServiceType(serviceTypeTag: String): List<TicketDTO> {
        return ticketRepository.findByServiceTypeTagAndStatus(serviceTypeTag, "waiting")
            .map { ticket -> ticket.toDTO() }
    }

    override fun getCountOfWaitingCustomersPerServiceType(): Map<ServiceTypeDTO, Int> {
        return serviceTypeRepository.findAll().map { it.toDTO() }.associateWith { serviceType ->
            ticketRepository.countByServiceTypeTagAndStatus(serviceType.tag, "waiting")
        }
    }

    override fun getCurrentCounterTicket(counterId: Long): TicketDTO {
        return ticketRepository.findFirstByCounterIdAndTimestampNotNullOrderByTimestampDesc(counterId).toDTO();
    }

    override fun getTicketsWithInProgressOrServedStatus(): List<TicketDTO> {
        val pageRequest = PageRequest.of(0, 5)
        return ticketRepository.findByInProgressOrServedStatus(pageRequest).map { it.toDTO() }
    }

    override fun stopTicket(counterId: Long): TicketDTO? {
        val ticket:Ticket? = ticketRepository.findFirstByCounterIdAndStatus(counterId, "in progress");
        ticket?.apply { status = "served"}
        return if(ticket!=null){
            ticketRepository.save(ticket).toDTO()
        } else {
            null
        }
    }
    override fun getLatestTicketByCounterId(counterId: Long): TicketDTO? {
        return ticketRepository.findLatestTicketByCounterId(counterId)?.toDTO()
    }
}