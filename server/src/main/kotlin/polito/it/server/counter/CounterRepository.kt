package polito.it.server.counter

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CounterRepository: JpaRepository<Counter, Long> {
    fun findByNumber(number: Int): Optional<Counter>
}