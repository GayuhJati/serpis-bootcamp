package com.techno.bootcamp.productservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "tb_product")
public class ProdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Long updatedBy;

    @Column(nullable = false)
    private Long createdBy;

    @Column(nullable = false)
    private Double price;
}
