package com.starter.project.dao;

import com.starter.project.model.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeDao extends JpaRepository<CardType, Long> {
}
