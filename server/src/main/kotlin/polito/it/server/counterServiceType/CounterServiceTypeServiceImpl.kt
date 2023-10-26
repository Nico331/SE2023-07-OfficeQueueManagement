package polito.it.server.counterServiceType

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import polito.it.server.counter.CounterRepository
import polito.it.server.serviceType.ServiceTypeDTO
import polito.it.server.serviceType.ServiceTypeRepository
import polito.it.server.serviceType.toDTO

@Service
@Transactional
class CounterServiceTypeServiceImpl(
    private val counterServiceTypeRepository: CounterServiceTypeRepository,
    private val counterRepository: CounterRepository,
    private val serviceTypeRepository: ServiceTypeRepository
): CounterServiceTypeService {
    override fun getAllDistinctServiceTypes(): List<ServiceTypeDTO> {
        return counterServiceTypeRepository.findAllDistinctServiceTypes()
            .map { it.toDTO() }
    }
    override fun addCounterServiceType(counterId: Long, serviceTypeId: Long): CounterServiceTypeDTO? {
        val counter = counterRepository.findByIdOrNull(counterId)
        val serviceType = serviceTypeRepository.findByIdOrNull(serviceTypeId)
        return if(counter!= null && serviceType!= null){
            val counterServiceType = CounterServiceType(counter = counter, serviceType = serviceType)
            counterServiceTypeRepository.save(counterServiceType).toDTO()
        } else null
    }
}