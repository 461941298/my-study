package com.yjh.study.ch3CAS;

import java.util.concurrent.atomic.AtomicStampedReference;

public class UseAtomicStampedReference {

    public static void main(String[] args) throws InterruptedException {

//        AtomicStampedReference使用版本号来解决ABA的问题，如果版本号和期望值不能同时一致那么就不能设置成功

        String oldStr = "yjh";
        final int[] oldStamp = {0};
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(oldStr, oldStamp[0]);

        Thread rightThread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean b = atomicStampedReference.compareAndSet(oldStr, "mm", oldStamp[0], ++oldStamp[0]);
                System.out.println("设置" + b);
                System.out.println(atomicStampedReference.getReference());

            }
        });

        Thread errorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean b = atomicStampedReference.compareAndSet(oldStr, "mm1", oldStamp[0]-1, oldStamp[0]);
                System.out.println("设置" + b);
                System.out.println(atomicStampedReference.getReference());

            }
        });


        rightThread.start();
        rightThread.join();

        errorThread.start();


    }
}
