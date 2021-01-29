package com.muyi.mybatisplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {

    @Bean
    public Docket productApi(){

        return new Docket(DocumentationType.SWAGGER_2)  // 文档类型，不用理会，就是SWAGGER_2
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.muyi.mybatisplus.controller"))// 接口文档信息设置，抽取apiInfo()进行设置
                .build()
                .pathMapping("/")      // 统一为接口路径添加前缀，比如SpringCloud有些网关配置配置了前缀但Swagger只读取了Controller
                .apiInfo(apiInfo());   // 接口文档信息设置，抽取apiInfo()进行设置
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("一拳有限公司私密文档")
                .contact(new Contact("muyi_code", null, "yangbinghuang@163.com"))
                .description("测试swaggers文档")
                .version("1.0")
                .build();
    }
}
