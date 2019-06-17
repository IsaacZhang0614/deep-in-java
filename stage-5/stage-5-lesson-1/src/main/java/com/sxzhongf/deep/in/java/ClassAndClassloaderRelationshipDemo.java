package com.sxzhongf.deep.in.java;

/**
 * ClassAndClassloaderRelationshipDemo for
 * {@link Class} & {@link ClassLoader} 之间的关系
 * Class 对象是通过ClassLoader的loadClass()方法加载并获取，相反
 * ClassLoader可以通过 Class的getClassLoader方法获取。
 *
 * Class对象可能来自于文件（如.class文件），也可能来自于网络I/O,那么ClassLoader如何将资源变为Class对象？
 * .class文件或者网络I/O都是字节码流（Stream）,ClassLoader能够把二进制流解析为Class对象
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since 2019/6/17
 */
public class ClassAndClassloaderRelationshipDemo {

    public static void main(String[] args) {
        Class<?> cls = Object.class;
        //获取 Object.class 所加载的ClassLoader
        //Object.class 是被Bootstrap ClassLoader加载，其Java表现形式为null,所以查出来为null,
        getClassLoader(cls);

        //获取原生类型
        //Object.class 是被Bootstrap ClassLoader加载，其Java表现形式为null
        getClassLoader(int.class);

        //获取当前类
        getClassLoader(ClassAndClassloaderRelationshipDemo.class);

        //加载ClassAndClassloaderRelationshipDemo.class的 ClassLoader
        //是否与系统ClassLoader相同
        //结果为 true
        System.out.println(ClassAndClassloaderRelationshipDemo.class.getClassLoader()
                == ClassLoader.getSystemClassLoader());
    }

    private static void getClassLoader(Class klass) {
        System.out.printf("类[%s] 被 %s ClassLoader加载\n", klass.getName(), klass.getClassLoader());
    }
}
