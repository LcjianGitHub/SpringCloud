package com.lcjian.demo.dao;

import com.lcjian.db.DatabaseContextHolder;
import com.lcjian.db.DatabaseType;
import com.lcjian.demo.mapper.DemoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DemoDao {
    private final Logger logger = LoggerFactory.getLogger(DemoDao.class);

    @Resource
    DemoMapper demoMapper;

    public Map getInfo(String numId) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.orderdb);
        logger.info("选择的数据库表： " + DatabaseContextHolder.getDatabaseType());
        List<Map> maps = demoMapper.getInfo(numId);
        return maps != null && maps.size()>0 ? maps.get(0):new HashMap();
    }
}
