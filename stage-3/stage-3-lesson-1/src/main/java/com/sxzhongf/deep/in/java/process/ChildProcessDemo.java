package com.sxzhongf.deep.in.java.process;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class ChildProcessDemo {
    public static void main(String[] args) throws IOException {

        //IDEA（主进程）-> 启动ChildProcessDemo ->Windows 计算器/ shell 命令
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        if (operatingSystemMXBean.getName().toLowerCase().startsWith("mac")) {
            //显示当前目录信息
            System.out.println("Current System is " + operatingSystemMXBean.getName());
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "ls -las"});
            System.out.print(loadStream(process.getInputStream()));


        } else if (operatingSystemMXBean.getName().toLowerCase().startsWith("windows")) {
            //启动计算器
            Runtime.getRuntime().exec("calc");

        }
    }

    private static String loadStream(InputStream in) throws IOException {
        int ptr = 0;
        in = new BufferedInputStream(in);
        StringBuffer buffer = new StringBuffer();
        while ((ptr = in.read()) != -1) {
            buffer.append((char) ptr);
        }
        return buffer.toString();
    }
}
