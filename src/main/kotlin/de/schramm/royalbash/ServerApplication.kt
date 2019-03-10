package de.schramm.royalbash

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ServerApplication {
    companion object {
        @JvmStatic
        val logger: Logger = LoggerFactory.getLogger(ServerApplication::class.java)
    }
}

fun main(args: Array<String>) {
    org.springframework.boot.SpringApplication.run(ServerApplication::class.java, *args)
}
