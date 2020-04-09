package com.lcjian.demo.dao;

import com.lcjian.db.DatabaseContextHolder;
import com.lcjian.db.DatabaseType;
import com.lcjian.demo.mapper.HuaBeiSaveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HuaBeiSaveDao
 * @Description
 * @Author liangcj3
 * @Date 2020/1/14  16:12
 **/
@Repository
public class HuaBeiSaveDao {
    private static Logger logger = LoggerFactory.getLogger(HuaBeiSaveDao.class);
    @Resource
    HuaBeiSaveMapper huaBeiSaveMapper;

    public Boolean searchMsg(String tradeId) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.orderdb);
        logger.info("选择的数据库表： " + DatabaseContextHolder.getDatabaseType());
        List<Map> data = huaBeiSaveMapper.searchMsg(tradeId);
        return data != null && data.size() > 0 ? true : false;
    }

}
