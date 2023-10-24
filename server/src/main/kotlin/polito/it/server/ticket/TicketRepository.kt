package polito.it.server.ticket

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import polito.it.server.serviceType.ServiceType

@Repository
interface TicketRepository: JpaRepository<Ticket, Long> {
    fun findByServiceTypeTagAndStatus(serviceTypeTag: String, status: String): List<Ticket>
    fun countByServiceTypeTagAndStatus(serviceTypeTag: String, status: String): Int
    fun findFirstByServiceTypeTagAndStatusOrderByTimestampAsc(selectedServiceTypeTag: String, status: String): Ticket

    fun findFirstByCounterIdAndDateIssuedNullOrderByTimestampDesc(counterId: Long): Ticket
}
