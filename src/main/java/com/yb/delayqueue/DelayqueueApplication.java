package com.yb.delayqueue;

import com.yb.delayqueue.task.Task1Deal;
import com.yb.delayqueue.task.Task2Deal;
import com.yb.delayqueue.task.core.TaskDealControl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yebin
 */
@SpringBootApplication
public class DelayqueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(DelayqueueApplication.class, args);

        TaskDealControl taskDealControl = new TaskDealControl();
        taskDealControl.setTask("你好",new Task1Deal(),1);
        taskDealControl.setTask("你好",new Task2Deal(),10);
        taskDealControl.execute();

    }

}
