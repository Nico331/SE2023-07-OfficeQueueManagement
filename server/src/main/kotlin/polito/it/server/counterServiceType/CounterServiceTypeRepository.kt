package polito.it.server.counterServiceType

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import polito.it.server.counter.Counter
import polito.it.server.serviceType.ServiceType

@Repository
interface CounterServiceTypeRepository: JpaRepository<CounterServiceType, Long> {
    fun findByCounterId(counterId: Long): List<CounterServiceType>
    fun existsByServiceType(serviceType: ServiceType): Boolean
    fun findDistinctByServiceType(serviceType: ServiceType) : List<CounterServiceType>
    fun countAllByCounter (counter: Counter) : Int
    @Query("SELECT DISTINCT cst.serviceType FROM CounterServiceType cst")
    fun findAllDistinctServiceTypes(): List<ServiceType>
    @Query("SELECT COUNT(DISTINCT c.serviceType) FROM CounterServiceType c WHERE c.counter = :counter")
    fun countAllTypesByCounter(counter: Counter?): Int
    @Query("SELECT cst.serviceType FROM CounterServiceType cst WHERE cst.counter.id = :counterId")
    fun findServiceTypesByCounterId(@Param("counterId") counterId: Long): List<ServiceType>

}