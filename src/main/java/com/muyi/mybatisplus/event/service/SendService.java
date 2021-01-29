package com.muyi.mybatisplus.event.service;

import com.muyi.mybatisplus.event.OrderSuccessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 短信服务，监听OrderSuccessEvent,但不用实现ApplicationListener
 */
@Service
public class SendService
{

    /**
     * 发送短信 @EventListener指点监听的事件
     */
    @EventListener(OrderSuccessEvent.class)
    public void sendEvent(Object data){

        System.out.println("发送事件..."+data);
    }
}
