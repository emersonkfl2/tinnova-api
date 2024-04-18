# Sistema de gerenciamento de veículos
Este projeto é um simples projeto que permite a criação, busca, alteração e exclusão de veículos.
A aplicação usa a arquitetura REST e os dados são armazenados no POSTGRESQL.
## Features
- Criação de veículos.
- Busca de veículos.
- Alteração de veículos.
- Exclusão de veículos
## Tecnologias usadas
- Java
- Spring Boot
- Spring Data JPA
- JUnit
- Jackson
- Docker
- PostgreSQL
## Endpoints
TOs seguintes endpoints estão disponíveis
### Endpoints de veículos
- `GET /veiculos`: Retorna a lista de veículos na base.
- `GET /veiculos/search?marca={marca}&ano={ano}&cor={cor}`: Retorna a lista de veículos com base nos parametros passados.
- `GET /veiculos/{id}`: Retorna o veículo de acordo com o ID .
- `POST /veiculos`: Cria um novo veículo na base de dados.
- `PUT /veiculos/{id}`: Altera um veículo existente
- `PATCH /veiculos/{id}`: Altera dados específicos de um veículo com base no body da requisição
- `DELETE /veiculos/{id}`: deleta um veículo pelo ID.

## Como rodar o projeto
1. Clone os repositório usando`git clone https://github.com/emersonkfl2/tinnova-api.git`
2. Vá para a raíz do diretório co o comando `cd tinnova-api`
2. Abra a pasta `docker` no terminal e rode o comando `sudo docker compose up` para iniciar o banco de dados
3. Rode o comando `mvn clean install` para buildar o projeto
4. Inicie o projeto pelo comando `mvn spring-boot:run`
5. The application will be running on `http://localhost:8080/api/`
