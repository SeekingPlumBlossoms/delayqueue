package com.yb.delayqueue.config;

public enum TaskStatusEnum {
    /**执行成功*/
    SUCCESS,
    /**执行失败*/
    FAIL,
    /**执行重试*/
    RETRY;
}
