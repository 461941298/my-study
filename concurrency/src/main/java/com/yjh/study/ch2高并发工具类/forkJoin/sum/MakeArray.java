package com.yjh.study.ch2高并发工具类.forkJoin.sum;

import java.util.Random;

public class MakeArray {
    public static int MaxCount = 5000;
    private static int[] arr;

    public MakeArray() {
    }

    public static int[] getArr() {
        arr = new int[MaxCount];
        Random r = new Random();
        int range = MaxCount * 4;

        for(int i = 0; i < MaxCount; ++i) {
            arr[i] = r.nextInt(range);
        }

        return arr;
    }
}