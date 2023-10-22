package polito.it.server.counter

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class CounterServiceImpl(
    private val counterRepository: CounterRepository,
): CounterService{
    override fun getAll(): List<CounterDTO>{
        return counterRepository.findAll().map { it.toDTO() }
    }
    override fun addCounter(counterNumber: Int): CounterDTO{
        val counterOptional = counterRepository.findByNumber(counterNumber)
        return if (counterOptional.isPresent) {
            counterRepository.save(counterOptional.get().copy(working = true )).toDTO()
        }
        else
         counterRepository.save(Counter(0L, counterNumber, true )).toDTO()
    }
    override fun removeCounter(counterNumber: Int) {
        val counterOptional = counterRepository.findByNumber(counterNumber)
        counterOptional.ifPresent {
            counterRepository.save(counterOptional.get().copy(working = false))
        }
    }
}