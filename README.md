# Клиент для взаимодействия с REST API

Это клиент, позволяющий легко взаимодействовать с [REST_API](https://github.com/vakaheydev/daily-rest-api)

## Особенности
- **Клиент**: Позволяет [Сайту MVC](https://github.com/vakaheydev/daily-mvc) и [Телеграм боту](https://github.com/vakaheydev/daily-tgbot) взаимодействовать с [REST_API](https://github.com/vakaheydev/daily-rest-api) для управления данными

## Установка

Проект контейнеризован с использованием Docker, что позволяет быстро и удобно осуществить запуск всех сервисов (REST API, MVC, Telegram-бот) локально.

### Требования

- Docker
- Docker Compose

### Запуск приложения

Чтобы запустить все сервисы (REST API, клиент, MVC и Telegram-бот), выполните следующие команды:

Перейдите в папку с докером в [REST_API](https://github.com/vakaheydev/daily-rest-api):
```sh
cd ./docker
```

Запустите все сервисы:
```sh
docker compose up --build -d 
```

Чтобы запустить конкретный сервис, используйте команду ниже, но замените <service name> именем нужного сервиса (rest-api, mvc, tg-bot)
```sh
docker compose up --build -d <service name> 
```
