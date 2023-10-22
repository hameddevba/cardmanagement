package com.starter.project.service;

import com.starter.project.dao.RoleDao;
import com.starter.project.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;


    public List<Role> findAll() {
        return roleDao.findAll(Sort.by("id"));
    }

}
