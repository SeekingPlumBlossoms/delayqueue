package com.yb.delayqueue.task.base;

import com.yb.delayqueue.config.TaskStatusEnum;

/**
 * 任务处理基类
 * @author yebin
 */
public abstract class BaseDealTask  {
    /**第几圈执行*/
    private int cycleNum;
    /**
     * @param
     * @return
     */
  public abstract  TaskStatusEnum dealTask();

    public int getCycleNum() {
        return cycleNum;
    }

    public BaseDealTask setCycleNum(int cycleNum) {
        this.cycleNum = cycleNum;
        return this;
    }
}
