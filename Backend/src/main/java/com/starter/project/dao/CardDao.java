package com.starter.project.dao;

import com.starter.project.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDao extends JpaRepository<Card, Long> {
}
