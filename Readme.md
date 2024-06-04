# camila-services

[![Hexagonal Architecture](https://img.shields.io/badge/Architecture-Hexagonal-brightgreen.svg?style=plastic)](https://alistair.cockburn.us/hexagonal-architecture/)
[![Reactive Paradigm](https://img.shields.io/badge/Programming%20Paradigm-Reactive-blue.svg?style=plastic)](https://www.reactivemanifesto.org/)
[![Microservices Style](https://img.shields.io/badge/Architectural%20Style-Microservices-purple.svg?style=plastic)](https://microservices.io/)
[![OAuth2 Standard](https://img.shields.io/badge/Security-OAuth2-yellow.svg?style=plastic)](https://oauth.net/2/)
[![Serveless Standard](https://img.shields.io/badge/Deploy%20Approach-Serveless-black.svg?style=plastic)](https://martinfowler.com/articles/serverless.html)

Side project to practice **Java** `Ecosystem`

## Tecnologías

| Development                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | Testing                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | Deployment                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![OpenJDK](https://img.shields.io/badge/OpenJDK-%3E%3D21-005571.svg)](https://adoptium.net/es/temurin/releases/) <br> [![GraalVM](https://img.shields.io/badge/GraalVM-%3E%3D21.0.1-005571.svg)](https://www.graalvm.org/downloads/) <br> [![Maven](https://img.shields.io/badge/Maven-%3E%3D3.8.8-005571.svg)](https://maven.apache.org/) <br> [![Spring](https://img.shields.io/badge/Spring-%3E%3D6.x-brightgreen.svg)](https://spring.io/) <br> [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-%3E%3D3.2.x-brightgreen.svg)](https://spring.io/boot) <br> [![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-%3E%3D2023.0.x-brightgreen.svg)](https://spring.io/cloud) <br> [![MongoDB](https://img.shields.io/badge/MongoDB-%3E%3D7.x-cyan.svg)](https://www.mongodb.com/) <br> [![Couchbase](https://img.shields.io/badge/Couchbase-%3E%3D7.x-blue.svg)](https://www.couchbase.com/) | [![JUnit5](https://img.shields.io/badge/JUnit5-%3E%3D5.10.2-orange.svg)](https://junit.org/junit5/) <br> [![Cucumber](https://img.shields.io/badge/Cucumber-%3E%3D7.17.0-orange.svg)](https://cucumber.io/) <br> [![Pitest](https://img.shields.io/badge/Pitest-%3E%3D1.21.1-orange.svg)](https://pitest.org/) <br> [![ChaosMonkey](https://img.shields.io/badge/ChaosMonkey-%3E%3D3.1.0-orange.svg)](https://codecentric.github.io/chaos-monkey-spring-boot/) <br> [![ArchUnit](https://img.shields.io/badge/ArchUnit-%3E%3D1.2.1-orange.svg)](https://www.archunit.org/) <br> [![JMeter](https://img.shields.io/badge/JMeter-%3E%3D5.6.2-orange.svg)](https://jmeter.apache.org/) <br> [![TestContainers](https://img.shields.io/badge/Testcontainers-%3E%3D1.19.8-orange.svg)](https://testcontainers.com/) | [![Docker](https://img.shields.io/badge/Docker-%3E%3D26.1.3-brown.svg)](https://www.docker.com/) <br> [![Docker-compose](https://img.shields.io/badge/Docker%20Compose-%3E%3D2.27.0-brown.svg)](https://docs.docker.com/compose/install/) <br> [![Kubernetes](https://img.shields.io/badge/Kubernetes-%3E%3D1.30.1-brown.svg)](https://kubernetes.io/releases/) <br> [![Knative](https://img.shields.io/badge/Knative-%3E%3D1.10.2-brown.svg)](https://github.com/knative/serving/releases/) |

## Componentes

| Componente                                  | Descripción                                                                     |
|---------------------------------------------|---------------------------------------------------------------------------------|
| [camila-product-api](/camila-product-api)   | Contiene un microservicio que expone la consulta de productos                   |
| [camila-discovery](/camila-discovery)       | Contiene un descubridor de servicios                                            |
| [camila-gateway](/camila-gateway)           | Contiene un gateway para servicios                                              |
| [camila-config](/camila-config)             | Contiene un configurador central de servicios                                   |
| [camila-admin](/camila-admin)               | Contiene un administrador de servicios (UI)                                     |
| [camila-orchestrator](/camila-orchestrator) | Contiene la configuración como código para orquestar el despliegue del proyecto |

## Diagrama de arquitectura

![Arquitectura-C1](.docs/architecture/camila-service-da-v1-C1.svg "Diagrama C1")

![Arquitectura-C2](.docs/architecture/camila-service-da-v1-C2.svg "Diagrama C2")

## Domain Storytelling

En **camila.shopping**, se ha reconocido la necesidad de mejorar la organización y presentación de productos. Para abordar este desafío, se ha propuesto el desarrollo de un algoritmo de clasificación que optimice la experiencia del usuario al buscar dichos productos.

![domain-storytelling](.docs/architecture/camila-shopping-domain-v1.dst.svg "Diagrama WDS")

---

**El Problema:**
La diversidad de productos en **camila.shopping** es impresionante, pero la manera en que se presentan puede mejorarse. La tarea es organizar estos productos de manera efectiva dentro de sus respectivas categorías para facilitar la búsqueda de los clientes.

**La Solución:**
La solución propuesta es un algoritmo de clasificación que emplea una combinación ponderada de métricas, en este caso: el número de unidades vendidas y el ratio de stock. La ponderación asignará por ejemplo, un 80% de peso al número de unidades vendidas. El 20% restante se asignará al ratio de stock, siendo estas ponderaciones variables en cada consulta.

**La Presentación de Resultados:**
La información organizada será expuesta a través de una API REST. Esta interfaz proporcionará un medio eficiente para acceder a la lista de productos ordenada según las métricas definidas.

**La Implementación:**
El algoritmo estará integrado en la infraestructura de **camila.shopping** y se comunicará con los sistemas existentes para recopilar datos de ventas y stock. La información clasificada estará disponible para consumo a través de la API REST, proporcionando a los usuarios un acceso fácil y directo a los productos organizados.

**El Impacto Esperado:**
Con esta mejora, se espera optimizar la experiencia del usuario, aumentar la eficiencia en la búsqueda de productos y, elevar la satisfacción de los clientes en **camila.shopping**. La implementación del algoritmo de clasificación es un paso estratégico para mantenerse a la vanguardia en el competitivo mundo del comercio electrónico.

---

Métricas:

| name                 | description                 |
|----------------------|-----------------------------|
| Ventas por unidades  | número de unidades vendidas | 
| Ratio de stock       | ratio de tallas con stock   |

---

Muestra de productos:

| id | name                          | sales_units | stock                |
|----|-------------------------------|-------------|----------------------|
| 1  | V-NECH BASIC SHIRT            | 100         | S: 4 / M:9 / L:0     |
| 2  | CONTRASTING FABRIC T-SHIRT    | 50          | S: 35 / M:9 / L:9    |
| 3  | RAISED PRINT T-SHIRT          | 80          | S: 20 / M:2 / L:20   |
| 4  | PLEATED T-SHIRT               | 3           | S: 25 / M:30 / L:10  |
| 5  | CONTRASTING LACE T-SHIRT      | 650         | S: 0 / M:1 / L:0     |
| 6  | SLOGAN T-SHIRT                | 20          | S: 9 / M:2 / L:5     |

## Empaquetar

```bash
export SPRING_PROFILES_ACTIVE="${SPRING_PROFILES_ACTIVE:-"loc"}"

# jars
mvn deploy \
  -Dmaven.test.skip=true  -f ./pom.xml
# images
mvn spring-boot:build-image \
  -Dmaven.test.skip=true  -f ./pom.xml
```
