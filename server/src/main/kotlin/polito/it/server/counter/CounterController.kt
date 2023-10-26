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
    @GetMapping("/getservices/{counterId}")
    fun getServices(@PathVariable counterId: Long): List<ServiceTypeDTO> {
        return counterService.getServicesByCounterId(counterId);
    }
    @PostMapping("/addcounter")
    fun addServiceType(@RequestBody request: CounterDTO): CounterDTO {
        return counterService.addCounter(Integer.parseInt(request.number.toString()))
    }

    @DeleteMapping("/removecounter/{number}")
    fun removeServiceType(@PathVariable number: String) {
        return counterService.removeCounter(number.toInt())
    }
}