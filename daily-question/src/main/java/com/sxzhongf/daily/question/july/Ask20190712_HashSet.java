package com.sxzhongf.daily.question.july;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

/**
 * Ask20190712_HashSet for TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/7/12
 */
public class Ask20190712_HashSet {
    public static void main(String[] args) throws Exception {
        Set s = new HashSet();
        s.add("foo");
        Iterator i = s.iterator();
        Method m = i.getClass().getMethod("hasNext", new Class[0]);
//        m.setAccessible(true); //设置可访问HashSet内部成员
        //XXX.class 是确定的， getClass() 是运行期才知道，所以这个类的访问性是不确定的
        Method m2 = Iterator.class.getMethod("hasNext", new Class[0]);
//        System.out.println(m.invoke(i, new Object[0]));
        System.out.println(m2.invoke(i, new Object[0]));
    }
}

