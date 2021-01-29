package com.muyi.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muyi.mybatisplus.pojo.MpUserPojo;

/**
 * 继承MyBatis-Plus提供的IService接口
 */
public interface MpUserService extends IService<MpUserPojo>{

    void showTransactional();
}