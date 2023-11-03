package com.starter.project.service;

import com.starter.project.dao.AccountDao;
import com.starter.project.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    public Account save(Account account) {
        return accountDao.save(account);
    }
    public List<Account> findAll() {
        return accountDao.findAll();
    }

}
