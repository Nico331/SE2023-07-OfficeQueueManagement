package polito.it.server.counter

interface CounterService {
    fun getAll(): List<CounterDTO>

    fun addCounter(counterNumber: Int): CounterDTO

    fun removeCounter(counterNumber: Int)
}