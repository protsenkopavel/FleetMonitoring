package net.protsenko.realtimesimulatorservice.listener;

import lombok.extern.slf4j.Slf4j;
import net.protsenko.datageneratorservice.event.VehicleCreatedEvent;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@KafkaListener(topics = "vehicle-created-events-topic", groupId = "vehicle-created-events")
public class VehicleCreatedEventHandler {

    @Transactional
    @KafkaHandler
    public void handle(@Payload VehicleCreatedEvent vehicleCreatedEvent,
                       @Header("messageId") String messageId,
                       @Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {
        log.info("Received event: {}", vehicleCreatedEvent.vehicleData().getClass());
        log.info("Message id: {}", messageId);
        log.info("Message key: {}", messageKey);
    }
}
