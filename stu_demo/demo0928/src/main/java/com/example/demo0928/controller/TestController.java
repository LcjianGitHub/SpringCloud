package com.example.demo0928.controller;

import com.example.demo0928.service.TestService;
import com.example.demo0928.thread.ThreadCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        System.out.println(name);
        return name;
//        String sql = "select name from tb_lcjian_demo where id =?";
//        String name =(String)jdbcTemplate.queryForObject(sql,new Object[]{1},String.class);
//        return name;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(2 * Runtime.getRuntime().availableProcessors());
        ExecutorService pool = Executors.newFixedThreadPool(2);
        List<Future<Map<String, Object>>> results = new ArrayList<>();
        List<Map<String,Object>> list = new ArrayList<>();
        Map map = new HashMap();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i <3 ; i++) {
            results.add(pool.submit(new ThreadCallable(map, i, countDownLatch))) ;
//            Future submit = pool.submit(new ThreadCallable(map, i, countDownLatch));
        }
        System.out.println("----------------");
        countDownLatch.await();
        System.out.println(System.currentTimeMillis());
        for (Future<Map<String,Object>> fu: results) {
            list.add(fu.get());
        }
        System.out.println(list.toString());
        pool.shutdown();
    }

}
