package net.protsenko.datageneratorservice.config;

import net.protsenko.core.event.VehicleCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Autowired
    Environment environment;

    Map<String, Object> producerConfigs() {
        Map<String, Object> configs = new HashMap<>();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                environment.getProperty("spring.kafka.producer.bootstrap-servers"));
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                environment.getProperty("spring.kafka.producer.key-serializer"));
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                environment.getProperty("spring.kafka.producer.value-serializer"));
        configs.put(ProducerConfig.ACKS_CONFIG,
                environment.getProperty("spring.kafka.producer.acks"));
        configs.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,
                environment.getProperty("spring.kafka.producer.properties.delivery.timeout.ms"));
        configs.put(ProducerConfig.LINGER_MS_CONFIG,
                environment.getProperty("spring.kafka.producer.properties.linger.ms"));
        configs.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,
                environment.getProperty("spring.kafka.producer.properties.request.timeout.ms"));
        configs.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
                environment.getProperty("spring.kafka.producer.properties.enable.idempotence"));
        configs.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,
                environment.getProperty("spring.kafka.producer.properties.max.in.flight.requests.per.connection"));

        return configs;
    }

    @Bean
    ProducerFactory<String, VehicleCreatedEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    KafkaTemplate<String, VehicleCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    NewTopic createTopic() {
        return TopicBuilder
                .name("vehicle-created-events-topic")
                .partitions(1)
                .replicas(1)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}