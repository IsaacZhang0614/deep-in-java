package com.sxzhongf.deep.in.java.memory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class InitializeObjectPropertiesDemo {
    public static void main(String[] args) {

    }

    public static class Person {
        private final String name;
        private final int age;
        private final Collection<String> tags;

        public Person(String name, int age, Collection<String> tags) {
            this.name = name; //String 是不变的对象（引用类型）
            this.age = age; //age原生型，复制
            this.tags = tags; //Collection 是可变对象（引用类型）
        }
    }

    private static void initializePropertis() {

        List<String> tags = Arrays.asList("A", "B", "C");
        /**
         * Person 对象初始化完成之后，才能被其他线程引用
         * Java 方法参数的特点：
         * 对于对象类型：引用
         * 引用包含：普通对象，数组，集合（Collection,Map）
         * 对用原生类型，复制
         */
        Person person = new Person("Isaac", 30, tags);

        /**
         * 修改第三个元素"C"->"SS"
         * 线程不安全，初始化的时候数据是A，B，C
         * 但是引用对象可被改变
         */
        tags.set(2, "SS");

        Thread thread = new Thread(() -> {
            person.toString();
        });

    }
}
