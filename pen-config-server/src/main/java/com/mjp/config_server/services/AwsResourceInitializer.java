package com.mjp.config_server.services;

import com.mjp.config_server.configs.SqsConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.CreateSecretRequest;
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
    private final S3Client s3Client;
    private final SecretsManagerClient secretsManagerClient;

    @Value("${s3.bucket-name}")
    private String bucketName;

    @Value("${aws.sns.topic-name}")
    private String topicName;

    public AwsResourceInitializer(SqsClient sqsClient, SnsClient snsClient, SqsConfig sqsConfig, S3Client s3Client, SecretsManagerClient secretsManagerClient) {
        this.sqsClient = sqsClient;
        this.snsClient = snsClient;
        this.sqsConfig = sqsConfig;
        this.s3Client = s3Client;
        this.secretsManagerClient = secretsManagerClient;
    }

    @PostConstruct
    public void initializeAwsResources() {
        String topicArn = initializeSnsTopic();
        initializeSqsQueues(topicArn);
//        initializeS3Bucket();
//        initializeSecretsManager();
//        initializeApiGateway();




    }

    private void initializeSecretsManager() {
        String secretName = "dbPassword";
        String secretValue = "123456";

        secretsManagerClient.createSecret(CreateSecretRequest.builder()
                .name(secretName)
                .secretString(secretValue)
                .build());
    }

    private void initializeS3Bucket() {


        s3Client.createBucket(
                CreateBucketRequest
                        .builder()
                        .bucket(bucketName)
                        .build());
    }

    private void initializeSqsQueues(String topicArn) {
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

    private String initializeSnsTopic() {
        CreateTopicResponse topicResponse = snsClient.createTopic(CreateTopicRequest.builder()
                .name(topicName)
                .build());
        return topicResponse.topicArn();
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
