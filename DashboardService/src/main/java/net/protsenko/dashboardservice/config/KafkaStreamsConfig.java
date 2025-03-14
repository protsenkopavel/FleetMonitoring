package net.protsenko.dashboardservice.config;

import net.protsenko.realtimesimulatorservice.event.VehicleTelemetryEvent;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;

@Component
@EnableKafkaStreams
public class KafkaStreamsConfig {

    @Bean
    public KStream<String, VehicleTelemetryEvent> telemetryStream(StreamsBuilder builder) {
        return builder.stream("vehicle-telemetry-events-topic");
    }

}

