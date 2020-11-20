package com.yang.springboot.study;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest implements Runnable {
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 10, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(2));
    private int maxThreadNo;
    private int threadNo;

    public ThreadPoolTest(int maxThreadNo, int threadNo) {
        this.maxThreadNo = maxThreadNo;
        this.threadNo = threadNo;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 7; i++) {
            ThreadPoolTest threadPoolTest = new ThreadPoolTest(7, i);
            threadPoolExecutor.execute(threadPoolTest);
            System.out.println("线程池队列数" + threadPoolExecutor.getQueue().size() + "|活跃线程数" + threadPoolExecutor.getActiveCount());

        }

        Thread.sleep(4000);
        threadPoolExecutor.execute(new ThreadPoolTest(8, 8));
        System.out.println("线程池队列数" + threadPoolExecutor.getQueue().size() + "|活跃线程数" + threadPoolExecutor.getActiveCount() + "|核心线程数" + threadPoolExecutor.getCorePoolSize());
    }


    @Override
    public void run() {
        try {
            System.out.println("B==================" + Thread.currentThread().getName() + "=======================B");
            long start = System.currentTimeMillis();
            if (threadNo < 5) {
                Thread.sleep(1000 * (maxThreadNo - threadNo));
            } else {
                Thread.sleep(2000);
            }

            System.out.println("线程" + Thread.currentThread().getName() + "|线程自定义序号" + threadNo + "|线程消耗时间" + (System.currentTimeMillis() - start) / 1000);
            System.out.println("E==================" + Thread.currentThread().getName() + "=======================E");
            System.out.println("线程池队列数" + threadPoolExecutor.getQueue().size() + "|活跃线程数" + threadPoolExecutor.getActiveCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
