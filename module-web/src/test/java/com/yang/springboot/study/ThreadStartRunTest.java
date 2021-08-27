package com.yang.springboot.study;

/**
 * @author Yang Hao
 * @date 2021-04-21 21:32
 */
public class ThreadStartRunTest implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
//
//    public static void main(String[] args) {
//        ThreadStartRunTest myThread2 = new ThreadStartRunTest();
//        Thread thread = new Thread(myThread2);
//        thread.start();
//        syso();
//    }

    public static void syso() {
        System.out.println("-------我是分割线-------");
    }


    public static void main(String[] args) {
        ThreadStartRunTest myThread2 = new ThreadStartRunTest();
        Thread thread = new Thread(myThread2);
        thread.start();
        syso();
        thread.run();
    }



}