package com.example.samplefundtransferservice.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundTransferDTO {

    private long id;

    @NotNull(message = "customerId should not be null.")
    private long customerId;

    @NotNull(message = "FromAccount number should not be null.")
    private long fromAccount;

    @NotNull(message = "ToAccount number should not be null.")
    private long toAccount;

    @Positive(message = "Transfer amount should be positive.")
    @Max(value = 20000, message = "Transfer amount should not be greater than 20000.")
    private double amount;

    private String description;
}
