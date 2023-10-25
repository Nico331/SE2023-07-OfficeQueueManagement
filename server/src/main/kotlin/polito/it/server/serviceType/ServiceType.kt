package polito.it.server.serviceType

import jakarta.persistence.*
import polito.it.server.EntityBase

@Entity
@Table(name = "service_types")
data class ServiceType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false, unique = true)
    val tag: String,

    @Column(nullable = false)
    val serviceTime: Int,

    @Column(nullable = false)
    val working: Boolean,

    @Column(nullable = false)
    val accumulator: Int = 0,
)