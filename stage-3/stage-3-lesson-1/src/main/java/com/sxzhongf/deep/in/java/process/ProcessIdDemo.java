package com.sxzhongf.deep.in.java.process;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class ProcessIdDemo {
    public static void main(String[] args) {
        //Java 9 之前的实现
        getProcessIdBeforeJava9();

        //Java 9之后的实现方式
        getProcessIdInJava9();

        //Java 10中的实现
        getProcessIdInJava10();
    }

    /**
     * Java 10之后的实现方式
     */
    private static void getProcessIdInJava10() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
//        System.out.println("[Java 10 的查询方式]当前进程ID : " + runtimeMXBean.getPid());
    }

    /**
     * Java 9之后的实现方式
     */
    private static void getProcessIdInJava9() {
        long pid = 1L;//ProcessHandle.current().pid();
        System.out.println("[Java 9+ 的查询方式]当前进程ID : " + pid);
    }

    /**
     * Java 9之前的实现方式
     */
    private static void getProcessIdBeforeJava9() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        //52978@bogon
        String name = runtimeMXBean.getName();
        String pid = name.substring(0, name.indexOf("@"));
        System.out.println("[Java 9 之前的查询方式]当前进程ID : " + pid);
    }
}
