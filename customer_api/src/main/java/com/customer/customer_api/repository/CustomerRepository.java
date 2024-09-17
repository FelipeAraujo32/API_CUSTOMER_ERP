package com.customer.customer_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.customer_api.entity.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, UUID>{

}
