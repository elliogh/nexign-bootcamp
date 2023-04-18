# Nexign Bootcamp
## Стек
- Java 11
- Spring(Boot, Cloud)

## Информация о проекте
- ``eureka-server`` - сервер для регистрации микросервисов Eureka
- ``api-gateway`` - gateway для переадресации запросов
- ``cdr-generator-service`` - сервис для генерации CDR

### eureka-server
Работает на порту ``8761``

### api-gateway
Работает на порту ``8765``

### cdr-generator-service
Работает на порту произвольном порту

Для генерации отчета: ``http://localhost:8765/cdr-generator/cdr/random``

## Порядок запуска сервисов
``eureka-server`` ``cdr-generator-service`` ``api-gateway``