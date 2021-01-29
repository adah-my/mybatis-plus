package com.muyi.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan("com.muyi.mybatisplus.mapper")
@SpringBootApplication
public class MybatisPlusApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}
