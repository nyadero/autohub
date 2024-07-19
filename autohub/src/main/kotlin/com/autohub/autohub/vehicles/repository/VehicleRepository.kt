package com.autohub.autohub.vehicles.repository

import com.autohub.autohub.vehicles.entity.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository : JpaRepository<Vehicle, String> {
//    fun save(vehicle: Vehicle): Vehicle
//    fun findById(id: String): Vehicle?
//    fun delete(vehicle: Vehicle)
    fun findAllByNameContainingIgnoreCase(string: kotlin.String): kotlin.collections.MutableList<com.autohub.autohub.vehicles.entity.Vehicle>
}