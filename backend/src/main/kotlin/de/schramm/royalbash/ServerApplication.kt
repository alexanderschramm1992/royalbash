package de.schramm.royalbash

import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ServerApplication

fun main(args: Array<String>) {
    org.springframework.boot.SpringApplication.run(ServerApplication::class.java, *args)
}
