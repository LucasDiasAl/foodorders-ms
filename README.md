***Microsserviços e Suas Funcionalidades***

---> Orders (Gerenciamento de Pedidos)

Responsável pelo gerenciamento de pedidos, incluindo criação, listagem e atualização de status dos pedidos.

---> Payments (Processamento de Pagamentos)

Responsável por gerenciar pagamentos, garantindo a integração segura entre pedidos e confirmação financeira.

---> Server (Discovery Server - Eureka Server)

Servidor de descoberta de serviços utilizando Netflix Eureka, permitindo o registro dinâmico dos microsserviços.

---> Gateway (API Gateway com Spring Cloud Gateway)

Responsável pelo roteamento de requisições entre os microsserviços, fornecendo segurança e balanceamento de carga.

*****Tecnologias Utilizadas*****

****Java 17***: Linguagem de programação principal.

****MySQL***: Banco de dados relacional para armazenamento das informações.

****Flyway***: Controle de versão do banco de dados para garantir migrações consistentes.

****Spring Cloud Netflix Eureka***: Registro e descoberta dinâmica de microsserviços.

****Spring Cloud Starter Netflix Eureka Client***: Integração dos microsserviços com Eureka Server.

****Spring Cloud OpenFeign***: Cliente HTTP declarativo para comunicação entre microsserviços.

****Spring Cloud Gateway***: API Gateway para roteamento e segurança.

****Resilience4j***: Implementação do padrão Circuit Breaker para resiliência dos microsserviços.

****Spring Boot Starter Data JPA***: Gerenciamento de persistência.

****Lombok***: Redução de código boilerplate.

****Spring Boot Starter Actuator***: Monitoramento e gestão da aplicação.

