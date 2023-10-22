package polito.it.server.ticket

import jakarta.persistence.*
import polito.it.server.EntityBase
import polito.it.server.counter.Counter
import polito.it.server.serviceType.ServiceType
import java.sql.Timestamp
import java.time.LocalDate

@Entity
@Table(name = "tickets")
data class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false, unique = false)
    val number: Int,

    @Column(nullable = false)
    val timestamp: Timestamp, // 2023-10-22:15:56:00

    @Column(nullable = false)
    val dateIssued: LocalDate, //2023-10-22

    @ManyToOne
    val serviceType: ServiceType,

    @ManyToOne
    val counter: Counter,

    @Column(nullable = false)
    val status: String // Ad es. "waiting", "served", etc.
)