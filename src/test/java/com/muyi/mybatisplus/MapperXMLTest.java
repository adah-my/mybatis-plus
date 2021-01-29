package com.muyi.mybatisplus;

import com.muyi.mybatisplus.mapper.MpUserMapper;
import com.muyi.mybatisplus.pojo.MpUserPojo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试手写SQL与接口方法是否会自动映射驼峰
 *
 * 在通用Mapper中，接口的驼峰和手写SQL的驼峰分别开启，MyBatis-Plus是一起的
 */
@SpringBootTest
public class MapperXMLTest{

    @Autowired
    private MpUserMapper userMapper;

    @Test
    public void create(){
        MpUserPojo user = new MpUserPojo();
        user.setName("测试驼峰映射");
        user.setAge(66);
        user.setUserType(1);
        userMapper.insert(user);
    }

    @Test
    public void test(){
        // 手写SQL
        MpUserPojo user = userMapper.myGetById(1352088960569462785L);
        System.out.println(user);

        // 接口方法
        MpUserPojo mpUser = userMapper.selectById(1352088960569462785L);
        System.out.println(mpUser);
    }
}