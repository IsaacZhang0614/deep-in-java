package com.sxzhongf.deep.in.jvm.stage4;

/**
 * MonitorDeadLock for dead lock demo
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/4/1
 **/
public class MonitorDeadLock {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                new DeadLock(1, 2).dead();
            }, "1-dead-lock-thread" + i).start();
            new Thread(() -> {
                new DeadLock(2, 1).dead();
            }, "2-dead-lock-thread" + i).start();
        }
    }

    static class DeadLock {

        private int a, b;

        public DeadLock(int a, int b) {
            this.a = a;
            this.b = b;
        }

        /**
         * 造成死锁的原因是Integer.valueOf方法出于减少对象创建次数和节省内存的考虑，
         * 会对数值在-128 到 127 之间的Integer对象进行缓存（JVM明确要求，
         * 实际值可根据java.lang.Integer.IntegerCache.high参数设置）
         * 如果valueOf返回的参数在这个范围之内，就直接返回缓存的对象
         */
        public void dead() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println("============> " + a + b);
                }
            }
        }
    }
}
