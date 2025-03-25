package com.example.samplefundtransferservice.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "fund_transfer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundTransfer {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "customer_id", nullable = false)
    private long customerId;

    @Column(name = "to_account", nullable = false)
    private long fromAccount;

    @Column(name = "fron_account", nullable = false)
    private long toAccount;

    private double amount;

    private String description;
}
