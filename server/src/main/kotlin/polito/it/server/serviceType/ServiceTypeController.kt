package polito.it.server.serviceType

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/API")
class ServiceTypeController (private val serviceTypeService: ServiceTypeService){
    @GetMapping("/getservicetypes")
    fun getCounters(): List<ServiceTypeDTO> {
        return serviceTypeService.getAll()
    }
    @PostMapping("/addservicetype")
    fun addServiceType(@RequestBody request: ServiceTypeDTO): ServiceTypeDTO {
        return serviceTypeService.addServiceType(request.tag, request.serviceTime, request.code)
    }

    @DeleteMapping("/removeservicetype/{tag}")
    fun removeServiceType(@PathVariable tag: String) {
        println(tag)
        return serviceTypeService.removeServiceType(tag)
    }
}