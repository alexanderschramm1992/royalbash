package de.schramm.royalbash;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"de.schramm.royalbash"})
public class ServerApplication {

    public static final Logger logger = LogManager.getLogger(ServerApplication.class);

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ServerApplication.class, args);
    }
}
