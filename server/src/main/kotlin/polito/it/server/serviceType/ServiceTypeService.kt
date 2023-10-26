package polito.it.server.serviceType


interface ServiceTypeService {
    fun getAll(): List<ServiceTypeDTO>

    fun addServiceType(serviceTag: String, serviceTime: Int, code: String): ServiceTypeDTO

    fun removeServiceType(serviceTag: String)
}