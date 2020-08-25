package com.yang.springboot.study;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * corePoolSize 核心线程数量
 * - 即使没有任务执行，核心线程也会一直存活
 * - 线程数小于核心线程时，即使有空闲线程，线程沲也会创建新线程执行任务
 * - 设置allowCoreThreadTimeout=true时，核心线程会超时关闭
 *
 * maximumPoolSize 最大线程
 * - 当所有核心线程都在执行任务，且任务队列已满时，线程沲会创建新线程执行任务
 * - 当线程数=maxPoolSize,且任务队列已满，此时添加任务时会触发RejectedExecutionHandler进行处理
 *
 * keepAliveTime TimeUnit 线程空闲时间
 * - 如果线程数>corePoolSize，且有线程空闲时间达到keepAliveTime时，线程会销毁，直到线程数量=corePoolSize
 * - 如果设置allowCoreThreadTimeout=true时，核心线程执行完任务也会销毁直到数量=0
 *
 * workQueue 任务队列
 * - ArrayBlockingQueue 有界队列，需要指定队列大小
 * - LinkedBlockingQueue 若指定大小则和ArrayBlockingQueue类似，若不指定大小则默认能存储Integer.MAX_VALUE个任务，相当于无界队列，此时maximumPoolSize值其实是无意义的
 * - SynchronousQueue 同步阻塞队列，当有任务添加进来后，必须有线程从队列中取出，当前线程才会被释放，newCachedThreadPool就使用这种队列
 *
 * ThreadPoolExecutor 饱和策略
 * - AbortPolicy 直接抛出异常
 * - CallerRunsPolicy 直接调用新添加runnable.run函数执行任务
 * - DiscardPolicy 直接抛弃任务，什么也不干
 * - DiscardOldestPolicy 抛弃队列中最先加入的任务，然后再添加新任务
 *
 *
 * @author Yang Hao
 * @date 2020/7/29
 */
public class ThreadPoolExecutorDemo {
    //核心线程数 5
    private static final int CORE_POOL_SIZE = 5;

    //最大线程数 10
    private static final int MAX_POOL_SIZE = 10;


    private static final int QUEUE_CAPACITY = 100;

    //线程空闲时间
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i <10; i++) {
            Runnable work = new MyRunnable("" + i);
            executor.execute(work);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("结束所有线程");
    }


}
