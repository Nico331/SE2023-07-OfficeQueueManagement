package polito.it.server.counter

import polito.it.server.serviceType.ServiceTypeDTO

interface CounterService {
    fun getAll(): List<CounterDTO>

    fun addCounter(counterNumber: Int): CounterDTO

    fun removeCounter(counterNumber: Int)
    fun getServicesByCounterId(counterId: Long): List<ServiceTypeDTO>
}