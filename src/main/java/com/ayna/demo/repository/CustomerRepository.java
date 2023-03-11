package com.ayna.demo.repository;

import com.ayna.demo.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.surname LIKE : value")
    List<Customer> findByLastname(@Param("value") String surname);
}
