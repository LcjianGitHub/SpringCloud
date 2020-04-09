package com.lcjian.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.lcjian.demo.dao.DemoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DemoService {
    private final Logger logger = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    DemoDao demoDao;

    public JSONObject getInfo(JSONObject jsonObject) {
        logger.info("demo开始: " + jsonObject);
        JSONObject result = new JSONObject();
        result.put("RESP_CODE", "1111");
        result.put("RESP_DESC", "失败");
        String numId = jsonObject.getString("num_id");
        Map map = demoDao.getInfo(numId);
//        Map map = new HashMap();
//        map.put("SERIAL_NUMBER","1111");
        String serialNumber = (String) map.getOrDefault("SERIAL_NUMBER", "");
        result.put("SERIAL_NUMBER", serialNumber);
        return result;
    }
}
