package polito.it.server.counterServiceType

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import polito.it.server.serviceType.ServiceType

@Repository
interface CounterServiceTypeRepository: JpaRepository<CounterServiceType, Long> {
    fun findByCounterId(counterId: Long): List<CounterServiceType>
    fun existsByServiceType(serviceType: ServiceType): Boolean
}