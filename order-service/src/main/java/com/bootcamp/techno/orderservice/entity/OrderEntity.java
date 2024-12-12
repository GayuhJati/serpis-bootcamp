package com.bootcamp.techno.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@Entity
@Table(name = "tb_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private Long idUser;

    @Column(nullable = false)
    private Long idProduct;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate = Instant.now();

    @LastModifiedDate
    private Instant lastModifiedDate = Instant.now();

}
