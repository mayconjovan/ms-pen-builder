#!/bin/bash
set -e  # Para encerrar o script em caso de erro

# 🚀 Definir credenciais fake para LocalStack
export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_DEFAULT_REGION=us-east-1

# 🌍 Definir o endpoint do LocalStack
LOCALSTACK_ENDPOINT="http://localstack:4566"

# Executar os scripts de criação de segredos, tópicos SNS e filas SQS
echo "🚀 Iniciando configuração do LocalStack..."
./create-secrets.sh
./create-sns-topics.sh
./create-sqs-queues.sh

echo "🎉 Configuração do LocalStack concluída com sucesso!"
