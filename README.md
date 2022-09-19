# Projeto integrado em arquitetura de software

## Projeto desenvolvido como requisito para o curso de Arquitetura de Software Distribuído da PUC Minas
---
### Introdução

O projeto foi construído como requisito para o curso de Arquitetura de Sogtware Distribuído da PUC Minas, e tem por objetivo apresentar e validar uma hipótese arquitetural para a contrução de uma solução que utilize microserviços e persistência relacional e não relacional, além de validar alguns casos de uso.

Foram construídos ao todo 7 microserviços, uma aplicação web, a persistência foi realizada com postgreSQL e mongoDB, e a comunicação de mensageria utilizou o RabbitMQ.

---
### Requisitos para a execução do projeto

Para executar o projeto é necessário seguir os passos a seguir.
Caso você já possua o docker e docker-compose instalado, pule o primeiro passo.

1. Instale o docker e docker-compose
- [Docker](https://docs.docker.com/install/)
- [Docker Compose](https://docs.docker.com/compose/install/)

    Opicionalmente:

- [Compass para MongoDB](https://www.mongodb.com/products/compass)

2. Crie um arquivo ***.env*** na raiz do projeto, com a estrutura de váriávies a seguir:
```docker
MONGO_URL_CONNECTION=
MONGO_DATABASE_NAME=agrow
MONGO_USER=
MONGO_PASS=

PG_URL_CONNECTION=
PG_USER=
PG_PASS=
PG_HOST_AUTH_METHOD=trust
PG_DATA=/data/postgres

RABBITMQ_HOST=localhost
RABBITMQ_PORT=5672
RABBITMQ_USER=guest
RABBITMQ_PASS=guest

ENDPOINT_COMPARTILHAMENTO_API=http://localhost:8081
ENDPOINT_DRONES_CONSUMER_API=http://localhost:8084
ENDPOINT_LACTACAO_CONSUMER_API=http://localhost:8086
ENDPOINT_COLHEITA_CONSUMER_API=http://localhost:8082
```
3. Preencha as variáveis de conexão com os respectivos valores.
4. Por fim, execute o projeto utilizando o docker-compose:
```shell
$ docker-compose up -d --build
```
### Para acessar
1. Agrow: http://localhost:8080
2. RabbitMQ: http://localhost:15672
3. PGAdmin: http://localhost:5051