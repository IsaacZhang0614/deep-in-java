package com.sxzhongf.deep.in.java;

/**
 * ChangeClassLoaderDemo for 演示切换classloader使用场景
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since 2019/6/17
 */
public class ChangeClassLoaderDemo {

    public static void main(String[] args) {
        /**
         * ClassLoader previousClassLoader = Thread.currentThread().getContextClassLoader();
         * previousClassLoader 能够加载User.class V1版本(user-api-1.0.0.jar文件中，在/Users/Isaac/classpath1)
         * User.class V2版本(user-api-2.0.0.jar文件中，在/Users/Isaac/classpath2)
         *
         * 如果想要操作User.class V2版本
         *
         * previousClassLoader ClassPath -> /classpath1
         * newClassLoader ClassPath -> /classpath2
         *
         * 通常 系统或者应用（包括自定义，如：UserClassLoader） ClassLoader 均为 URLClassLoader 子类
         */
    }

    private static void changeClassLoader(ClassLoader newClassLoader) {
        Thread currentThread = Thread.currentThread();
        ClassLoader previousClassLoader = currentThread.getContextClassLoader();
        try {
            // 需要申请 setContextClassLoader 安全权限，否则会报SecurityException 异常
            currentThread.setContextClassLoader(newClassLoader);
            //利用新的ClassLoader 来加载类
            //doSomeActions
            loadUser();
        } catch (SecurityException e) {

        } finally {
            currentThread.setContextClassLoader(previousClassLoader);
        }
    }

    private static void loadUser(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try{
            classLoader.loadClass("User");// 使用V2
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

//V1
class User {

}
//V2
//class User{
//
//}
