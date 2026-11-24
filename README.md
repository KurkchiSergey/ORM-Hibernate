# spring-hibernate-orders

Приложение из задания по JDBC, переписанное на Hibernate (JPA):
по имени покупателя возвращает названия заказанных им товаров.

## Сущности и связь

- `Customer` (таблица `customers`) — `@OneToMany(mappedBy = "customer") List<Order> orders`
- `Order` (таблица `orders`) — `@ManyToOne @JoinColumn(name = "customer_id") Customer customer`

У одного покупателя много заказов, у каждого заказа ровно один покупатель,
поэтому связь двунаправленная один-ко-многим / многие-к-одному.
Владелец связи — `Order`, потому что внешний ключ `customer_id` хранится в таблице `orders`.

## Репозиторий

`ProductRepository` получает `EntityManager` через `@PersistenceContext`
и выполняет JPQL-запрос:

```
SELECT o.productName FROM Customer c JOIN c.orders o WHERE LOWER(c.name) = LOWER(:name)
```

## Запуск

1. `docker start my-postgres`
2. Запустите `SpringHibernateOrdersApplication`.
3. Откройте `http://localhost:8080/products/fetch-product?name=alexey`
