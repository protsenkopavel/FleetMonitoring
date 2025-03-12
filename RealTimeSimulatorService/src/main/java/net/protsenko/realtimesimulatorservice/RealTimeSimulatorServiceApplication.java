package net.protsenko.realtimesimulatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RealTimeSimulatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealTimeSimulatorServiceApplication.class, args);
    }

}
