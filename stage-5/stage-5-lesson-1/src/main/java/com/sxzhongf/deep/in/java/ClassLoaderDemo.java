package com.sxzhongf.deep.in.java;

import java.util.ServiceLoader;

/**
 * ClassLoaderDemo for 演示ClassLoader
 * System ClassLoader为只读，Application ClassLoader可修改
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since 2019/6/17
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {

        //类对象除 Bootstrap ClassLoader 之外，SystemClassLoader,还有ApplicationClassLoader
        //BootStrapClassLoader 无法获取
        //SystemClassLoader 通过java.lang.ClassLoader.getSystemClassLoader方法获取
        //ApplicationClassLoader 通过线程上下文 java.lang.Thread.getContextClassLoader获取(有可能在parent获取)

        //System ClassLoader
        //Java 8 输出结果：sun.misc.Launcher$AppClassLoader@18b4aac2
        //Java 9 以后输出结果：包名路径改变了：jdk.internal.loader.ClassLoaders$AppClassLoader@18b4aac2
        //ClassLoader.getSystemClassLoader() 只读
        System.out.println("getSystemClassLoader : " + ClassLoader.getSystemClassLoader());

        //Application ClassLoader
        //Java 8 输出结果：sun.misc.Launcher$AppClassLoader@18b4aac2
        //Java 9 以后输出结果：包名路径改变了：jdk.internal.loader.ClassLoaders$AppClassLoader@18b4aac2
        //可修改 Thread.currentThread().getContextClassLoader()
        System.out.println("getContextClassLoader : " + Thread.currentThread().getContextClassLoader());

        //如何实现类隔离，通过修改 Thread 上下文 ClassLoader
//        changeClassLoader()
    }

    /**
     * 如果当前{@link ClassLoader}无法加载User.class类，但是User.class可被newClassLoader加载
     * 此时就可以切换Classloader来实现，类隔离机制
     */
    private static void changeClassLoader(ClassLoader newClassLoader) {
        Thread currentThread = Thread.currentThread();

        //这个newClassLoader可是任意ClassLoader,我们以 SystemClassLoader为例
//        {code}ClassLoader newClassLoader = ClassLoader.getSystemClassLoader(){/code};
        ClassLoader previousClassLoader = currentThread.getContextClassLoader();
        try {
            // 需要申请 setContextClassLoader 安全权限，否则会报SecurityException 异常
            currentThread.setContextClassLoader(newClassLoader);
            //利用新的ClassLoader 来加载类
            //doSomeActions
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            currentThread.setContextClassLoader(previousClassLoader);
        }
    }

    /**
     * 为了兼容或者适配老的ClassLoader代码
     * 比如：
     * JAXB通过线程上线文ClassLoader来实现切换不同SPI
     * 不得已而为之是因为
     * JAXB 1.x 2.x
     * JDK 提供 API 1.x
     * 第三方包实现2.x
     * 就只能通过切换ClassLoader来实现
     */
    private static void loadSomeClasses() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

//        {code}ServiceLoader.load(String.class);{/code}
        try {
            classLoader.loadClass("abc");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

