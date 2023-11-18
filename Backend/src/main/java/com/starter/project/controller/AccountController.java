package com.starter.project.controller;

import com.starter.project.dto.AccountDto;
import com.starter.project.mapper.AccountMapper;
import com.starter.project.model.Account;
import com.starter.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    AccountMapper mapper;


    @GetMapping
    public ResponseEntity<List<AccountDto>> find(@Param("searchText") String searchText,@Param("size") int size) {
        List<Account> accounts = accountService.find(searchText, size);

        return ResponseEntity.ok(mapper.toDto(accounts));
    }


}