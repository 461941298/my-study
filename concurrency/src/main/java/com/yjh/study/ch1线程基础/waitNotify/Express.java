package com.yjh.study.ch1线程基础.waitNotify;

public class Express {

    private static Object lock = new Object();
    private Long km = 0L;
    private Long oldKm = 0L;

    public Long getKm() {
        return km;
    }

    public void setKm(Long km) {
        this.oldKm = this.km;
        this.km = km;
        synchronized (lock) {
            if(this.km != this.oldKm) {
//                调用前必须先持有锁
//                调用nofify方法本身并不释放锁，而是synchronized代码块执行完才释放锁
                lock.notifyAll();
            }
        }
    }

    public void listenKm() throws InterruptedException {
        synchronized (lock) {
            while (this.km == this.oldKm) {

                System.out.println(Thread.currentThread().getName() + " 将被暂停");
//                调用wait方法时必须要先持有锁， 当wait方法执行时，当前线程放弃锁；当wait方法返回时当前线程重新持有锁
//                使用wait使线程进行等待状态，后面的代码将不能被执行
//                如果锁对象调用了 notify/notifyAll 方法并释放了锁, 或者是等待超时则后面的代码进行执行状态
                lock.wait();
                System.out.println(Thread.currentThread().getName() + " 被通知");
                System.out.println(Thread.currentThread().getName() +" 记录数据库");
            }

        }
    }

}
