package com.customer.customer_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.customer_api.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>{

}
