#!/bin/bash
set -e  # Para sair imediatamente se algum comando falhar


# 🚀 Definir credenciais fake para LocalStack
export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_DEFAULT_REGION=us-east-1

# Variáveis do LocalStack
AWS_ENDPOINT="http://localstack:4566"
AWS_REGION="us-east-1"

# Lista de workers
SERVER_LIST=(
    "factory-ball"
    "factory-ball-support"
    "factory-ball-support-coupler-ink-tube"
    "factory-external-tube"
    "factory-ink"
    "factory-internal-tube"
    "factory-outer-tube-cover"
    "factory-tip-cap"
)

# Criando os parâmetros dinamicamente
PORT=8000

for SERVER in "${SERVER_LIST[@]}"; do
    echo "🚀 Criando parâmetros para $SERVER..."

    aws ssm put-parameter --endpoint-url "$AWS_ENDPOINT" --region "$AWS_REGION" --name "$SERVER/name" \
        --value "$SERVER" --type "String" --overwrite

    aws ssm put-parameter --endpoint-url "$AWS_ENDPOINT" --region "$AWS_REGION" --name "$SERVER/port" \
        --value "$PORT" --type "String" --overwrite

    aws ssm put-parameter --endpoint-url "$AWS_ENDPOINT" --region "$AWS_REGION" --name "$SERVER/delay-millis" \
        --value "200" --type "String" --overwrite

    ((PORT++))  # Incrementa a porta para o próximo serviço
done

echo "✅ Todos os parâmetros foram adicionados com sucesso ao Parameter Store!"
