package com.example.demo0928.dao.impl;

import com.example.demo0928.dao.TestDao;
import com.example.demo0928.mapper.TestMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class TestDaoImpl implements TestDao {

    @Resource
    TestMapper testMapper;

    @Override
    public String getName(String id) {
        return testMapper.getName(id);
    }
}
