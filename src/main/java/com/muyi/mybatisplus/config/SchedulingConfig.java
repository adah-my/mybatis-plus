package com.muyi.mybatisplus.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 */
@Slf4j
@Component
public class SchedulingConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(3);
        taskScheduler.setThreadNamePrefix("schedule-task-");
        taskScheduler.setRejectedExecutionHandler(
                new RejectedExecutionHandler() {
                    /**
                     * 自定义线程池拒绝策略（模拟发送告警邮件）
                     */
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        log.info("发送告警邮件======>:嘿沙雕，线上定时任务卡爆了, 当前线程名称为:{}, 当前线程池队列长度为:{}",
                                r.toString(),
                                executor.getQueue().size());
                    }
                });
        taskScheduler.initialize();
        taskRegistrar.setScheduler(taskScheduler);
    }
}

    /**
     * SpringBoot定时任务的不足
     * 其实，SpringBoot定时任务是很鸡肋的：
     *      不支持集群时单节点启动
     *      不支持分片任务
     *      不支持失败重试
     *      动态调整比较繁琐
     *
     */






