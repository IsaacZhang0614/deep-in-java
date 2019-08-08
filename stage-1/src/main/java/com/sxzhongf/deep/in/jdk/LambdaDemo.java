package com.sxzhongf.deep.in.jdk;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * LambdaDemo for : TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/8
 */
public class LambdaDemo {

    public static void main(String[] args) {

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
        PropertyChangeListener propertyChangeListener2 = LambdaDemo::println;
    }

    @FunctionalInterface
    public interface Action{
        void execute();
    }

    private static void println(Object object){
        System.out.println(object);
    }
}
