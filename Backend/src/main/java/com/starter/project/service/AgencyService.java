package com.starter.project.service;

import com.starter.project.dao.AgencyDao;
import com.starter.project.model.Agency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AgencyService {
    @Autowired
    private AgencyDao agencyDao;

    public Agency save(Agency agency) {
        return agencyDao.save(agency);
    }
}
