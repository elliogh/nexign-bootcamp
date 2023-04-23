# Nexign Bootcamp
## Стек
- Java 11
- Maven
- Spring (Boot, Cloud, Data, Security)
- PostgreSQL

## Информация о проекте
- `eureka-server` - сервер для регистрации микросервисов Eureka
- `cdr-generator-service` - сервис для генерации CDR
- `brt-service` - Billing Real Time сервис для тарификации
- `hrs-service` - High Real Time сервис для тарификации
- `crm-service` - CRM сервис для абонента и менеджера

### eureka-server
Работает на порту `8761` и осуществляет регистрацию микросервисов

### cdr-generator-service
Работает на порту `8084` и генерирует CDR файл, который содержит: тип вызова,
номер абонента,
дата и время начала звонка,
дата и время окончания звонка и передает его в `brt-service`

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
- Есть авторизация - 2 уровня прав: `user:user`(логин:пароль) для абонента и `admin:admin`(логин:пароль) для менеджера

## Запросы
- Для пополнения баланса:
`PATCH:` `localhost:8090/abonent/pay`

Request Body:
```json
{
  "numberPhone": "73734435259",
  "money": "400"
}
```

Response Body:
```json
{
    "id": 2,
    "numberPhone": "73734435259",
    "money": 400.0
}
```
- Для получения детализации звонков:
`GET:` `localhost:8090/abonent/report/73734435247`

Response Body:
```json
{
  "id": 1,
  "numberPhone": "73734435247",
  "tariffIndex": "11",
  "numbers": [
    {
      "callType": "01",
      "startTime": "2022-01-01 01:15:10",
      "endTime": "2022-01-01 01:41:20",
      "duration": "00:26:10",
      "cost": 13.5
    },
    {
      "callType": "01",
      "startTime": "2022-01-16 01:54:12",
      "endTime": "2022-01-16 02:03:52",
      "duration": "00:09:40",
      "cost": 5.0
    },
    {
      "callType": "01",
      "startTime": "2022-02-02 10:16:33",
      "endTime": "2022-02-02 10:23:03",
      "duration": "00:06:30",
      "cost": 3.5
    },
    {
      "callType": "01",
      "startTime": "2022-02-17 20:53:21",
      "endTime": "2022-02-17 21:11:50",
      "duration": "00:18:29",
      "cost": 9.5
    },
    {
      "callType": "01",
      "startTime": "2022-03-13 21:53:38",
      "endTime": "2022-03-13 22:09:06",
      "duration": "00:15:28",
      "cost": 8.0
    },
    {
      "callType": "01",
      "startTime": "2022-03-21 06:00:30",
      "endTime": "2022-03-21 06:30:16",
      "duration": "00:29:46",
      "cost": 24.0
    },
    {
      "callType": "01",
      "startTime": "2022-04-04 13:05:10",
      "endTime": "2022-04-04 13:24:53",
      "duration": "00:19:43",
      "cost": 30.0
    },
    {
      "callType": "01",
      "startTime": "2022-04-15 17:35:15",
      "endTime": "2022-04-15 17:44:27",
      "duration": "00:09:12",
      "cost": 15.0
    },
    {
      "callType": "01",
      "startTime": "2022-04-30 02:14:38",
      "endTime": "2022-04-30 02:29:20",
      "duration": "00:14:42",
      "cost": 22.5
    },
    {
      "callType": "01",
      "startTime": "2022-05-29 12:03:40",
      "endTime": "2022-05-29 12:12:11",
      "duration": "00:08:31",
      "cost": 13.5
    }
  ],
  "totalCost": 144.5,
  "monetaryUnit": "rubles"
}
```

- Для смены тарифа:
`PATCH:` `localhost:8090/manager/changeTariff`

Request Body:
```json
{
    "numberPhone": "73734435247",
    "tariff_id": "03"
}
```

Response Body:
```json
{
    "id": 1,
    "numberPhone": "73734435247",
    "tariff_id": "03"
}
```

- Для создания нового абонента:
`POST:` `localhost:8090/manager/abonent`

Request Body:
```json
{
    "numberPhone": "7123123126",
    "tariff_id": "06",
    "balance" : "400"
}
```

Response Body:
```json
{
    "numberPhone": "7123123126",
    "tariff_id": "06",
    "balance" : "400"
}
```

- Для выполнения тарификации:
`PATCH:` `localhost:8090/manager/billing`

Request Body:
```json
{
    "action": "run"
}
```

Response Body:
```json
{
    "numbers": [
        {
            "numberPhone": "73734435247",
            "balance": -44.5
        },
        ......................................
        {
            "numberPhone": "73734683247",
            "balance": 0.0
        }
    ]
}
```

## Порядок запуска сервисов
`eureka-server` -> `cdr-generator-service` -> `hrs-service` -> `brt-service` -> `crm-service` -> `api-gateway`