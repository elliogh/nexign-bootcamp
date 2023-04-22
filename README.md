# Nexign Bootcamp
## Стек
- Java 11
- Maven
- Spring (Boot, Cloud, Data)
- PostgreSQL

## Информация о проекте
- `eureka-server` - сервер для регистрации микросервисов Eureka
- `api-gateway` - gateway для переадресации запросов
- `cdr-generator-service` - сервис для генерации CDR
- `brt-service` - Billing Real Time сервис для тарификации
- `hrs-service` - High Real Time сервис для тарификации
- `crm-service` - CRM сервис для абонента и менеджера

### eureka-server
Работает на порту `8761` и осуществляет регистрацию микросервисов

### api-gateway
Работает на порту `8765` и переадресовывает запросы на нужный микросервис

### cdr-generator-service
Работает на порту `8084` и генерирует CDR файл, который содержит: тип вызова,
номер абонента,
дата и время начала звонка,
дата и время окончания звонка.

### brt-service
Работает на порту `8085`, авторизует абонентов оператора, чей телефон хранится в базе и чей баланс больше 0.
генерирует файл CDR+, который содержит уже информацию о тарифе абонента и передает его в ``hrs-service`` (High performance rating server)

### hrs-service
Работает на `произвольном` порту и считает сколько денег нужно списать со счетаабонента, исходя из длительности его разговоров и выбранного тарифа
и возвращает данные в `brt-service`, который вносит изменения в базу и меняет баланс пользователя на соответствующую сумму

### crm-service
Работает на порту `8090` и тут есть два уровня прав: менеджер и абонент
- Абонент может пополнить счет и получить детализацию звонков
- Менеджер может сменить тариф, создать нового пользователя и выполнить тарификацию

Для генерации CDR: `http://localhost:8765/cdr-generator/cdr/random`

## Порядок запуска сервисов
`eureka-server` -> `cdr-generator-service` -> `hrs-service` -> `brt-service` -> `crm-service` -> `api-gateway`