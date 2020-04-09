package com.lcjian.demo.service;

import com.alibaba.fastjson.JSONObject;

import com.lcjian.demo.dao.HuaBeiSaveDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName HuaBeiSaveService
 * @Description
 * @Author liangcj3
 * @Date 2020/1/14
 **/
@Service
public class HuaBeiSaveService {

    private static Logger logger = LoggerFactory.getLogger(HuaBeiSaveService.class);

    @Autowired
    HuaBeiSaveDao huaBeiSaveDao;

    public JSONObject saveMsg(JSONObject jsonObject) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("RESP_CODE", "2222");
        resultJson.put("RESP_DESC", "失败");
        String tradeId = jsonObject.getString("TRADE_ID");//请求流水号（格式：yyyyMMddHHmmssSSS + 3位随机数字）

        Boolean aBoolean = huaBeiSaveDao.searchMsg(tradeId);
        return resultJson;
    }
}
