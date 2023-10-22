package polito.it.server.serviceType

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceTypeRepository: JpaRepository<ServiceType, Long> {

}