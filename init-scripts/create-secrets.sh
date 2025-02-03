#!/bin/bash
set -e  # Para encerrar o script em caso de erro

# 🚀 Definir credenciais fake para LocalStack
export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_DEFAULT_REGION=us-east-1

# 🌍 Definir o endpoint do LocalStack
LOCALSTACK_ENDPOINT="http://localstack:4566"

echo "🚀 Iniciando criação de segredos no LocalStack..."

# Criar segredos
SECRET_NAME="db-factory-credentials"
SECRET_VALUE=$(printf '{"url":"jdbc:postgresql://localhost:5432/", "username":"postgres","password":"123456","host":"factory-db","port":"5432","dbname":"pen-factory"}')

aws --endpoint-url=$LOCALSTACK_ENDPOINT secretsmanager create-secret \
  --name "$SECRET_NAME" \
  --secret-string "$SECRET_VALUE"

echo "✅ Segredo '$SECRET_NAME' criado."

SECRET_NAME_SECONDARY="db-orders-credentials"
SECRET_VALUE_SECONDARY=$(printf '{"url":"jdbc:postgresql://localhost:5432/", "username":"postgres","password":"123456","host":"localhost","port":"5432","dbname":"pen-processor-order"}')

aws --endpoint-url=$LOCALSTACK_ENDPOINT secretsmanager create-secret \
  --name "$SECRET_NAME_SECONDARY" \
  --secret-string "$SECRET_VALUE_SECONDARY"

echo "✅ Segredo '$SECRET_NAME_SECONDARY' criado."
