package com.integration.bosta.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tracking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingNumber;

    private String carrier;

    private String status;
    
    private LocalDateTime expectedDelivery;

    private String lastCheckpointLocation;

    private String lastCheckpointMessage;

    private LocalDateTime lastCheckpointTime;
}
