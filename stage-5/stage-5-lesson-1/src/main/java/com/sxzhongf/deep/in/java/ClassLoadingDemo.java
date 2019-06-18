package com.sxzhongf.deep.in.java;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * ClassLoadingDemo for 类加载过程演示
 * Class对象可能来自于文件（如.class文件），也可能来自于网络I/O,那么ClassLoader如何将资源变为Class对象？
 * .class文件或者网络I/O都是字节码流（Stream）,ClassLoader能够把二进制流解析为Class对象
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since 2019/6/17
 */
public class ClassLoadingDemo {

    public static void main(String[] args) throws ClassNotFoundException {

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

        //利用反射展示当前Class对象的属性
        //屏蔽掉：System.out.println("当前类属性：" + userClass.getDeclaredFields());

        Stream.of(userClass.getDeclaredFields()).forEach(
                field -> {
                    System.out.println("Stream field all path : " + field);
                    System.out.println("Stream field : " + field.getName());
                }
        );

        Class<?> userClassFromThreadContextClassLoader = classLoader.loadClass(className);

        //User.class 被MyClassLoader加载后，是否与线程上下文加载的User.class 一致？
        //答案是不一致
        //这个现象能够解释 Spring 中spring-boot-devtools 模块中的Class != Class问题
        System.out.println(userClass == userClassFromThreadContextClassLoader);

        //重新替换掉当前线程上下文 ClassLoader
        //将myClassLoader 放入 Thread.currentThread().getContextClassLoader()
        Thread.currentThread().setContextClassLoader(myClassLoader);

        //老得线程上下文 ClassLoader 是MyClassLoader的parent(代码如下：super(Thread.currentThread().getContextClassLoader());)
        //由于双亲委派，即使是MyClassLoader重新调用loadClass(String),也不会重新加载
        Class<?> userClassFromMyClassLoader = classLoader.loadClass(className);
        System.out.println(userClass == userClassFromMyClassLoader);

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
            //可用于动态加载
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
