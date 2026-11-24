package ru.netology.orders.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Названия товаров, которые заказывал покупатель с указанным именем.
     * JPQL работает с сущностями, а не с таблицами: вместо SQL-JOIN по customer_id
     * мы идём по связи c.orders, а Hibernate сам строит JOIN.
     * Имя сравнивается без учёта регистра.
     */
    public List<String> getProductName(String name) {
        return entityManager.createQuery(
                        "SELECT o.productName FROM Customer c JOIN c.orders o " +
                                "WHERE LOWER(c.name) = LOWER(:name)",
                        String.class)
                .setParameter("name", name)
                .getResultList();
    }
}
