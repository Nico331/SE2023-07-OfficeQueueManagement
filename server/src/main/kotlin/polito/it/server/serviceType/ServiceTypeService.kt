package polito.it.server.serviceType


interface ServiceTypeService {
    fun getAll(): List<ServiceTypeDTO>

    fun addServiceType(serviceTag: String, serviceTime: Int): ServiceTypeDTO

    fun removeServiceType(serviceTag: String)
}