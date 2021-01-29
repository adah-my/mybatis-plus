package com.muyi.mybatisplus.event.service;

import com.muyi.mybatisplus.event.OrderSuccessEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * 短信服务，监听OrderSuccessEvent
 */
@Service
public class EmailService implements ApplicationListener<OrderSuccessEvent> {

    @Override
    public void onApplicationEvent(OrderSuccessEvent orderSuccessEvent)
    {
        this.sendSms();
    }

    private void sendSms()
    {
        System.out.println("发送邮件...");
    }
}