package net.protsenko.datageneratorservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.protsenko.core.event.VehicleCreatedEvent;
import net.protsenko.core.model.VehicleData;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerImpl implements Producer {

    public final KafkaTemplate<String, VehicleCreatedEvent> kafkaTemplate;

    @Override
    public void send(VehicleData vehicleData) {
        String vehicleId = UUID.randomUUID().toString();

        VehicleCreatedEvent vehicleCreatedEvent = new VehicleCreatedEvent(
                vehicleId,
                vehicleData
        );

        ProducerRecord<String, VehicleCreatedEvent> record = new ProducerRecord<>(
                "vehicle-created-events-topic",
                vehicleId,
                vehicleCreatedEvent
        );
        record.headers().add("messageId", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));

        CompletableFuture<SendResult<String, VehicleCreatedEvent>> future =
                kafkaTemplate.send(record);

        future.whenComplete((result, exception) -> {
            if (exception != null) {
                log.error("Failed to send message: {}", exception.getMessage());
            } else {
                log.info("Message sent successfully: {}", result.getRecordMetadata());
            }
        });

    }

}
