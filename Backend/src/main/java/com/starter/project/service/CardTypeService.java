package com.starter.project.service;

import com.starter.project.dao.CardTypeDao;
import com.starter.project.model.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CardTypeService {
    @Autowired
    private CardTypeDao cardTypeDao;

    public List<CardType> findAll() {
        return cardTypeDao.findAll();
    }

}
