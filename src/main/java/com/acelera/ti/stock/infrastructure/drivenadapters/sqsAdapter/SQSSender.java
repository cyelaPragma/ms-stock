package com.acelera.ti.stock.infrastructure.drivenadapters.sqsAdapter;

import com.acelera.ti.stock.infrastructure.drivenadapters.sqsAdapter.config.SQSProperties;
import com.acelera.ti.stock.infrastructure.drivenadapters.sqsAdapter.dto.TransactionSQS;
import com.acelera.ti.stock.infrastructure.helpers.sqs.JsonHelperService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Builder
@Slf4j
public class SQSSender {
    private final SqsClient client;
    private final SQSProperties properties;
    private final JsonHelperService jsonHelperService;

    public void sendMessage (TransactionSQS message){
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(properties.getQueueUrl())
                .messageBody(jsonHelperService.writeValueAsString(message))
                .build();

        client.sendMessage(request);
    }
}
