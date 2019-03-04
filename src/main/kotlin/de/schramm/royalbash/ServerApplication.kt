package de.schramm.royalbash

import org.apache.logging.log4j.LogManager
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class ServerApplication {

    val logger = LogManager.getLogger(ServerApplication::class.java)
}

fun main(args: Array<String>) {
    org.springframework.boot.SpringApplication.run(ServerApplication::class.java, *args)
}
