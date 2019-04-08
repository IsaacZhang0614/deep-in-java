package com.sxzhongf.deep.in.java.collection;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class QueueAndDequeDemo {
    public static void main(String[] args) {
        Queue<String> queueList = new LinkedList<>();
        Deque<String> dequeList = new LinkedList<>();
        List<String> list = new LinkedList<>();

        queueList.add("1");
        queueList.offer("2");
        queueList.offer("3");
        queueList.offer("4");

        queueList.forEach(System.out::println);
        String header = queueList.poll();
        System.out.println("poll header : " + header);
        queueList.forEach(System.out::println);


        String header2 = queueList.peek();
        System.out.println("peek header : " + header2);
        queueList.forEach(System.out::println);
    }
}
