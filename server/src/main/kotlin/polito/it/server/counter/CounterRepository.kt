package polito.it.server.counter

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CounterRepository: JpaRepository<Counter, Long> {

}