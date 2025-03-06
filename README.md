# Projeto AlocaSalas

## Visão Geral

O projeto AlocaSalas é uma aplicação web projetada para gerenciar a alocação de salas para diversas atividades. Ele fornece funcionalidades para adicionar, editar, excluir e listar salas, garantindo uma gestão eficiente das mesmas.

## Tecnologias Utilizadas

-   **Java**: A principal linguagem de programação utilizada para o desenvolvimento do backend.
-   **Spring Boot**: Um framework usado para criar aplicações Spring independentes e de nível de produção.
-   **Spring Cloud Gateway**: Utilizado como API Gateway para roteamento de requisições.
-   **Spring Data JPA**: Fornece integração fácil com o banco de dados usando JPA (Java Persistence API).
-   **Maven**: Uma ferramenta de automação de build usada para gerenciar dependências do projeto e construir o projeto.
-   **SQL**: Utilizado para gerenciamento e operações de banco de dados.
-   **Swagger**: Utilizado para documentação e teste de APIs.
-   **Jakarta Persistence**: Para ORM (Mapeamento Objeto-Relacional) e interações com o banco de dados.

## Estrutura do Projeto

O projeto é composto por múltiplos serviços que fazem parte do mesmo workspace:

-   Eureka Server: Servidor de descoberta de serviços.
-   API Gateway: Roteamento de requisições para os serviços apropriados.
-   AlocaSalas Service: Serviço principal para gerenciamento de salas.
-   Email Service: Serviço para envio de emails.

A estrutura do projeto segue um padrão de projeto Maven:
```bash
AlocaSalas/
├── eureka-server/
├── api-gateway/
├── aloca-salas-service/
├── email-service/
└── README.md

```


## Como Executar

1.  Clone o repositório:

    ```bash
    git clone https://github.com/JulianReina96/AlocaSalas-BackEnd/
    cd AlocaSalas
    ```

2.  Inicie o Eureka Server:

    ```bash
    cd eureka-server
    mvn clean install
    mvn spring-boot:run
    ```

3.  Inicie o API Gateway:

    ```bash
    cd ../api-gateway
    mvn clean install
    mvn spring-boot:run
    ```

4.  Inicie o AlocaSalas Service:

    ```bash
    cd ../aloca-salas-service
    mvn clean install
    mvn spring-boot:run
    ```

5.  Inicie o Email Service:

    ```bash
    cd ../email-service
    mvn clean install
    mvn spring-boot:run
    ```

## Endpoints da API

### Endpoints de Sala

-   `GET /sala/{nome}`: Busca uma sala pelo nome.
-   `POST /sala`: Adiciona uma nova sala.
-   `PUT /sala/{idSala}`: Edita uma sala existente.
-   `DELETE /sala/{idSala}`: Exclui uma sala pelo ID.
-   `GET /sala`: Lista todas as salas.

### Endpoints de Disciplina

-   `GET /disciplina/{nome}`: Busca uma disciplina pelo nome.
-   `POST /disciplina`: Adiciona uma nova disciplina.
-   `PUT /disciplina/{idDisciplina}`: Edita uma disciplina existente.
-   `DELETE /disciplina/{idDisciplina}`: Exclui uma disciplina pelo ID.
-   `GET /disciplina`: Lista todas as disciplinas.

### Endpoints de Aula

-   `GET /aula/{id}`: Busca uma aula pelo ID.
-   `POST /aula`: Adiciona uma nova aula.
-   `PUT /aula/{idAula}`: Edita uma aula existente.
-   `DELETE /aula/{idAula}`: Exclui uma aula pelo ID.
-   `GET /aula`: Lista todas as aulas.

### Endpoints de Horário

-   `GET /horario/{id}`: Busca um horário pelo ID.
-   `POST /horario`: Adiciona um novo horário.
-   `PUT /horario/{idHorario}`: Edita um horário existente.
-   `DELETE /horario/{idHorario}`: Exclui um horário pelo ID.
-   `GET /horario`: Lista todos os horários.

## Swagger

O projeto utiliza o Swagger para documentação e teste das APIs. O Swagger é configurado para gerar automaticamente a documentação das APIs baseando-se nas anotações presentes nos controladores. Para acessar a documentação do Swagger, você pode navegar até a URL `/swagger-ui.html` após iniciar a aplicação.

### Configuração do Swagger

A configuração do Swagger pode ser feita adicionando a dependência do `springdoc-openapi-ui` no arquivo `pom.xml`:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.5.10</version>
</dependency>
```
## Contato

Para qualquer dúvida ou suporte, entre em contato com o mantenedor do projeto em [julianreina@gmail.com](mailto:julianreina@gmail.com).
