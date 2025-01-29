#!/bin/bash
set -e  # Para encerrar o script em caso de erro

# 🚀 Definir credenciais fake para LocalStack
export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_DEFAULT_REGION=us-east-1

# 🌍 Definir o endpoint do LocalStack
LOCALSTACK_ENDPOINT="http://localstack:4566"

echo "🚀 Iniciando criação de tópicos SNS..."

# Criar tópicos SNS
FACTORY_TOPIC_ARN=$(aws --endpoint-url=$LOCALSTACK_ENDPOINT sns create-topic --name factory-topic --query 'TopicArn' --output text)
PAYMENT_TOPIC_ARN=$(aws --endpoint-url=$LOCALSTACK_ENDPOINT sns create-topic --name notify-payment --query 'TopicArn' --output text)

if [ -z "$FACTORY_TOPIC_ARN" ] || [ -z "$PAYMENT_TOPIC_ARN" ]; then
    echo "❌ Erro ao criar tópicos SNS. Verifique o LocalStack."
    exit 1
fi

echo "✅ Tópicos SNS criados:"
echo "   - factory-topic: $FACTORY_TOPIC_ARN"
echo "   - notify-payment: $PAYMENT_TOPIC_ARN"
