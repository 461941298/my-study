package com.yjh.study.ch2高并发工具类.forkJoin.sum;

public class SingleSum {
    public SingleSum() {
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = MakeArray.getArr();
        int sum = 0;
        long start = System.currentTimeMillis();

        for(int i = 0; i < MakeArray.MaxCount; ++i) {
            Thread.sleep(1L);
            sum += arr[i];
        }

        long end = System.currentTimeMillis();
        System.out.println("SingleSum 用时 " + (end - start) + "ms");
    }
}
