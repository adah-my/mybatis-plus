package com.muyi.mybatisplus.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 定时任务是实际开发中非常普遍的需求，比如定时统计报表、定时更新用户状态等。
 * 如果你使用SpringBoot开发项目，那么只需加上@EnableScheduling + @Scheduled两个注解即可启用定时任务。
 */
@Slf4j
@Component
//@EnableScheduling
public class TaskOne {

    // cron最实用就是两点：
    // */number表示“每隔...”，是最实用的
    // 逗号表示“或”，比如 8,13,18 表示 8或13或18
    /**
     * cron：定时执行（最常用）
     * 指定cron表达式，一般只要写6位即可，分别代表@Schedule(cron = "秒 分 时 日 月 星期 [年]")，第7位[年]可以不写。    
     *
     * zone
     *
     * fixedDelay：距离上一次结束时间（含义是上一个任务结束n毫秒后开始下一个任务。）
     *
     * fixedDelayString
     *
     * fixedRate：距离上一次开始时间（含义是上一个任务开始5秒后开始下一个任务。）
     * 但很遗憾，SpringBoot默认定时任务是单线程的，如果任务执行时间较短，那么fixRate可以保证每个任务开始时间的间隔稳定性，但如果上一个任务耗时异常，那么下一个任务会被往后顶。
     *
     * fixedRateString
     *
     * initialDelay：启动多少秒后开始首次任务（如果希望推迟首次执行时间，可以用initialDelay指定。）
     *
     * initialDelayString
     *
     * 每隔10秒执行test1()
     */
//    @Scheduled(cron = "*/10 * * * * ?")
    public void test1(){
        log.info("============= test1任务启动 =============");
        try
        {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        log.info("=============== test1任务结束 ============");
    }
}