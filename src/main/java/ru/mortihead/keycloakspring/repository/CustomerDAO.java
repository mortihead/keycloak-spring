package ru.mortihead.keycloakspring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mortihead.keycloakspring.dto.Customer;

public interface CustomerDAO extends CrudRepository<Customer, Long> {

}