package com.example.demo0928.controller;

import com.example.demo0928.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/getMsg",method = RequestMethod.GET)
    public String getTestMsg() throws Exception{
//        return "Test Message !!!";
        String name = testService.getName("1");
        return name;
//        String sql = "select name from tb_lcjian_demo where id =?";
//        String name =(String)jdbcTemplate.queryForObject(sql,new Object[]{1},String.class);
//        return name;
    }
}
