package com.emazon.transactions.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "supply")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private Long articleId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Long performedByUserId;

}
