package polito.it.server.ticket

import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import polito.it.server.serviceType.ServiceType
import java.awt.print.Pageable

@Repository
interface TicketRepository: JpaRepository<Ticket, Long> {
    fun findByServiceTypeTagAndStatus(serviceTypeTag: String, status: String): List<Ticket>
    fun countByServiceTypeTagAndStatus(serviceTypeTag: String, status: String): Int
    fun findFirstByServiceTypeTagAndStatusOrderByTimestampAsc(selectedServiceTypeTag: String, status: String): Ticket

    fun findFirstByCounterIdAndTimestampNotNullOrderByTimestampDesc(counterId: Long): Ticket

    fun findByCounterIsNotNullOrderByDateIssuedDesc(pageRequest: PageRequest): List<Ticket>

    fun findFirstByCounterIdOrderByTimestampDesc(counterId: Long): Ticket
}
