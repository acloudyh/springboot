package com.yang.springboot.study;

/**
 * @author Yang Hao
 * @date 2020/7/23
 */
public class ThreadLockTest {
    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    public static void main(String[] args) {
        String s =null;

        //线程-1
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "获取资源 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "等待资源 2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "获取资源 2");
                }
            }
        }, "线程-1").start();

        //线程-2
//        new Thread(() -> {
//            synchronized (resource2) {
//                System.out.println(Thread.currentThread() + "获取资源 2");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread() + "等待资源 1");
//                synchronized (resource1) {
//                    System.out.println(Thread.currentThread() + "获取资源 1");
//                }
//            }
//        }, "线程-2").start();

        // 线程-2 修改成以下  破坏循环等待条件
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "获取资源 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "等待资源 2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "获取资源 2");
                }
            }
        }, "线程-2").start();


    }

}
