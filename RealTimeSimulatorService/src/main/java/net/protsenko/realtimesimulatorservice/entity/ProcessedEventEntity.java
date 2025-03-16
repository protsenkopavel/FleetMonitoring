package net.protsenko.realtimesimulatorservice.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@NoArgsConstructor
@Table(name = "processed_events")
public class ProcessedEventEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String messageId;

    @Column(nullable = false)
    private String vehicleId;

    public ProcessedEventEntity(String messageId, String vehicleId) {
        this.messageId = messageId;
        this.vehicleId = vehicleId;
    }

}
