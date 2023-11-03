package com.starter.project.dao;

import com.starter.project.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
    Optional<Customer> findByNni(String code);
}
