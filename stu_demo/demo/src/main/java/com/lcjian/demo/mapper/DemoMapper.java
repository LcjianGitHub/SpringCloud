package com.lcjian.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DemoMapper {
    List<Map> getInfo(@Param("numId") String numId);
}
