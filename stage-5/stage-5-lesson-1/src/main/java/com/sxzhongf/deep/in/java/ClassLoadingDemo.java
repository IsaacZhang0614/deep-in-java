package com.sxzhongf.deep.in.java;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * ClassLoadingDemo for 类加载过程演示
 * Class对象可能来自于文件（如.class文件），也可能来自于网络I/O,那么ClassLoader如何将资源变为Class对象？
 * .class文件或者网络I/O都是字节码流（Stream）,ClassLoader能够把二进制流解析为Class对象
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since 2019/6/17
 */
public class ClassLoadingDemo {

    public static void main(String[] args) {

        //获取当前main线程的ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //加载某个类
        //User user = ... -> 第一个指令为 load class ,我们不通过这种方式

        // 当前工程相对路径：stage-5/stage-5-lesson-1
        // 它是相对于根目录，或者说系统 System.${user.dir->当前工作目录}
        // 当前工程绝对路径：${user.dir}/stage-5/stage-5-lesson-1
        // 当前工程classpath：${user.dir}/stage-5/stage-5-lesson-1/target/classes
        // User 类路径：com.sxzhongf.deep.in.java.User
        // User.class 文件路径${user.dir}/stage-5/stage-5-lesson-1/target/classes/stage-5/com/sxzhongf/deep/in/java/User.class

        String className = "com.sxzhongf.deep.in.java.User";
        String classFileName = className.replace(".", "/")
                                        .concat(".class");
        String classPath = System.getProperty("user.dir") + "/stage-5/stage-5-lesson-1/target/classes";
        File classFile = new File(classPath, classFileName);
        System.out.println(classFile.getAbsolutePath());

        // ClassLoader 也是对象，也会被GC管理
        MyClassLoader myClassLoader = new MyClassLoader();

        //定义Class对象
        Class<?> userClass = myClassLoader.defineClass(className, classFile);
        System.out.println("customize class load to get : " + userClass);
    }

    private static class MyClassLoader extends ClassLoader {
        private MyClassLoader() {
            //将当前线程上下文 Classloader作为parent传入父类，这是上下文 ClassLoader一个非常重要的作用和使用场景
            super(Thread.currentThread().getContextClassLoader());
        }

        /**
         * 通过文件->定义某个Class
         */
        private Class<?> defineClass(String name, File classFile) {
            byte[] bytes = loadBytes(classFile);

            //利用ClassLoader defineClass方法来定义 Class
            return super.defineClass(name, bytes, 0, bytes.length);
        }

        private byte[] loadBytes(File classFile) {
            byte[] bytes = null;
            try {
                bytes = FileUtils.readFileToByteArray(classFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bytes;
        }
    }
}
