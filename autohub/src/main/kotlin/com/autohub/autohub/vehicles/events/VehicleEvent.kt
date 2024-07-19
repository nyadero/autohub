package com.autohub.autohub.vehicles.events

import com.autohub.autohub.vehicles.entity.Vehicle
import org.springframework.context.ApplicationEvent

class VehicleEvent(
    private val vehicle: Vehicle,
     val name: String
) : ApplicationEvent(vehicle)