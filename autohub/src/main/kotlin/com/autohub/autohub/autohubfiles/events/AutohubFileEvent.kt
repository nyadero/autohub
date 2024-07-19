package com.autohub.autohub.autohubfiles.events

import com.autohub.autohub.autohubfiles.enums.FileEventType
import com.autohub.autohub.vehicles.entity.Vehicle
import org.springframework.context.ApplicationEvent
import org.springframework.web.multipart.MultipartFile

class AutohubFileEvent(
    val vehicle: Vehicle,
    val files: List<MultipartFile>?,
    val fileEventType: FileEventType
) : ApplicationEvent(vehicle)