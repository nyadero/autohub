package com.autohub.autohub.vehicles.controller

import com.autohub.autohub.vehicles.dto.VehicleDto
import com.autohub.autohub.vehicles.entity.Vehicle
import com.autohub.autohub.vehicles.service.VehiclesService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/vehicles")
class VehiclesController(
    private val vehiclesService: VehiclesService
) {

    // add vehicle
    @PostMapping("")
    fun addVehicle(
        @ModelAttribute vehicleDto: VehicleDto
    ): ResponseEntity<Vehicle?>{
        println(vehicleDto.model)
        return vehiclesService.addVehicle(vehicleDto)
    }

    //    get all vehicles
    @GetMapping("")
    fun allVehicles(
        @RequestParam(name = "pageNumber", defaultValue = "0")  pageNumber: Int
    ): ResponseEntity<List<Vehicle>> {
        return vehiclesService.allVehicles(pageNumber)
    }

    // get vehicle by id
    @GetMapping("/{id}")
    fun vehicleById(
        @PathVariable("id") id: String
    ): ResponseEntity<Vehicle>{
        return vehiclesService.vehicleById(id)
    }

    // update vehicle
    @PutMapping("/{id}")
    fun updateVehicle(
        @PathVariable("id") id: String,
        @RequestBody vehicleDto: VehicleDto
    ): ResponseEntity<Vehicle>{
        return vehiclesService.updateVehicle(id, vehicleDto)
    }

    // search vehicles
    @GetMapping("/search")
    fun searchVehicles(
        @RequestParam(name = "query") query: String
    ): ResponseEntity<List<Vehicle>>{
        return vehiclesService.searchVehicles(query)
    }




    // delete vehicle
    @DeleteMapping("/{id}")
    fun deleteVehicle(
        @PathVariable("id") id: String
    ): ResponseEntity<String>{
        return vehiclesService.deleteVehicle(id)
    }

}