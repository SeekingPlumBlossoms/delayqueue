package com.yb.delayqueue.task.core;

import com.yb.delayqueue.task.base.BaseDealTask;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yebin
 * @version 1.0
 * @className TaskDealControl
 * @description 任务调度中心
 * @date 2019/5/30 14:58
 **/
public class TaskDealControl<T extends BaseDealTask> {

    /**
     * 延时任务集,环上每个slot(插槽)对应一个Set
     */
    private static final Set[] cycleTasks = new Set[3600];
    /**
     * 当前循环轮数
     */
    private AtomicInteger CYCLE_INDEX = new AtomicInteger(0);

    /**
     * 当前循环轮下标
     */
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * 任务调度线程池
     */
    private final static ExecutorService executorService = Executors.newFixedThreadPool(8);

    /**
     * 延时环状队列步进线程池
     */
    private static final ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(8);

    public boolean setTask(String messages, T t, int delay) {
        Set taskSet = cycleTasks[delay];
        t.setCycleNum(0);
        taskSet.add(t);
        cycleTasks[delay] = taskSet;
        System.out.println(taskSet.size());
        return true;
    }

    public void execute() {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // 取当前延时对应的slot
                int currentCycleIndex = count.intValue();
                if (currentCycleIndex < 3600) {
                    System.out.println(currentCycleIndex);
                    int currentIndex = currentCycleIndex;
                    Set<T> set = cycleTasks[currentIndex];
                    if (set != null && set.size() > 0) {
                        // 存在任务，进行调度
                        System.out.println("调用开始");
                        for (T t : set) {
                            if (t.getCycleNum() == CYCLE_INDEX.intValue()) {
                                executorService.submit(new Runnable() {
                                    @Override
                                    public void run() {
                                        t.dealTask();
                                    }
                                });
                            }
                        }
                    }
                    // 增加index
                    count.getAndIncrement();
                } else {
                    count.set(0);
                    // 循环轮数增加
                    CYCLE_INDEX.incrementAndGet();
                }
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    /**
     * 初始化数据
     */
     public  TaskDealControl() {
        for (int i = 0; i < 3600; i++) {
            cycleTasks[i] = new HashSet();
        }
        System.out.println("初始化完成");
    }

}
