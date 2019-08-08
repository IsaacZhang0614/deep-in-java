package com.sxzhongf.deep.in.jdk;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * SCFPDemo for :
 * S : {@link Supplier}
 * C : {@link Consumer}
 * F ：{@link Function}
 * P : {@link Predicate}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/8
 */
public class SCFPDemo {

    //Supplier模式
    public static void showSupplier() {
        String string = "hello";
        Supplier<String> string2 = () -> "hello";
        Supplier<String> string3 = () -> {
            return "hello";
        };
    }

    //Consumer 模式
    public static void showConsumer() {
//传统的匿名类方式处理写法
        PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                println(evt);
            }
        };

        // Lambda 基本写法
        PropertyChangeListener propertyChangeListener1 = evt -> {
            println(evt);
        };

        //Lambda 缩略写法
        PropertyChangeListener propertyChangeListener2 = SCFPDemo::println;
    }

    // Function 演示
    public  static void showFunction(){
        Function<String,Integer> compare = SCFPDemo::compareTo;
    }
    public static Integer compareTo(String value){
        return value.compareTo("hello");
    }

    public static void main(String[] args) {

    }

    private static void println(Object object) {
        System.out.println(object);
    }
}
