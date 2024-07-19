package com.autohub.autohub

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport
import java.util.Objects

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
class AutohubApplication

fun main(args: Array<String>) {
    val dotEnv: Dotenv = Dotenv.load()
    // datasource
    System.setProperty("SPRING_DATASOURCE_URL", Objects.requireNonNull(dotEnv.get("SPRING_DATASOURCE_URL")))
    System.setProperty("SPRING_DATASOURCE_USERNAME", dotEnv.get("SPRING_DATASOURCE_USERNAME"))
    System.setProperty("SPRING_DATASOURCE_PASSWORD", dotEnv.get("SPRING_DATASOURCE_PASSWORD"))
    System.setProperty("SPRING_DATASOURCE_DRIVER_CLASS_NAME", dotEnv.get("SPRING_DATASOURCE_DRIVER_CLASS_NAME"))

    runApplication<AutohubApplication>(*args)

}
