package com.muyi.mybatisplus.controller;

import com.muyi.mybatisplus.common.Result;
import com.muyi.mybatisplus.pojo.MpUserPojo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2测试接口
 */
@EnableSwagger2
@RestController
@Slf4j
@Api(tags = "用户接口")
public class UserController{

    /**
     * 新增用户
     * @param user
     * @return
     */
    @ApiOperation(value = "新增用户")
    @PostMapping("/addUser")
    public Result<MpUserPojo> addUser(@RequestBody MpUserPojo user){
        log.info("user:{}", user);
        return Result.success(user);
    }


    /**
     * 根据用户名和年龄获取用户
     *
     * @param name
     * @param age
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名字", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "age", value = "年龄", dataType = "Integer", paramType = "query", required = true)
    })
    @GetMapping("/getUserByNameAndAge")
    public Result getUserByNameAndAge(@RequestParam String name, @RequestParam Integer age) {
        log.info("name:{}, age:{}", name, age);
        return Result.success(new MpUserPojo());
    }
}
