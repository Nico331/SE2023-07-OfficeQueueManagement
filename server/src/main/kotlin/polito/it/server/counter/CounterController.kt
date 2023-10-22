package polito.it.server.counter

import org.springframework.web.bind.annotation.*
import polito.it.server.serviceType.ServiceTypeDTO

@RestController
@RequestMapping("/API")
class CounterController (private val counterService: CounterService){
    @GetMapping("/getcounters")
    fun getCounters(): List<CounterDTO> {
        return counterService.getAll()
    }
    @PostMapping("/addcounter")
    fun addServiceType(@RequestBody request: CounterDTO): CounterDTO {
        return counterService.addCounter(request.number)
    }

    @DeleteMapping("/removecounter/{number}")
    fun removeServiceType(@PathVariable number: String) {
        return counterService.removeCounter(number.toInt())
    }
}