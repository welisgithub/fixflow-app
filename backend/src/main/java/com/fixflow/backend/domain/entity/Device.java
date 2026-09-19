package com.fixflow.backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String imei;

    @Column(nullable = false, length = 50)
    private String brand;

    @Column(name = "commercial_model", nullable = false, length = 100)
    private String commercialModel;

    @Column(name = "technical_model", length = 100)
    private String technicalModel;

    @Column(name = "pre_existing_defects", columnDefinition = "TEXT")
    private String preExistingDefects;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
