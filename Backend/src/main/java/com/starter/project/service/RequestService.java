package com.starter.project.service;

import com.starter.project.dao.RequestDao;
import com.starter.project.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RequestService {
    @Autowired
    private RequestDao requestDao;


    public Page<Request> find(int page, int size) {
        return requestDao.findAll(PageRequest.of(page, size, Sort.by("id")));
    }
    public Request findById(long id) {
        return requestDao.findById(id).orElse(null);
    }
    public Request save(Request request) {
        return requestDao.save(request);
    }
}
