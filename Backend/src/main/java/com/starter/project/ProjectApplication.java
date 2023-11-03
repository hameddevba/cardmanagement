package com.starter.project;

import com.starter.project.dao.CardTypeDao;
import com.starter.project.service.AccountService;
import com.starter.project.service.AgencyService;
import com.starter.project.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {
    @Autowired
    RequestService requestService;

    @Autowired
    AccountService accountService;

    @Autowired
    AgencyService agencyService;

    @Autowired
    CardTypeDao cardTypeDao;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

}
