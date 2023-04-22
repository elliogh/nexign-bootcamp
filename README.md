# Nexign Bootcamp
## Стек
- Java 11
- Spring (Boot, Cloud)

## Информация о проекте
- ``eureka-server`` - сервер для регистрации микросервисов Eureka
- ``api-gateway`` - gateway для переадресации запросов
- ``cdr-generator-service`` - сервис для генерации CDR
- ``brt-service`` - Billing Real Time сервис для тарификации
- ``hrs-service`` - High Real Time сервис для тарификации
- ``crm-service`` - CRM сервис для абонента и менеджера

### eureka-server
Работает на порту ``8761``

### api-gateway
Работает на порту ``8765``

### cdr-generator-service
Работает на порту ``8084``

### brt-service
Работает на порту ``8085``

### hrs-service
Работает на произвольном порту

### crm-service
Работает на порту ``8090``

Для генерации отчета: ``http://localhost:8765/cdr-generator/cdr/random``

## Порядок запуска сервисов
``eureka-server`` -> ``cdr-generator-service`` -> ``hrs-service`` -> ``brt-service`` -> ``crm-service`` -> ``api-gateway``