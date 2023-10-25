package polito.it.server.serviceType

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ServiceTypeRepository: JpaRepository<ServiceType, Long> {
    fun findByTag(tag: String): Optional<ServiceType>
}