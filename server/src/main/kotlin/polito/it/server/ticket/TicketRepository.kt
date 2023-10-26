package polito.it.server.ticket

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface TicketRepository: JpaRepository<Ticket, Long> {
    fun findByServiceTypeTagAndStatus(serviceTypeTag: String, status: String): List<Ticket>
    fun countByServiceTypeTagAndStatus(serviceTypeTag: String, status: String): Int
    fun findFirstByServiceTypeTagAndStatusOrderByTimestampAsc(selectedServiceTypeTag: String, status: String): Ticket

    fun findFirstByCounterIdAndTimestampNotNullOrderByTimestampDesc(counterId: Long): Ticket

    @Query("SELECT t FROM Ticket t WHERE (t.status = 'in progress' OR t.status = 'served') ORDER BY t.timestampCalled DESC")
    fun findByInProgressOrServedStatus(pageRequest: Pageable): List<Ticket>

    fun findFirstByCounterIdAndStatus(counterId: Long, status: String): Ticket
    fun countByServiceTypeIdAndDateIssued(id: Long,dateIssued: LocalDate): Int
    @Query("SELECT t FROM Ticket t WHERE t.counter.id = :counterId ORDER BY t.timestamp DESC LIMIT 1")
    fun findLatestTicketByCounterId(counterId: Long): Ticket?

}
