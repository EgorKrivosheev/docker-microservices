# Docker micro-services
`Java: 11`, `Docker-compose`

**Build:** `Maven`, `Docker`

**Deploy**: `Heroku`

## Modules
- [eureka-server] — `Java: 11`, `Spring Boot`, `Eureka Server`, `Eureka Client`
- [admin-server] — `Java: 11`, `Spring Boot` (Web, Security), `Admin Server`, `Eureka Client`
- [transformation] — `Java: 11`, `Spring Boot` (Web, Data, Kafka, Security), `Data` (Postgre SQL, Liquibase), `OpenAPI` (UI, Rest), `Lombok`, `Eureka Client`

[eureka-server]:https://github.com/EgorKrivosheev/docker-microservices/tree/master/eureka-server
[admin-server]:https://github.com/EgorKrivosheev/docker-microservices/tree/master/admin-server
[transformation]:https://github.com/EgorKrivosheev/docker-microservices/tree/master/transformation

# License
[MIT](https://github.com/EgorKrivosheev/docker-microservices/blob/master/LICENSE)
