package com.sxzhongf.deep.in.java;

/**
 * ClassObjectDemo for 类对象示例代码
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since 2019/6/17
 */
public class ClassObjectDemo {

    public static void main(String[] args) {

        // 如下定义类对象
        // ClassLoader在加载类的过程（验证是否合法）中，会有去重的操作
        //（为什么验证无感知？运行时验证：.class文件（已经编译结果））
        // 为什么会有去重的操作 ？
        // 1. 双亲委派（类加载以及类存储）
        Class<?> klass = Object.class;
        isPrimitive(klass);
        //原生类型也有类对象
        Class<?> intClass = int.class;
        isPrimitive(intClass);

        //Object.class 和 int.class 均被Bootstrap ClassLoader加载
        //Bootstrap ClassLoader 在Java 9之前，在rt.jar中
        //类对象是除 Bootstrap ClassLoader 之外，SystemClassLoader,还有ApplicationClassLoader

    }

    /**
     * 判断当前类是否为原生类型
     *
     * @param cls {@link Class}
     */
    private static void isPrimitive(Class cls) {
        System.out.printf("类[%s] 是否属于原生类型：%s\n", cls.getName(), cls.isPrimitive());
    }
}
