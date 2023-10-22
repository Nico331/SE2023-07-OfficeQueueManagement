package polito.it.server.counterServiceType

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CounterServiceTypeRepository: JpaRepository<CounterServiceType, Long> {
    fun findByCounterId(counterId: Long): List<CounterServiceType>
}