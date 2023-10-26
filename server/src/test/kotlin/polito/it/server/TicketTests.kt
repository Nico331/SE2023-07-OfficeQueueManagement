package polito.it.server

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import polito.it.server.counter.CounterRepository
import polito.it.server.counterServiceType.CounterServiceTypeRepository
import polito.it.server.serviceType.ServiceType
import polito.it.server.serviceType.ServiceTypeRepository
import polito.it.server.ticket.Ticket
import polito.it.server.ticket.TicketRepository
import polito.it.server.ticket.TicketServiceImpl
import java.sql.Timestamp
import java.time.Duration
import java.time.LocalDate
/*
@SpringBootTest
class TicketServiceImplTest {

    @InjectMocks
    lateinit var ticketService: TicketServiceImpl

    @Autowired
    lateinit var ticketRepository: TicketRepository

    @Autowired
    lateinit var counterRepository: CounterRepository

    @Autowired
    lateinit var serviceTypeRepository: ServiceTypeRepository

    @Autowired
    lateinit var counterServiceTypeRepository: CounterServiceTypeRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    fun testGetAll() {
        val tickets = listOf(
            Ticket(id = 1, number = 1, serviceType = ServiceType(code="P", tag = "Payments", serviceTime = 10, working = true), timestamp = Timestamp(System.currentTimeMillis()), dateIssued = LocalDate.now(), waitingTime = Duration.ofSeconds(500)),
            Ticket(id = 2, number = 2, serviceType = ServiceType(code="C", tag = "Cards", serviceTime = 8, working = true), timestamp = Timestamp(System.currentTimeMillis()), dateIssued = LocalDate.now(), waitingTime = Duration.ofSeconds(300))
        )
        //Mockito.`when`(ticketRepository.findAll()).thenReturn(mockTickets)
        tickets.forEach{ ticket->
            ticketRepository.save(ticket)
        }
        val result = ticketService.getAll()

        assertEquals(2, result.size)
        assertEquals(1, result[0].id)
        assertEquals(2, result[1].id)
    }

    @Test
    fun testGetTicket() {
        val mockTicket = Ticket(id = 1, number = 1, serviceType = ServiceType(code="P", tag = "Payments", serviceTime = 10, working = true), timestamp = Timestamp(System.currentTimeMillis()), dateIssued = LocalDate.now(), waitingTime = Duration.ofSeconds(500))
        Mockito.`when`(ticketRepository.findById(1).get()).thenReturn(mockTicket)

        val result = ticketService.getTicket(1)

        assertNotNull(result)
        assertEquals(1, result!!.id)
    }


}

*/