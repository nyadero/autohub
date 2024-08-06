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
    System.setProperty("SPRING_DATASOURCE_USERNAME", Objects.requireNonNull(dotEnv.get("SPRING_DATASOURCE_USERNAME")))
    System.setProperty("SPRING_DATASOURCE_PASSWORD", dotEnv.get("SPRING_DATASOURCE_PASSWORD"))
    System.setProperty("SPRING_DATASOURCE_DRIVER_CLASS_NAME", dotEnv.get("SPRING_DATASOURCE_DRIVER_CLASS_NAME"))

    // security
    System.setProperty("JWT_SECRET_KEY", Objects.requireNonNull(dotEnv.get("JWT_SECRET_KEY")))
    System.setProperty("JWT_EXPIRATION", Objects.requireNonNull(dotEnv.get("JWT_EXPIRATION")))
    System.setProperty("JWT_REFRESH_TOKEN_EXPIRATION", Objects.requireNonNull("JWT_REFRESH_TOKEN_EXPIRATION"))
    runApplication<AutohubApplication>(*args)

}
