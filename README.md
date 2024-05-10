## _Cáculo de Produto de Seguros_

Este micro serviço foi desenvolvido com o objetivo básico de calcular o preço tarifado de um produto de seguros a partir do preço base informado.


## Como executar este micro serviço:

- Pré requisitos:
	- JDK 17.0.10
	- Maven 3.8.6
- Após efetuar o git pull ou download do projeto
	- Abrir o terminal para executar o projeto (cmd -> win | shell -> linux) 
	- Acessar o diretório raiz do projeto: **cd ..\calculo-produto-seguros**
	- Executar o comando abaixo para inicializar o micro serviço:

```
$ mvn clean install exec:java
```

## Documentação da API:

- Para acessar a documentação da api basta acessar no browser o endereço: [API Docs](http://127.0.0.1:8081/seguros/api-docs)
- Para visualizar a documentação da api de uma forma fácil e interativa basta acessar o link: [Seguros Docs](http://127.0.0.1:8081/seguros/docs.html)

## Pontos de desenvolvimeno da solução:

- Foi desenvolvido um CRUD com foco no negócio do Produto de Seguros
- Esta solução foi desenvolvida com foco em Clean Code e utilizando principios SOLID.
- Foram desenvolvidos 2 testes unitários com foco em transaçaõ com a base de dados
- FOram desenvolvidos 5 testes de integração basica para atestar a funcionalidade da api rest.
- Foram expostas funcionalidades de métricas para observar a saude do micro-serviço
  - [Health](http://127.0.0.1:8081/seguros/actuator/health)
  - [Memória](http://127.0.0.1:8081/seguros/actuator/metrics/jvm.memory.used)
  - [CPU](http://127.0.0.1:8081/seguros/actuator/metrics/process.cpu.usage)
  - [Prometheus](http://127.0.0.1:8081/seguros/actuator/prometheus)

