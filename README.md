# ToolsChallenge - API de Pagamentos

## **Tabela de Conteúdos**

[Descrição](#descrição)

[Pré-requisitos](#pré-requisitos)

[Instalação](#instalação)

[Como Usar](#como-usar)

[Testes](#testes)

[Estrutura do Projeto](#estrutura-do-projeto)

[Tecnologias Utilizadas](#tecnologias-utilizadas)

[Contribuição](#contribuição)

[Contato](#contato)


## **Descrição**

ToolsChalenge - API Pagamentos: Sistema de pagamentos de cartões de crédito, responsável por gerenciar operações de crédito.

Gestão de Pagamentos: Módulo encarregado de gerar pagamentos de cartões de crédito, realizar estornos e consultar movimentos nos cartões de crédito.

## **Pré-requisitos**

Para que o sistema funcione corretamente, é necessário instalar os itens listados abaixo:

- Java 21
- Maven 3.6+
- Postman (para testes de API)

## **Instalação**

**Clone este repositório**

    git clone https://github.com/MurilloThomazini/ToolsChallenge

**Entre no diretório do projeto**

    cd projeto

**Instale as dependências**

    mvn install

**Execute a aplicação**

    mvn spring-boot:run

## **Como Usar**

Para utilizar o sistema, é necessário que o projeto esteja em execução. No Postman, realize as operações desejadas. Abaixo, seguem alguns exemplos de uso:

**Pagamento:**

    curl -X POST http://localhost:8080/pagamento \

    -H "Content-Type: application/json" \

    -d '

    {
        "transacao": {
            "cartao" : "4123234532211234",
            "id": "10001231231237",
            "descricao": {
            "valor": 50.00,
            "dataHora": "09/08/2024 06:30:00",
            "estabelecimento": "Chocolate e cia"
            },
            "formaPagamento": {
                "tipo": "PARCELADO LOJA",
                "parcelas": "2"
            }
        }
    } 

    '

**Estorno Pagamento:**

    curl -X POST http://localhost:8080/pagamento/10001231231237/estorno \

    -H "Content-Type: application/json" \

**Consultar Pagamento:**

    curl -X GET http://localhost:8080/pagamento/10001231231237 \

    -H "Content-Type: application/json" \

**Consultar Pagamentos Gerais:**

    curl -X GET http://localhost:8080/pagamento \

    -H "Content-Type: application/json" \

## **Testes**

O sistema conta com um módulo de testes para assegurar que tudo esteja conforme o esperado. Para executar os testes, basta utilizar o comando abaixo:

    mvn test

## **Estrutura do Projeto**

    src/

    ├── main/

    │   ├── java/

    │   │   └── com/

    │   │       └── tools/

    │   │           └── challenge/

    │   │               ├── Application.java

    │   │               ├── controller/

    │   │               │   └── PagamentoController.java

    │   │               ├── exception/

    │   │               │   ├── ExceptionControllerAdvice.java

    │   │               │   └── TransacaoNaoEncontradaException.java

    │   │               ├── models/

    │   │               │   ├── error/

    │   │               │   │   └── ErrorResponse.java

    │   │               │   ├── pagamento/

    │   │               │   │   ├── Descricao.java

    │   │               │   │   ├── FormaPagamento.java

    │   │               │   │   ├── Transacao.java

    │   │               │   │   ├── TransacaoResponse.java

    │   │               │   │   ├── enums/

    │   │               │   │   │   ├── StatusEnum.java

    │   │               │   │   │   └── TipoPagamentoEnum.java

    │   │               └── service/

    │   │                   └── PagamentoService.java

    │   └── resources/

    │       └── application.properties

    └── test/

        ├── java/

        │   └── com/

        │       └── tools/

        │           └── challenge/

        │               ├── controller/

        │               │   └── PagamentoControllerTest.java

        │               └── service/
    
        │                   └── PagamentoServiceTest.java


## **Tecnologias Utilizadas**

- Java 21
- Spring Boot 3.3.2
- Maven 3.6+
- JUnit


## **Contribuição**

**Faça um fork do projeto**

**Crie uma branch para sua feature**

    git checkout -b feature/AmazingFeature

**Adicione suas alterações**

    git add -A 

**Commit suas mudanças**

    git commit -m 'Add some AmazingFeature'

**Push para a branch**

    git push origin feature/AmazingFeature

**Abra um Pull Request**

## **Contato**

**Murillo Costa Thomazini** - 	[Email](murillo_thomazini@hotmail.com) - [Linkedin](https://www.linkedin.com/in/murillo-costa-thomazini/)!