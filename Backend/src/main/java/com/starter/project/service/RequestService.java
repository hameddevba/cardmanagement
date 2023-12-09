package com.starter.project.service;

import com.starter.project.dao.RequestDao;
import com.starter.project.dao.UserDao;
import com.starter.project.model.Request;
import com.starter.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import static java.util.Optional.ofNullable;


@Service
@Transactional
public class RequestService {
    @Autowired
    private RequestDao requestDao;

    @Autowired
    private UserDao userDao;
    public Page<Request> find(int page, int size) {
        return requestDao.findAll(PageRequest.of(page, size, Sort.by("id")));
    }
    public Request findById(long id) {
        return requestDao.findById(id).orElse(null);
    }
    public Request save(Request request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //User user = userDao.findByUsername(authentication.getName()).orElse(null);
        request.setCreationDate(new Date());
        //request.setUser(user);
        //request.setAgency(ofNullable(user).map(User::getAgency).orElse(null));
        return requestDao.save(request);
    }
}
