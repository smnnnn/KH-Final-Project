package com.kh.project.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages="com.kh.project", annotationClass=Mapper.class)
public class MybatisConfiguration {

}
