# API Customer ERP

> Microsserviço de gestão de clientes focado em **boas práticas de backend Java**, com REST API, validação de dados, persistência em PostgreSQL, documentação OpenAPI e mensageria assíncrona com RabbitMQ.

## 🎯 Objetivo do projeto

Este repositório foi estruturado para demonstrar competências técnicas valorizadas em entrevistas para backend:

- modelagem de API REST com versionamento (`/api/v1`)
- separação em camadas (`controller`, `service`, `repository`, `dto`, `entity`)
- validação de dados e tratamento global de exceções
- persistência com Spring Data JPA + PostgreSQL
- integração assíncrona com RabbitMQ
- documentação de endpoints com Swagger/OpenAPI

---

## 🧰 Stack técnica

- **Java 17**
- **Spring Boot 3.3.3**
- **Spring Web**
- **Spring Data JPA**
- **Bean Validation**
- **PostgreSQL**
- **RabbitMQ (AMQP)**
- **Springdoc OpenAPI (Swagger UI)**
- **Maven**

Dependências em: `customer_api/pom.xml`.

---

## 🏗️ Arquitetura e organização

```text
customer_api/
 └─ src/main/java/com/customer/customer_api/
    ├─ controller/        # Endpoints REST
    ├─ service/           # Regras de negócio
    ├─ repository/        # Acesso a dados (JPA)
    ├─ entity/            # Entidades persistidas
    ├─ dto/               # Contratos de entrada/saída
    ├─ exception/         # Tratamento global de erros
    ├─ messaging/         # Producer/consumer RabbitMQ
    ├─ config/rabbitmq/   # Configuração de filas/exchanges/bindings
    └─ convert/           # Conversores de modelo
```

---

## 🚀 Como executar localmente

### Pré-requisitos

- JDK 17+
- Maven 3.9+
- PostgreSQL
- RabbitMQ

### 1) Configurar variáveis em `application.properties`

Ajuste credenciais e hosts de banco/mensageria conforme seu ambiente.

### 2) Executar a aplicação

```bash
cd customer_api
./mvnw spring-boot:run
```

A API sobe, por padrão, em `http://localhost:8080`.

### 3) Acessar documentação interativa

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

---

## 📌 Endpoints principais

Base path: `api/v1/customers`

- `POST /api/v1/customers` — cria cliente
- `GET /api/v1/customers/{customerId}` — busca por ID
- `PUT /api/v1/customers/{customerId}` — atualiza cliente
- `DELETE /api/v1/customers/{customerId}` — remove cliente

---

## ✅ Qualidade e próximos passos

### Pontos fortes já presentes

- design em camadas para facilitar manutenção
- contrato de API com DTOs de request/response
- exceções de negócio dedicadas
- preparo para integração orientada a eventos

### Melhorias recomendadas para evolução

- ampliar testes unitários e de integração
- adicionar containerização com Docker Compose (app + postgres + rabbit)
- incluir pipeline CI (build, test, lint)
- instrumentar métricas de negócio e tracing distribuído
- criar coleção Postman e exemplos de payload
