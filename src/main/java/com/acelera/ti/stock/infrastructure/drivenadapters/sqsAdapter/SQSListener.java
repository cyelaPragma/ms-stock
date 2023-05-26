package com.acelera.ti.stock.infrastructure.drivenadapters.sqsAdapter;

import com.acelera.ti.stock.infrastructure.drivenadapters.sqsAdapter.config.SQSProperties;
import com.acelera.ti.stock.infrastructure.drivenadapters.sqsAdapter.dto.TransactionSQS;
import com.acelera.ti.stock.infrastructure.helpers.sqs.JsonHelperService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.ArrayList;
import java.util.List;

@Builder
@Slf4j
public class SQSListener {
    private final SqsClient client;
    private final SQSProperties properties;
    private final JsonHelperService jsonHelperService;

    public List<TransactionSQS> getMessages() {
        List<Message> messages = client.receiveMessage(getReceiveMessageRequest()).messages();
        List<TransactionSQS> response = new ArrayList<>();
        for (Message message:messages) {
            response.add(jsonHelperService.jsonStringToObject(message.body(), TransactionSQS.class));
            confirmReceive(message);
        }
        return response;
    }

    private void confirmReceive(Message message) {
        DeleteMessageRequest deleteMessageRequest = getDeleteMessageRequest(message.receiptHandle());
        client.deleteMessage(deleteMessageRequest);
    }

    private ReceiveMessageRequest getReceiveMessageRequest() {
        return ReceiveMessageRequest.builder()
                .queueUrl(properties.getQueueUrl())
                .maxNumberOfMessages(properties.getMaxNumberOfMessages())
                .waitTimeSeconds(properties.getWaitTimeSeconds())
                .visibilityTimeout(properties.getVisibilityTimeout())
                .messageAttributeNames("*")
                .build();
    }

    private DeleteMessageRequest getDeleteMessageRequest(String receiptHandle) {
        log.info("Deleted message {}", receiptHandle);
        return DeleteMessageRequest.builder()
                .queueUrl(properties.getQueueUrl())
                .receiptHandle(receiptHandle)
                .build();
    }
}
