package com.muyi.mybatisplus.service.impl;

import com.muyi.mybatisplus.event.OrderSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 测试SpringBoot的事件监听机制
 */
@Service
public class OrderService {

    @Autowired
    private ApplicationContext applicationContext;

    public void order(){
        // 下单成功
        System.out.println("下单成功...");
        // 发布通知（传入了当前对象）
        applicationContext.publishEvent(new OrderSuccessEvent(this));
        System.out.println("main线程结束...");
    }

}
