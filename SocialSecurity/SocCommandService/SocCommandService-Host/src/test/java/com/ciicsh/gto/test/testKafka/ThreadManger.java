package com.ciicsh.gto.test.testKafka;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManger {

    private ThreadManger(){}

    private static class SingleThreadPool{
        private static ThreadManger threadManger= new ThreadManger();
    }
    // 线程池维护线程的最少数量
    private static final int SIZE_CORE_POOL = 1;

    // 线程池维护线程的最大数量
    private static final int SIZE_MAX_POOL = 15;

    /*
     * 线程池单例创建方法
     */
    public ThreadManger newInstance(){
        return SingleThreadPool.threadManger;
    }
    // 实质就是newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
    private final ThreadPoolExecutor mThreadPool = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, 0L,
        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    /*
    * 将线程池初始化，核心线程数量
    */
    public void perpare() {
        if (mThreadPool.isShutdown() && !mThreadPool.prestartCoreThread()) {
            @SuppressWarnings("unused")
            int startThread = mThreadPool.prestartAllCoreThreads();
        }
    }
}
