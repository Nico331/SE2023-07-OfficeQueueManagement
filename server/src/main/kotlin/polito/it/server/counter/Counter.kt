package polito.it.server.counter
import jakarta.persistence.*
import polito.it.server.serviceType.ServiceType

@Entity
@Table(name = "counters")
data class Counter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val number: Int
)
