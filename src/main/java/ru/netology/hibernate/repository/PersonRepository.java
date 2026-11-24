package ru.netology.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.entity.Person;

import java.util.List;

@Repository
public class PersonRepository {

    /**
     * @PersistenceContext — правильный способ получить EntityManager:
     * Spring внедряет потокобезопасный прокси, который для каждой
     * транзакции или запроса берёт свой настоящий EntityManager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Люди, живущие в указанном городе. Сравнение без учёта регистра:
     * "Moscow", "moscow" и "MOSCOW" найдут одних и тех же людей.
     * Фильтрация выполняется в базе данных JPQL-запросом,
     * а не загрузкой всей таблицы в память.
     */
    public List<Person> getPersonsByCity(String city) {
        return entityManager.createQuery(
                        "SELECT p FROM Person p WHERE LOWER(p.cityOfLiving) = LOWER(:city)",
                        Person.class)
                .setParameter("city", city)
                .getResultList();
    }
}
