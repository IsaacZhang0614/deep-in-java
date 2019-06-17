package com.sxzhongf.deep.in.java.memory;

/**
 * Thread Interrupt demo
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class ThreadInterruptDemo {
    public static void main(String[] args) {
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // t2 interrupt state will be set true and is cleared
                if (Thread.interrupted()) {
                    // will be call
                }
            }
        });

        Thread t1 = new Thread(() -> {
            // t1 calls t2's interrupt() method
            t2.interrupt();
            // t2 interrupt state will be set from false to true
        });

        Thread t3 = new Thread(() -> {
            // t2 interrupt state is true
            if (t2.isInterrupted()) {
                // will be call
            }
        });
    }
}
