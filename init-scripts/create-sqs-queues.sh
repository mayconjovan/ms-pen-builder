#!/bin/bash
set -e  # Para encerrar o script em caso de erro

# 🚀 Definir credenciais fake para LocalStack
export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_DEFAULT_REGION=us-east-1

# 🌍 Definir o endpoint do LocalStack
LOCALSTACK_ENDPOINT="http://localstack:4566"

echo "🚀 Iniciando criação de filas SQS..."

# Defina explicitamente os ARNs para os tópicos SNS
FACTORY_TOPIC_ARN="arn:aws:sns:us-east-1:000000000000:factory-topic"
PAYMENT_TOPIC_ARN="arn:aws:sns:us-east-1:000000000000:payment-topic"

# Verificar se os ARNs estão definidos corretamente
if [[ -z "$FACTORY_TOPIC_ARN" || -z "$PAYMENT_TOPIC_ARN" ]]; then
    echo "❌ Faltando variáveis de ARN para os tópicos SNS. Verifique se os tópicos foram criados corretamente."
    exit 1
fi

# Mapear tópicos SNS para suas filas SQS
declare -A TOPIC_TO_QUEUES
TOPIC_TO_QUEUES["$FACTORY_TOPIC_ARN"]="factory"
TOPIC_TO_QUEUES["$PAYMENT_TOPIC_ARN"]="payment-notification"


# Criar filas SQS e associá-las aos tópicos SNS
for TOPIC_ARN in "${!TOPIC_TO_QUEUES[@]}"; do
    for QUEUE_NAME in ${TOPIC_TO_QUEUES[$TOPIC_ARN]}; do
        QUEUE_URL=$(aws --endpoint-url=$LOCALSTACK_ENDPOINT sqs create-queue --queue-name "$QUEUE_NAME" --query 'QueueUrl' --output text)
        QUEUE_ARN=$(aws --endpoint-url=$LOCALSTACK_ENDPOINT sqs get-queue-attributes --queue-url "$QUEUE_URL" --attribute-names QueueArn --query 'Attributes.QueueArn' --output text)

        # Definir permissões para a fila permitir mensagens do SNS
        POLICY=$(printf '{
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
        }' "$QUEUE_ARN" "$TOPIC_ARN")

        POLICY_CLEANED=$(echo "$POLICY" | sed 's/"/\\"/g' | tr -d '\n' | tr -d ' ')

        aws --endpoint-url=$LOCALSTACK_ENDPOINT sqs set-queue-attributes --queue-url "$QUEUE_URL" --attributes "{\"Policy\":\"$POLICY_CLEANED\"}"

        # Associar fila ao tópico SNS
        aws --endpoint-url=$LOCALSTACK_ENDPOINT sns subscribe --topic-arn "$TOPIC_ARN" --protocol sqs --notification-endpoint "$QUEUE_ARN"

        echo "✅ Fila SQS '$QUEUE_NAME' criada e associada ao tópico SNS."
    done
done

# Criar a fila simples "payment-validator" (sem associar a nenhum tópico)
VALIDATOR_QUEUE_URL=$(aws --endpoint-url=$LOCALSTACK_ENDPOINT sqs create-queue --queue-name "payment-validator" --query 'QueueUrl' --output text)
VALIDATOR_QUEUE_ARN=$(aws --endpoint-url=$LOCALSTACK_ENDPOINT sqs get-queue-attributes --queue-url "$VALIDATOR_QUEUE_URL" --attribute-names QueueArn --query 'Attributes.QueueArn' --output text)

echo "🎉 Configuração de filas SQS concluída com sucesso!"
