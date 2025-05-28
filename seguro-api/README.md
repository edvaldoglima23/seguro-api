# API de Seguros

![Java](https://img.shields.io/badge/Java-11-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-brightgreen)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-blue)
![Oracle](https://img.shields.io/badge/Oracle-Database-red)
![WildFly](https://img.shields.io/badge/WildFly-26-orange)
![Swagger](https://img.shields.io/badge/Swagger-API%20Docs-green)

API REST para gerenciamento de apólices de seguros desenvolvida utilizando tecnologias modernas do ecossistema Java. Esta aplicação demonstra proficiência nas tecnologias solicitadas para posições de desenvolvimento backend Java.

## 🚀 Tecnologias Utilizadas

- **Backend**:
  - Java 11
  - Spring Boot 2.7
  - Spring MVC para API REST
  - Spring Data JPA/Hibernate
  - Spring Security para autenticação e autorização
  - JBoss/WildFly como servidor de aplicação
  - Oracle Database com suporte a PL/SQL
  - RabbitMQ para mensageria

- **Documentação e Testes**:
  - Swagger/OpenAPI para documentação da API
  - Validação com Bean Validation
  - Logs estruturados

## 🔥 Funcionalidades Principais

- CRUD completo de Apólices de Seguro
- Busca por diversos critérios (número da apólice, tipo de seguro, nome do segurado, CPF/CNPJ)
- Consulta de apólices vigentes
- Consulta de apólices a vencer nos próximos 30 dias
- Notificações via mensageria para eventos como nova apólice ou vencimento
- Autenticação e autorização via Spring Security
- Integração com procedures PL/SQL do Oracle

## 📦 Estrutura do Projeto

A aplicação segue uma arquitetura em camadas bem definida:

```
seguro-api/
├── src/main/java/com/example/seguro/
│   ├── config/         # Configurações do Spring, Security, RabbitMQ e Swagger
│   ├── controller/     # Endpoints REST da API
│   ├── dto/            # Objetos de transferência de dados
│   ├── model/          # Entidades JPA
│   ├── repository/     # Repositórios Spring Data JPA
│   ├── service/        # Lógica de negócio
│   └── SeguroApiApplication.java  # Classe principal da aplicação
└── src/main/resources/
    └── application.properties  # Configurações da aplicação
```

## 📋 Padrões e Boas Práticas

- **Design Patterns**: Repository, DTO, Service Layer
- **Clean Code**: Código legível e bem estruturado
- **RESTful API**: Seguindo as melhores práticas REST
- **Exception Handling**: Tratamento adequado de exceções
- **Validação**: Validação de entrada de dados
- **Segurança**: Implementação de controle de acesso

## 🛠️ Como Executar

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

## 🔍 Exemplos de Uso

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

## 📈 Diferenciais do Projeto

- **Integração com PL/SQL**: Demonstração de conhecimento em Oracle PL/SQL
- **Mensageria**: Implementação de comunicação assíncrona com RabbitMQ
- **Documentação**: API completamente documentada com Swagger
- **Segurança**: Implementação de autenticação e autorização
- **Código limpo e organizado**: Seguindo as melhores práticas de desenvolvimento

## 🔗 Contato

Para mais informações, entre em contato pelo email: edvaldoglima23@gmail.com 