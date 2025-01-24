package com.mjp.config_server.services;

import com.mjp.config_server.configs.SqsConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.Map;

@Component
public class AwsResourceInitializer {

    private final SqsClient sqsClient;
    private final SnsClient snsClient;
    private final SqsConfig sqsConfig;

    @Value("${aws.sns.topic-name}")
    private String topicName;

    public AwsResourceInitializer(SqsClient sqsClient, SnsClient snsClient, SqsConfig sqsConfig) {
        this.sqsClient = sqsClient;
        this.snsClient = snsClient;
        this.sqsConfig = sqsConfig;
    }

    @PostConstruct
    public void initializeAwsResources() {
        CreateTopicResponse topicResponse = snsClient.createTopic(CreateTopicRequest.builder()
                .name(topicName)
                .build());
        String topicArn = topicResponse.topicArn();

        sqsConfig.getQueues().values().forEach((queueName -> {
            try {
                CreateQueueResponse queueResponse = sqsClient.createQueue(CreateQueueRequest.builder().queueName(queueName).build());
                String queueUrl = queueResponse.queueUrl();

                GetQueueAttributesResponse queueAttributes = sqsClient.getQueueAttributes(GetQueueAttributesRequest.builder()
                        .queueUrl(queueUrl)
                        .attributeNames(QueueAttributeName.QUEUE_ARN)
                        .build());
                String queueArn = queueAttributes.attributes().get(QueueAttributeName.QUEUE_ARN);

                Map<QueueAttributeName, String> attributes = getQueueAttributeNameStringMap(queueArn, topicArn);
                sqsClient.setQueueAttributes(SetQueueAttributesRequest.builder()
                        .queueUrl(queueUrl)
                        .attributes(attributes)
                        .build());

                snsClient.subscribe(SubscribeRequest.builder()
                        .protocol("sqs")
                        .endpoint(queueArn)
                        .topicArn(topicArn)
                        .build());
            } catch (Exception e) {
                throw new RuntimeException("Erro ao configurar a fila '" + queueName + "'", e);
            }
        }));
    }

    private static Map<QueueAttributeName, String> getQueueAttributeNameStringMap(String queueArn, String topicArn) {
        String policy = """
                    {
                        "Version": "2012-10-17",
                        "Statement": [
                            {
                                "Effect": "Allow",
                                "Principal": "*",
                                "Action": "SQS:SendMessage",
                                "Resource": "%s",
                                "Condition": {
                                    "ArnEquals": {
                                        "aws:SourceArn": "%s"
                                    }
                                }
                            }
                        ]
                    }
                """.formatted(queueArn, topicArn);

        return Map.of(QueueAttributeName.POLICY, policy);

    }
}
