package com.mjp.config_server.services;

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

    @Value("${aws.sqs.queue-name}")
    private String queueName;

    @Value("${aws.sns.topic-name}")
    private String topicName;

    public AwsResourceInitializer(SqsClient sqsClient, SnsClient snsClient) {
        this.sqsClient = sqsClient;
        this.snsClient = snsClient;
    }

    @PostConstruct
    public void initializeAwsResources() {
        // Criar tópico SNS
        CreateTopicResponse topicResponse = snsClient.createTopic(CreateTopicRequest.builder()
                .name(topicName)
                .build());
        String topicArn = topicResponse.topicArn();

        // Criar fila SQS
        CreateQueueResponse queueResponse = sqsClient.createQueue(CreateQueueRequest.builder()
                .queueName(queueName)
                .build());
        String queueUrl = queueResponse.queueUrl();

        // Obter ARN da fila SQS
        GetQueueAttributesResponse queueAttributes = sqsClient.getQueueAttributes(GetQueueAttributesRequest.builder()
                .queueUrl(queueUrl)
                .attributeNames(QueueAttributeName.QUEUE_ARN)
                .build());
        String queueArn = queueAttributes.attributes().get(QueueAttributeName.QUEUE_ARN);

        // Configurar permissões para o tópico SNS publicar na fila SQS
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

        Map<QueueAttributeName, String> attributes = Map.of(
                QueueAttributeName.POLICY, policy
        );

        sqsClient.setQueueAttributes(SetQueueAttributesRequest.builder()
                .queueUrl(queueUrl)
                .attributes(attributes)
                .build());

        // Associar o tópico SNS à fila SQS
        snsClient.subscribe(SubscribeRequest.builder()
                .protocol("sqs")
                .endpoint(queueArn)
                .topicArn(topicArn)
                .build());
    }
}
