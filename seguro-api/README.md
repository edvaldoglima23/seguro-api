# API de Seguros

API REST para gerenciamento de apólices de seguros utilizando tecnologias modernas do ecossistema Java.

## Tecnologias Utilizadas

- Java 11
- Spring Boot 2.7
- Spring MVC
- Spring Data JPA
- Spring Security
- Hibernate/JPA
- Oracle Database
- Mensageria com RabbitMQ
- Documentação com Swagger
- WildFly/JBoss (suporte)

## Funcionalidades

- CRUD completo de Apólices de Seguro
- Busca por diversos critérios (número da apólice, tipo de seguro, nome do segurado, CPF/CNPJ)
- Consulta de apólices vigentes
- Consulta de apólices a vencer nos próximos 30 dias
- Notificações via mensageria para eventos como nova apólice ou vencimento
- Autenticação e autorização via Spring Security
- Documentação completa da API via Swagger

## Pré-requisitos

- JDK 11+
- Maven 3.6+
- Oracle Database
- RabbitMQ (para funcionalidades de mensageria)

## Configuração e Execução

1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/seguro-api.git
cd seguro-api
```

2. Configure o banco de dados Oracle no arquivo `application.properties`

3. Execute a aplicação
```bash
mvn spring-boot:run
```

4. Acesse a documentação da API
```
http://localhost:8080/api/swagger-ui.html
```

## Estrutura do Projeto

- `model` - Entidades JPA
- `repository` - Repositórios Spring Data JPA
- `service` - Regras de negócio
- `controller` - Endpoints REST
- `config` - Configurações do Spring
- `dto` - Objetos de transferência de dados
- `exception` - Tratamento de exceções

## Exemplos de Uso

### Criar uma nova apólice

```bash
curl -X POST http://localhost:8080/api/apolices \
  -H 'Content-Type: application/json' \
  -d '{
    "numeroApolice": "AP123456",
    "tipoSeguro": "AUTO",
    "nomeSegurado": "João Silva",
    "documentoSegurado": "123.456.789-00",
    "dataInicioVigencia": "2023-01-01",
    "dataFimVigencia": "2024-01-01",
    "valorPremio": 1500.00,
    "valorCobertura": 50000.00
}'
```

### Buscar apólice por número

```bash
curl http://localhost:8080/api/apolices/numero/AP123456
``` 