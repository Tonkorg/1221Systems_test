# Calorie Tracker API

REST API сервис для отслеживания дневной нормы калорий пользователя и учета съеденных блюд. Разработан как тестовое задание для компании 1221Systems.

## Описание

Проект предоставляет функциональность для:
- Регистрации пользователей с автоматическим расчетом дневной нормы калорий (по формуле Харриса-Бенедикта).
- Добавления блюд с указанием калорий и макронутриентов (белки, жиры, углеводы).
- Учета приемов пищи с привязкой к пользователю и списку блюд.
- Генерации отчетов:
    - Отчет за день (сумма калорий и список приемов пищи).
    - Проверка соблюдения дневной нормы калорий.
    - История питания по дням.

API документирован с использованием Swagger (доступен по адресу `/swagger-ui.html`).

## Технологии

- **Java**: 17
- **Spring Boot**: 3.4.4
- **Spring Data JPA**: Для работы с базой данных
- **PostgreSQL**: База данных (версия 16 в Docker)
- **Lombok**: Упрощение кода (геттеры, сеттеры, Builder)
- **Springdoc OpenAPI**: Документация API через Swagger
- **Maven**: Сборка проекта
- **Docker**: Контейнеризация приложения и базы данных
- **Docker Compose**: Оркестрация контейнеров
- **JUnit 5 + Mockito**: Юнит-тесты

## Требования

- **Docker**: Установленный Docker и Docker Compose для запуска приложения в контейнерах.
- **Maven**: Для сборки JAR-файла (если не используете Docker локально).
- **Java 17**: Только если запускаете без Docker.

## Установка и запуск

### Вариант 1: Запуск с Docker Compose (рекомендуемый)

1. **Клонирование репозитория**:
   ```bash
   git clone https://github.com/Tonkorg/1221Systems_test.git
   cd calorie-tracker
   ```
2. **Настройка переменных окружения**:

Создайте файл .env в корне проекта и добавьте следующие переменные:

```
SERVER_PORT=8080
DB_PORT=5432
SPRING_DATASOURCE_URL_DOCKER=jdbc:postgresql://db:5432/calorie_tracker
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=your_password
SPRING_JPA_HIBERNATE_DDL_AUTO=update
POSTGRES_DB=calorie_tracker
POSTGRES_USER=postgres
POSTGRES_PASSWORD=your_password
```

3. **Сборка и запуск:**

Убедитесь, что Docker и Docker Compose установлены.

Выполните:

```bash
mvn clean package
docker-compose up --build
```

Приложение будет доступно по адресу: http://localhost:8080/api.

### Вариант 2: Локальный запуск без Docker

1. **Клонирование репозитория**

```bash
git clone https://github.com/Tonkorg/1221Systems_test.git
cd calorie-tracker
```
2. Настройка базы данных:
   Установите PostgreSQL локально.  
   Создайте базу данных:

```sql
CREATE DATABASE calorie_tracker;
```
Настройте src/main/resources/application.properties

```yaml
spring.datasource.url=jdbc:postgresql://localhost:5432/calorie_tracker
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. **Сборка и запуск:**

```bash
mvn clean install
mvn spring-boot:run
```

API будет доступно по адресу: http://localhost:8080/api.

### Документация API
Откройте Swagger UI в браузере: http://localhost:8080/swagger-ui.html.
Используйте Swagger для тестирования эндпоинтов.
```
Метод	Эндпоинт	Описание
POST	/api/users	Создать пользователя
POST	/api/dishes	Добавить блюдо
POST	/api/meals	Добавить прием пищи
GET	/api/reports/daily/{userId}	Отчет за день
GET	/api/reports/check-norm/{userId}	Проверка нормы калорий
GET	/api/reports/history/{userId}	История питания за период
```

### Примеры запросов
Создание пользователя:

```text
POST http://localhost:8080/api/users
Content-Type: application/json
{
    "name": "John Doe",
    "email": "john@example.com",
    "age": 30,
    "weight": 70.0,
    "height": 170.0,
    "goal": "MAINTENANCE"
}
```

Добавление блюда

```text

POST http://localhost:8080/api/dishes
Content-Type: application/json
{
    "name": "Salad",
    "calories": 200.0,
    "protein": 10.0,
    "fat": 5.0,
    "carbs": 15.0
}
```

Добавление приема пищи:

```text
POST http://localhost:8080/api/meals
Content-Type: application/json
{
    "userId": 1,
    "dishIds": [1],
    "dateTime": "2025-03-21T12:00:00"
}
```


### Тестирование
Юнит-тесты для основной логики находятся в src/test/java. Запустите тесты:

```bash
mvn test
```


## Postman-коллекция

Для тестирования API используйте файл `CalorieTrackerAPI_v1.postman_collection.json` в корне репозитория. Импортируйте его в Postman через `File > Import`.

[CalorieTrackerAPI.postman_collection.json](CalorieTrackerAPI.postman_collection.json)