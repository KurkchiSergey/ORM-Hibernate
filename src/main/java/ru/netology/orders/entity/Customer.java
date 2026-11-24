package ru.netology.orders.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Покупатель — таблица customers.
 * У одного покупателя может быть много заказов: связь один-ко-многим (@OneToMany).
 */
@Entity
@Table(name = "customers")
public class Customer {

    // SERIAL в PostgreSQL = автоинкремент, поэтому стратегия IDENTITY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "surname", length = 50, nullable = false)
    private String surname;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    /**
     * mappedBy = "customer": внешний ключ хранится не здесь, а в таблице orders
     * (поле customer в классе Order). Эта сторона связи — только для чтения.
     * @JsonIgnore — чтобы при выводе в JSON не было бесконечной рекурсии
     */
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
