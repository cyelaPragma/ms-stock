package com.acelera.ti.stock.infrastructure.drivenadapters.sqsAdapter.dto;

import lombok.Data;

@Data
public class TransactionSQS {
    private String id;
    private String name;
}
