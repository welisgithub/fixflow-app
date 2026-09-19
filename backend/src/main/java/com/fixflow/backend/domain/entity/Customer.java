package com.fixflow.backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "tb_customers")
@Getter
@Setter
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id
    @GeneratedValue(

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "cpf_cnpj", nullable = false, unique = true, length = 20)
    private String cpfCnpj;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }
}

