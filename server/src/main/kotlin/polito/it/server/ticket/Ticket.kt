package polito.it.server.ticket

import jakarta.persistence.*
import polito.it.server.EntityBase
import polito.it.server.counter.Counter
import polito.it.server.serviceType.ServiceType
import java.sql.Timestamp
import java.time.Duration
import java.time.LocalDate

@Entity
@Table(name = "tickets")
data class Ticket(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(unique = false)
    val number: Int?=null,

    @Column()
    val timestamp: Timestamp?=null, // 2023-10-22:15:56:00

    @Column()
    val dateIssued: LocalDate?=null, //2023-10-22

    @ManyToOne
    val serviceType: ServiceType,

    @ManyToOne
    val counter: Counter?=null,

    @Column(nullable = false)
    var status: String = "waiting", // Ad es. "waiting", "served", etc.

    @Column(nullable = true)
    val waitingTime: Duration?=null // Ad es. "waiting", "served", etc.
)