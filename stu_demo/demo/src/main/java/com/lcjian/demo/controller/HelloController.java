package com.lcjian.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lcjian.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description
 * @Author liangcj3
 * @Date 2019/12/17  20:12
 **/
@RestController
@Api("测试接口")
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    DemoService demoService;

    @ApiOperation("测试接口")
    @RequestMapping(value = "hello",method = RequestMethod.POST)
    public JSONObject getInfo(@RequestBody JSONObject jsonObject) throws Exception{
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        System.out.println("ceshi");
        jsonObject = demoService.getInfo(jsonObject);
        return jsonObject;
    }
}
