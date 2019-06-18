package com.sxzhongf.deep.in.java;

/**
 * ClassLoaderAndClassPathRelationshipDemo for
 * 演示 ClassLoader 与 ClassPath 之间的关系
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since 2019/6/18
 */
public class ClassLoaderAndClassPathRelationshipDemo {

    public static void main(String[] args) {
        //通常在JVM进程中添加参数-verbose:class 查看 加载的Class
        //所在的位置（source）,如：
        //[Loaded com.sxzhongf.deep.in.java.ClassLoaderAndClassPathRelationshipDemo
        //from file:/Users/zhangpan/Documents/promotion/projects/deep-in-java/stage-5/stage-5-lesson-1/target/classes/]

        //第二种实现
        //Bootstrap ClassLoader 加载的Class会抛出 Exception in thread "main" java.lang.NullPointerException
        //因此，getClassLocation(Object.class);getClassLocation(int.class);
//        注释：getClassLocation(Object.class);
//        注释：getClassLocation(int.class);

        //返回路径和-verbose:class一样
        //说明类资源 与 URL 有关联，是否意味着ClassLoader 与 URL 存在关联？
        getClassLocation(ClassLoaderAndClassPathRelationshipDemo.class);
        //
    }

    private static void getClassLocation(Class<?> klass) {
        System.out.printf("类[%s] 资源所在位置：%s\n", klass,
                klass.getProtectionDomain().getCodeSource().getLocation());
    }
}
