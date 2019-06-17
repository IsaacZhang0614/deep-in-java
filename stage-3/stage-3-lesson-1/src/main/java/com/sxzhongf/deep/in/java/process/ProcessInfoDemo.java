package com.sxzhongf.deep.in.java.process;

import java.lang.management.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class ProcessInfoDemo {
    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

        long pid = ProcessHandle.current().pid();

        System.out.println("[Java 9+ 的查询方式]当前进程ID : " + pid);

        Instant instant = Instant.ofEpochMilli(runtimeMXBean.getStartTime());
        LocalDateTime localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("当前进程启动时间 : " + localDate);
        System.out.println("当前线程上线时间 : " + runtimeMXBean.getUptime());
        System.out.println("当前线程活跃数量 : " + threadMXBean.getThreadCount());

        //获取内存使用情况
        ManagementFactory.getMemoryManagerMXBeans().forEach(memoryManagerMXBean -> {
            System.out.println(memoryManagerMXBean.getName());
        });

        //退出当前线程
        System.exit(0);

    }
}
