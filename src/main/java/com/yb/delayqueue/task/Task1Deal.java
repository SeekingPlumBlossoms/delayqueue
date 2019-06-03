package com.yb.delayqueue.task;

import com.yb.delayqueue.config.TaskStatusEnum;
import com.yb.delayqueue.task.base.BaseDealTask;

import javax.sound.midi.Soundbank;
import java.util.Date;

/**
 * @author yebin
 * @version 1.0
 * @className Task1Deal
 * @description 任务1处理
 * @date 2019/5/30 17:03
 **/
public class Task1Deal extends BaseDealTask {
    @Override
    public TaskStatusEnum dealTask() {
        System.out.println("Task1Deal:"+new Date());
        return null;
    }
}
