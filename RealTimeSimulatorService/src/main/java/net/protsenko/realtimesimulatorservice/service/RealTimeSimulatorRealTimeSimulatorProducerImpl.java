package net.protsenko.realtimesimulatorservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.protsenko.datageneratorservice.model.VehicleData;
import net.protsenko.realtimesimulatorservice.event.CriticalDataEvent;
import net.protsenko.realtimesimulatorservice.event.VehicleTelemetryEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RealTimeSimulatorRealTimeSimulatorProducerImpl implements RealTimeSimulatorProducer {

    private final KafkaTemplate<String, VehicleTelemetryEvent> vehicleTelemetryEventKafkaTemplate;
    private final KafkaTemplate<String, CriticalDataEvent> criticalDataEventKafkaTemplate;

    @Override
    public void sendTelemetry(String vehicleId, VehicleData vehicleData) {
        VehicleTelemetryEvent vehicleTelemetryEvent = new VehicleTelemetryEvent(
                vehicleData.getLatitude(),
                vehicleData.getLongitude(),
                vehicleData.getSpeed(),
                vehicleData.getFuelLevel(),
                vehicleData.getEngineTemperature(),
                vehicleData.getMileage(),
                vehicleData.getEngineRuntime(),
                vehicleData.getDoorStatus()
        );

        ProducerRecord<String, VehicleTelemetryEvent> record = new ProducerRecord<>(
                "vehicle-telemetry-events-topic",
                vehicleId,
                vehicleTelemetryEvent
        );
        record.headers().add("messageId", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));

        SendResult<String, VehicleTelemetryEvent> result = null;
        try {
            result = vehicleTelemetryEventKafkaTemplate
                    .send(record).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        log.info("Topic: {}", result.getRecordMetadata().topic());
        log.info("Partition: {}", result.getRecordMetadata().partition());
        log.info("Offset: {}", result.getRecordMetadata().offset());
    }

    @Override
    public void sendCriticalData(String vehicleId, VehicleData vehicleData) {
        CriticalDataEvent criticalDataEvent = new CriticalDataEvent(
                vehicleData.getTirePressure(),
                vehicleData.isAirbagActivated()
        );

        ProducerRecord<String, CriticalDataEvent> record = new ProducerRecord<>(
                "critical-data-events-topic",
                vehicleId,
                criticalDataEvent
        );
        record.headers().add("messageId", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));

        SendResult<String, CriticalDataEvent> result = null;
        try {
            result = criticalDataEventKafkaTemplate
                    .send(record).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        log.info("Topic: {}", result.getRecordMetadata().topic());
        log.info("Partition: {}", result.getRecordMetadata().partition());
        log.info("Offset: {}", result.getRecordMetadata().offset());
    }

}
