package com.smart.smartapp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 线程池    参考：https://blog.csdn.net/zzti_erlie/article/details/124059864
 * @author: zhizj
 * @date: 2024/6/28
 */
@SpringBootTest
public class ThreadPoolTests {

    private final static Logger LOGGER = LoggerFactory.getLogger(ThreadPoolTests.class);

    private ReentrantLock lock=new ReentrantLock();

    @Test
    public void testThread() throws InterruptedException {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(1000);
        // corePoolSize核心线程数:线程池任务时候都保留的线程数，即使他们都空闲    keepAliveTime: 非核心线程在空闲时存活时间
        // 四种拒绝策略：1默认放弃且抛出异常
        // 调用者执行 new ThreadPoolExecutor.CallerRunsPolicy()
        // 直接放弃 ThreadPoolExecutor.DiscardPolicy
        // 抛弃最早未执行的任务，来执行当前的任务：threadPoolExecutor.DiscardOldestPolicy
        ExecutorService pool = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, queue, new CustomizableThreadFactory("work-pool"), new ThreadPoolExecutor.AbortPolicy());

        pool.execute(() -> {
            LOGGER.info("---------------hhhh");
        });
        Future<Boolean> submit = pool.submit(() -> {
            LOGGER.info("22222222222");
            return true;
        });

        // 固定线程池 只有核心线程，没有非核心线程，核心线程用完后，所有的任务都放入任务队列中，等待核心线程执行
        Executors.newFixedThreadPool(2);
        // 可款存线程池，核心线程0，队列0，无限多个非核心线程可用于执行任务，非核心线程空闲时间60秒
        Executors.newCachedThreadPool();
        // 单线程池，只有一个线程
        Executors.newSingleThreadExecutor();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        // 固定周期执行任务，如果这个周期内没有执行玩任务，则等待完成再执行下一个任务
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("iii");
        }, 60, 60, TimeUnit.SECONDS);

        // 固定延迟执行任务：前一个任务执行后等待一个固定时间再执行下一个任务
        executorService.scheduleWithFixedDelay(() -> {
            System.out.println("ww");
        }, 60, 60, TimeUnit.SECONDS);

        Thread.sleep(600000);
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();

        myThread.start();
        LOGGER.info("ddddddd");

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        Thread thread1 = new Thread(futureTask);
        thread1.start();
        String result = futureTask.get();
        System.out.println("iii" + result);
//        juc
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.submit(() -> {
            System.out.println(Thread.currentThread().getName());
        });


        ExecutorService service = new ThreadPoolExecutor(3, 5, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        service.execute(()->{
            LOGGER.info("threadpoolexecutor");
        });

        if (lock.tryLock(10,TimeUnit.SECONDS)){
            try {

            }finally {
                lock.unlock();
            }
        }else {

        }

    }

    @Test
    public void testTT(){
        System.out.println("yyyy");
    }

    @Test
    public void testT2(){
        System.out.println("yyyy");
    }

    @Test
    public void testT3(){
        System.out.println("yyyy");
    }

}
