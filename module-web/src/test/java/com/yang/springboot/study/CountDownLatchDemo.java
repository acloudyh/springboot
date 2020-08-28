package com.yang.springboot.study;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 *
 * 利用它可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行
 *
 * 在多线程协作完成业务功能时，有时候需要等待其他多个线程完成任务之后，主线程才能继续往下执行业务功能
 *
 * await() throws InterruptedException：调用该方法的线程等到构造方法传入的 N 减到 0 的时候，才能继续往下执行；
 *
 * await(long timeout, TimeUnit unit)：与上面的 await 方法功能一致，只不过这里有了时间限制，调用该方法的线程等到指定的 timeout 时间后，不管 N 是否减至为 0，都会继续往下执行；
 *
 * countDown()：使 CountDownLatch 初始值 N 减 1；long getCount()：获取当前 CountDownLatch 维护的值；
 *
 * @author Yang Hao
 * @date 2020/8/28
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        new Thread() {
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在操作业务");
                    Thread.sleep(2000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "操作完成");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        new Thread() {
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在操作业务");
                    Thread.sleep(5000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "操作完成");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        try {
            System.out.println("主线程等待两个线程执行完成之后再继续处理后续工作------------");
            latch.await();
            System.out.println("两个子线程已经处理完成------------");
            System.out.println("此时主线程继续执行------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
