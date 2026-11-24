# spring-hibernate-dao

Spring Boot приложение со слоем DAO на Hibernate (JPA) для таблицы `persons`
из задания «Таблица пользователей».

## Запуск

1. Запустите PostgreSQL в Docker: `docker start my-postgres`.
2. Запустите класс `SpringHibernateDaoApplication`.
3. Hibernate создаст таблицу `persons`, если её нет (`ddl-auto=update`),
   затем `data.sql` добавит тестовых людей.

## Запрос

```
GET http://localhost:8080/persons/by-city?city=Moscow
```

Город ищется без учёта регистра. Пример ответа:

```json
[
  {"name":"Ivan","surname":"Ivanov","age":30,"phoneNumber":"+79001112233","cityOfLiving":"Moscow"},
  {"name":"Anna","surname":"Sidorova","age":40,"phoneNumber":"+79007778899","cityOfLiving":"Moscow"}
]
```

## Устройство

- `entity/Person` — сущность таблицы `persons`, составной ключ `(name, surname, age)` через `@IdClass`
- `entity/PersonId` — класс составного ключа
- `repository/PersonRepository` — `@Repository`, `EntityManager` через `@PersistenceContext`, метод `getPersonsByCity`
- `controller/PersonController` — endpoint `/persons/by-city`
