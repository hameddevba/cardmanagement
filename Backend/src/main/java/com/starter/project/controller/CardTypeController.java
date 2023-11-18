package com.starter.project.controller;

import com.starter.project.dto.CardTypeDto;
import com.starter.project.mapper.CardTypeMapper;
import com.starter.project.model.CardType;
import com.starter.project.service.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/cardTypes")
public class CardTypeController {
    @Autowired
    CardTypeService cardTypeService;
    @Autowired
    CardTypeMapper mapper;


    @GetMapping("")
    public ResponseEntity<List<CardTypeDto>> findAll() {
        List<CardType> users = cardTypeService.findAll();
        return ResponseEntity.ok(mapper.toDto(users));
    }
}