package com.starter.project;

import com.starter.project.dao.*;
import com.starter.project.model.Role;
import com.starter.project.model.enums.RoleEnum;
import com.starter.project.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Autowired
    RoleDao roleDao;


    @Override
    public void run(String... args) throws Exception {
        Role r = new Role(RoleEnum.ROLE_ADMIN);
        roleDao.save(r);
    }
}
