package net.protsenko.dashboardservice.listener;

import lombok.extern.slf4j.Slf4j;
import net.protsenko.realtimesimulatorservice.event.CriticalDataEvent;
import net.protsenko.realtimesimulatorservice.event.VehicleTelemetryEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

    @KafkaListener(
            topics = "vehicle-telemetry-events-topic",
            containerFactory = "telemetryKafkaListenerContainerFactory"
    )
    public void consumeTelemetry(@Payload VehicleTelemetryEvent event,
                                 @Header("messageId") String messageId,
                                 @Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {
        log.info("Received event: {}", event);
        log.info("Message id: {}", messageId);
        log.info("Message key: {}", messageKey);
    }

    @KafkaListener(
            topics = "critical-data-events-topic",
            containerFactory = "criticalKafkaListenerContainerFactory"
    )
    public void consumeCriticalData(@Payload CriticalDataEvent event,
                                    @Header("messageId") String messageId,
                                    @Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {
        log.info("Received event: {}", event);
        log.info("Message id: {}", messageId);
        log.info("Message key: {}", messageKey);
    }

}
