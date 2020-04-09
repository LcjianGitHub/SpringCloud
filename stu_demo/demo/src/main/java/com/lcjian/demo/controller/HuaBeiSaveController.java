package com.lcjian.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lcjian.demo.service.HuaBeiSaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HuaBeiSaveController
 * @Description  接收相关触点的活动信息反馈
 * @Author liangcj3
 * @Date 2020/1/14
 **/
@RestController
@Api("花呗数据接收接口")
public class HuaBeiSaveController {
    private static Logger logger = LoggerFactory.getLogger(HuaBeiSaveController.class);

    @Autowired
    private HuaBeiSaveService huaBeiSaveService;

    @ApiOperation("花呗数据接收接口")
    @RequestMapping(value = "/save/hb-product", method = {RequestMethod.POST})
    public JSONObject SaveProduct(@RequestBody JSONObject object) {
        logger.info("花呗入参是:{}", object);
        object = huaBeiSaveService.saveMsg(object);
        logger.info("花呗出参是:{}", object);
        return object;
    }
}
