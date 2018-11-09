package com.yjh.study.ch5并发容器.bq;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// 放入DelayQueue的元素，要求实现Delayed接口
public class Item<T> implements Delayed {

    private final long activeTime; //到期时间点，单位纳秒
    private final T data;

    // activeTime 有效时间段,单位毫秒
    public Item(long activeTime, T data) {
        this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime, TimeUnit.MILLISECONDS)
                + System.nanoTime();//将传入的时长转换为超时的时刻
        this.data = data;
    }


    public T getData() {
        return data;
    }

    //    返回元素剩余的过期时间（单位：纳秒）
    @Override
    public long getDelay(TimeUnit unit) {
        long b = unit.convert(activeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        return b;
    }

    @Override
    public int compareTo(Delayed o) {
        long b = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return b == 0 ? 0 : (b > 0) ? 1 : -1;
    }
}
