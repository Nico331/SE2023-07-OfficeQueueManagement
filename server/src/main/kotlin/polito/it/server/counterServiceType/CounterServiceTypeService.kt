package polito.it.server.counterServiceType

import polito.it.server.counter.Counter
import polito.it.server.serviceType.ServiceType
import polito.it.server.serviceType.ServiceTypeDTO

interface CounterServiceTypeService {
    fun getAllDistinctServiceTypes(): List<ServiceTypeDTO>
    fun addCounterServiceType(counterId: Long, serviceTypeId: Long): CounterServiceTypeDTO?;
}