package com.yang.springboot.study;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author Yang Hao
 * @date 2020/7/27
 */
public class ThreadTest {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[ " + threadInfo.getThreadId() + " ] " + threadInfo.getThreadName());
        }
    }
}



