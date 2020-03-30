package com.sxzhongf.deep.in.jvm.stage4;


/**
 * TestJHSDB for 使用JHSDB分析代码 staticHolder,instanceHolder,localHolder存放在哪
 * JHSDB 本身对于压缩指针有缺陷，建议测试的时候禁用
 * -Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/3/30
 **/
public class TestJHSDB {

    static class Test{
        static ObjectHolder staticHolder = new ObjectHolder();
        ObjectHolder instanceHolder  = new ObjectHolder();
        void foo(){
            ObjectHolder localHolder = new ObjectHolder();
            System.out.println("Done.");
        }
    }

    public static class ObjectHolder{}

    public static void main(String[] args) {
        Test test = new TestJHSDB.Test();
        test.foo();
    }
}
