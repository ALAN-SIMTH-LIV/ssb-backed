package com.ssb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池
 */
@Configuration
public class AsyncConfig {

    @Bean("emailTaskExecutor")
    public Executor emailTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3); // 核心线程数
        executor.setMaxPoolSize(5); // 最大线程数
        executor.setQueueCapacity(100); // 队列容量
        executor.setThreadNamePrefix("email-"); // 线程名前缀
        // 拒绝策略
        // 当线程池和队列都满时，由调用方线程（提交任务的线程）直接执行该任务，避免任务被丢弃，同时起到背压限流的作用
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true); // 优雅停机
        executor.setAwaitTerminationSeconds(30); //  优雅停机超时时间
        executor.initialize(); // 初始化线程池
        return executor;
    }
}
