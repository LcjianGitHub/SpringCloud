package com.lcjian.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface HuaBeiSaveMapper {

    List<Map> searchMsg(@Param("tradeId") String tradeId);
}
