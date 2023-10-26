package polito.it.server.counterServiceType

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import polito.it.server.serviceType.ServiceTypeDTO


@RestController
@RequestMapping("/API/counterServiceTypes")
class CounterServiceTypeController(private val counterServiceTypeService: CounterServiceTypeService) {

    @GetMapping("/distinctServiceTypes")
    fun getAllDistinctServiceTypes(): List<ServiceTypeDTO> {
        return counterServiceTypeService.getAllDistinctServiceTypes()
    }
    @PostMapping("/addCounterServiceType")
    fun addCounterServiceType(@RequestBody request: Body ): ResponseEntity<Void> {
        counterServiceTypeService.addCounterServiceType(request.counterId, request.serviceTypeId)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    data class Body(
        val counterId: Long,
        val serviceTypeId: Long
    )
}