package com.starter.project.dao;

import com.starter.project.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyDao extends JpaRepository<Agency, Long> {
    Optional<Agency> findByCode(String code);
}
