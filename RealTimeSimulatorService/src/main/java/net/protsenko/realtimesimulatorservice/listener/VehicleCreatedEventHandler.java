package net.protsenko.realtimesimulatorservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.protsenko.core.event.VehicleCreatedEvent;
import net.protsenko.realtimesimulatorservice.entity.ProcessedEventEntity;
import net.protsenko.realtimesimulatorservice.repository.ProcessedEventRepository;
import net.protsenko.realtimesimulatorservice.service.VehicleSimulator;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(topics = "vehicle-created-events-topic", groupId = "vehicle-created-events")
public class VehicleCreatedEventHandler {

    private final ProcessedEventRepository processedEventRepository;
    private final VehicleSimulator vehicleSimulator;

    @Transactional
    @KafkaHandler
    public void handle(@Payload VehicleCreatedEvent vehicleCreatedEvent,
                       @Header("messageId") String messageId,
                       @Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {
        log.info("Received event: {}", vehicleCreatedEvent.vehicleData().getClass());
        log.info("Message id: {}", messageId);
        log.info("Message key: {}", messageKey);

        ProcessedEventEntity processedEventEntity = processedEventRepository.findByMessageId(messageId);

        if (processedEventEntity != null) {
            log.info("Duplicate messageId: {}", messageId);
            return;
        }

        processedEventRepository.save(new ProcessedEventEntity(messageId, vehicleCreatedEvent.vehicleId()));

        vehicleSimulator.addVehicle(vehicleCreatedEvent.vehicleId(), vehicleCreatedEvent.vehicleData());
    }
}
