Spring Cloud/Boot
Este microserviço é desenvolvido utilizando os módulos fornecidos pelo ecossistema Spring Cloud/Boot. Esses módulos são essenciais para a construção de uma arquitetura de microservices robusta e escalável.

Arquitetura de Microservices
O objetivo deste projeto é proporcionar um entendimento aprofundado e facilitar a implementação de uma arquitetura completa de microservices. 

Funcionalidades Principais
Service Discovery
O Service Discovery é responsável por facilitar o registro e a descoberta dinâmica de serviços na rede. Isso permite uma comunicação eficiente e escalável entre os diversos microservices.

API Gateway
O API Gateway atua como o ponto de entrada para os microservices, fornecendo uma camada de abstração que simplifica as chamadas aos serviços individuais. Isso contribui para uma maior modularidade e gerenciamento centralizado.

Balanceamento de Carga
O balanceamento de carga é incorporado para distribuir equitativamente as solicitações entre as instâncias dos microservices, melhorando a eficiência e a confiabilidade do sistema.

Serviço/Fila de Mensageria com RabbitMQ
A integração com o RabbitMQ como serviço de mensageria permite a implementação eficiente de comunicação assíncrona entre microservices, melhorando a escalabilidade e a resiliência do sistema.

Authorization Server com Keycloak
O módulo de Authorization Server é implementado usando o Keycloak para gerenciar a autenticação e autorização de usuários. Isso garante um ambiente seguro e controlado para o acesso aos microservices.

