package com.autohub.autohub.vehicles.serviceimpl

import com.autohub.autohub.autohubfiles.enums.FileEventType
import com.autohub.autohub.autohubfiles.events.AutohubFileEvent
import com.autohub.autohub.configuration.exceptions.EntityNotFoundException
import com.autohub.autohub.configuration.responses.ApiResponse
import com.autohub.autohub.configuration.responses.Data
import com.autohub.autohub.configuration.responses.ResponseType
import com.autohub.autohub.vehicles.dto.VehicleDto
import com.autohub.autohub.vehicles.entity.Vehicle
import com.autohub.autohub.vehicles.enums.DriveTrain
import com.autohub.autohub.vehicles.enums.EngineAspiration
import com.autohub.autohub.vehicles.enums.EngineLayout
import com.autohub.autohub.vehicles.enums.FuelType
import com.autohub.autohub.vehicles.enums.TransmissionType
import com.autohub.autohub.vehicles.enums.VehicleMake
import com.autohub.autohub.vehicles.enums.VehicleModel
import com.autohub.autohub.vehicles.events.VehicleEvent
import com.autohub.autohub.vehicles.repository.VehicleRepository
import com.autohub.autohub.vehicles.service.VehiclesService
import io.jsonwebtoken.io.IOException
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties
import org.springframework.boot.system.ApplicationHome
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.UUID


@Service
class VehiclesServiceImpl(
   private val vehicleRepository: VehicleRepository,
   private val applicationEventPublisher: ApplicationEventPublisher
) : VehiclesService {

    companion object{
        const val PAGE_SIZE = 20
    }

    // all vehicles
    override fun allVehicles(pageNumber: Int): List<Vehicle> {
        val sort: Sort = Sort.by(Sort.Direction.DESC, "createdAt")
        val pageRequest: PageRequest = PageRequest.of(pageNumber, PAGE_SIZE, sort)
        val page: List<Vehicle> = vehicleRepository.findAll(sort)
        return page
    }

    // add vehicle
    override fun addVehicle(vehicleDto: VehicleDto): Vehicle {
        println("saving vehicle")
        var vehicle: Vehicle = Vehicle(
            name = vehicleDto.name,
            make = VehicleMake.valueOf(vehicleDto.make),
            model = VehicleModel.valueOf(vehicleDto.model),
            price = vehicleDto.price,
            mileage = vehicleDto.mileage,
            fuelType = FuelType.valueOf(vehicleDto.fuelType),
            location = vehicleDto.location,
            engineLayout = EngineLayout.valueOf(vehicleDto.engineLayout),
            engineAspiration = EngineAspiration.valueOf(vehicleDto.engineAspiration),
            transmissionType = TransmissionType.valueOf(vehicleDto.transmissionType),
            description = vehicleDto.description,
            driveTrain = DriveTrain.valueOf(vehicleDto.driveTrain)
        )
        println(vehicleDto)
        val saved: Vehicle = vehicleRepository.save(vehicle)
        applicationEventPublisher.publishEvent(AutohubFileEvent(saved, vehicleDto.files, FileEventType.Upload))
        return vehicle
    }

    // vehicle by id
    override fun vehicleById(id: String): Vehicle {
        val vehicle: Vehicle = vehicleRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Vehicle not found") }
        applicationEventPublisher.publishEvent(VehicleEvent(vehicle, vehicle.name))
        return vehicle
    }

    // update vehicle
    override fun updateVehicle(
        id: String,
        vehicleDto: VehicleDto
    ): Vehicle {
        val vehicle: Vehicle = vehicleRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Vehicle not found") }
        vehicle.name = vehicleDto.name
        vehicle.make = VehicleMake.valueOf(vehicleDto.make)
        vehicle.model = VehicleModel.valueOf(vehicleDto.model)
        vehicle.price = vehicleDto.price
        vehicle.mileage = vehicleDto.mileage
        vehicle.fuelType = FuelType.valueOf(vehicleDto.fuelType)
        vehicle.location = vehicleDto.location
        vehicle.engineLayout = EngineLayout.valueOf(vehicleDto.engineLayout)
        vehicle.engineAspiration = EngineAspiration.valueOf(vehicleDto.engineAspiration)
        vehicle.transmissionType = TransmissionType.valueOf(vehicleDto.transmissionType)
        vehicle.description = vehicleDto.description
        vehicle.driveTrain = DriveTrain.valueOf(vehicleDto.driveTrain)
        val saved: Vehicle = vehicleRepository.save(vehicle)
        return saved
    }

    // search vehicles
    override fun searchVehicles(query: String): List<Vehicle> {
        val vehicles: List<Vehicle> = vehicleRepository.findAllByNameContainingIgnoreCase(query)
        return vehicles
    }


    // delete vehicle
    override fun deleteVehicle(id: String): Unit {
        val vehicle: Vehicle? = vehicleRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Vehicle not found") }
        vehicleRepository.delete(vehicle!!)
        applicationEventPublisher.publishEvent(AutohubFileEvent(vehicle!!, null, FileEventType.Delete))
    }

}