package com.yjh.study.ch2高并发工具类.forkJoin.sum;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSum extends RecursiveTask<Integer> {
    private static final int ThresholdValue;
    private int[] arr;
    private int fromIndex;
    private int toIndex;

    public ForkJoinSum(int[] arr, int fromIndex, int toIndex) {
        this.arr = arr;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    protected Integer compute() {
        int sum;
        if (ThresholdValue >= this.toIndex - this.fromIndex) {
            sum = 0;

            try {
                for(int i = this.fromIndex; i <= this.toIndex; ++i) {
                    Thread.sleep(1L);
                    sum += this.arr[i];
                }
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }

            return sum;
        } else {
            sum = (this.toIndex + this.fromIndex) / 2;
            ForkJoinSum left = new ForkJoinSum(this.arr, this.fromIndex, sum);
            ForkJoinSum right = new ForkJoinSum(this.arr, sum + 1, this.toIndex);
            invokeAll(left, right);
            return (Integer)left.join() + (Integer)right.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinSum sum = new ForkJoinSum(MakeArray.getArr(), 0, MakeArray.MaxCount - 1);
        long start = System.currentTimeMillis();
//        同步调用
        pool.invoke(sum);
//        异步调用
//        pool.execute(sum);
        long end = System.currentTimeMillis();
        System.out.println("ForkJoinSum 用时 " + (end - start) + "ms");
    }

    static {
        ThresholdValue = MakeArray.MaxCount / 10;
    }
}
