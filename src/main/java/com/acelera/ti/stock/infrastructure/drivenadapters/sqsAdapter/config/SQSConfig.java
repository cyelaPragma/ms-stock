package com.acelera.ti.stock.infrastructure.drivenadapters.sqsAdapter.config;

import com.acelera.ti.stock.infrastructure.helpers.sqs.JsonHelperService;
import com.acelera.ti.stock.infrastructure.drivenadapters.sqsAdapter.SQSListener;
import com.acelera.ti.stock.infrastructure.drivenadapters.sqsAdapter.SQSSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.auth.credentials.ContainerCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@Configuration
public class SQSConfig {
    @Bean
    public SQSListener sqsListener(SqsClient client, SQSProperties properties, JsonHelperService jsonHelperService) {
        return SQSListener.builder()
                .client(client)
                .properties(properties)
                .jsonHelperService(jsonHelperService)
                .build();
    }

    @Bean
    public SQSSender sqsSender(SqsClient client, SQSProperties properties, JsonHelperService jsonHelperService) {
        return SQSSender.builder()
                .client(client)
                .properties(properties)
                .jsonHelperService(jsonHelperService)
                .build();
    }

    @Bean
    public SqsClient configSqs(SQSProperties properties) {
        return SqsClient.builder()
                .endpointOverride(resolveEndpoint(properties))
                .region(Region.of(properties.getRegion()))
                .credentialsProvider(getProviderChain())
                .build();
    }

    private AwsCredentialsProviderChain getProviderChain() {
        return AwsCredentialsProviderChain.builder()
                .addCredentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .addCredentialsProvider(SystemPropertyCredentialsProvider.create())
                .addCredentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
                .addCredentialsProvider(ProfileCredentialsProvider.create())
                .addCredentialsProvider(ContainerCredentialsProvider.builder().build())
                .addCredentialsProvider(InstanceProfileCredentialsProvider.create())
                .build();
    }

    private URI resolveEndpoint(SQSProperties properties) {
        if (properties.getEndpoint() != null) {
            return URI.create(properties.getEndpoint());
        }
        return null;
    }
}
