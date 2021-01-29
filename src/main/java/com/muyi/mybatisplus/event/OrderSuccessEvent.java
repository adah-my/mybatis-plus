package com.muyi.mybatisplus.event;

import org.springframework.context.ApplicationEvent;

/**
 * 继承ApplicationEvent，自定义事件
 */
public class OrderSuccessEvent extends ApplicationEvent {

    public OrderSuccessEvent(Object source)
    {
        super(source);
    }
}

