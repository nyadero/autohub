package com.autohub.autohub.vehicles.eventlisteners

import com.autohub.autohub.vehicles.events.VehicleEvent
import com.autohub.autohub.vehicles.repository.VehicleRepository
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class VehicleEventListener(
    private val vehicleRepository: VehicleRepository
): ApplicationListener<VehicleEvent> {
    override fun onApplicationEvent(event: VehicleEvent) {
        print(event.name)
    }
}