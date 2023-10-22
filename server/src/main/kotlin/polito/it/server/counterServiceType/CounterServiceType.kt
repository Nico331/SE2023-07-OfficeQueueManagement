package polito.it.server.counterServiceType

import jakarta.persistence.*
import polito.it.server.counter.Counter
import polito.it.server.serviceType.ServiceType

@Entity
@Table(name = "counter_service_types")
data class CounterServiceType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne
    val counter: Counter,

    @ManyToOne
    val serviceType: ServiceType
)