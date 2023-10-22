package polito.it.server.serviceType

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import polito.it.server.counter.*
import polito.it.server.counterServiceType.CounterServiceTypeRepository

@Service
@Transactional
class ServiceTypeServiceImpl(
    private val serviceTypeRepository: ServiceTypeRepository,
    private val counterServiceTypeRepository: CounterServiceTypeRepository,
): ServiceTypeService {
    override fun getAll(): List<ServiceTypeDTO>{
        return serviceTypeRepository.findAll().map { it.toDTO() }
    }

    override fun addServiceType(serviceTag: String, serviceTime: Int): ServiceTypeDTO {
        val serviceTypeOptional = serviceTypeRepository.findByTag(serviceTag)
        return if (serviceTypeOptional.isPresent) {
            serviceTypeRepository.save(serviceTypeOptional.get().copy(serviceTime = serviceTime, working = true)).toDTO()
        } else
            serviceTypeRepository.save(ServiceType(0L, serviceTag, serviceTime, true)).toDTO()
    }
    override fun removeServiceType(tag: String) {
        val serviceTypeOptional = serviceTypeRepository.findByTag(tag)

        if (serviceTypeOptional.isPresent) {
            serviceTypeRepository.save(serviceTypeOptional.get().copy(working = false))
        }
    }
}