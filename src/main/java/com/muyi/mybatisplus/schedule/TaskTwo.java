package com.muyi.mybatisplus.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@EnableScheduling
public class TaskTwo
{
    /**
     * 一般情况下是没有问题的，甚至你根本没意识到它们其实不是同时开始的~因为你可能都不知道SpringBoot定时任务默认单线程。
     * 但由于每个任务执行时间短且一般需求对时间准确度要求并不特别严格，串行执行慢个2~3秒都可以接受。
     *
     * 但某次项目发布后，同事告诉我他的定时任务不跑了。我看了半天，才发现他没有配置线程池，而且有因为引入了Redis分布式锁，不小心发生了死锁卡在那了，最终导致其他任务都没法启动（串行化）。
     *
     * 解决方法：config配置线程池！！
     */
    @Scheduled(cron = "*/2 * * * * ?")
    public void test1(){
        log.info("============= test1任务启动 ============");
        try
        {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        log.info("============ test1任务结束 =============");
    }

    @Scheduled(cron = "*/2 * * * * ?")
    public void test2(){
        log.info("============= test2任务启动 ============");
        try
        {
            // 模拟远程服务卡死
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        log.info("============ test2任务结束 =============");
    }
    
    @Scheduled(cron = "*/2 * * * * ?")
    public void test3(){
        log.info("============= test3任务启动 ============");
        try
        {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        log.info("============ test3任务结束 =============");
    }
}
