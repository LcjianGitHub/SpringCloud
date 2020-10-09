package com.example.demo0928.service.impl;

import com.example.demo0928.dao.TestDao;
import com.example.demo0928.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestDao testDao;

    @Override
    public String getName(String id) {
        return testDao.getName(id);
//        return "";
    }
}
