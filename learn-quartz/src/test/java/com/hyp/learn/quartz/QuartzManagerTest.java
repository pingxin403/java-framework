package com.hyp.learn.quartz;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.quartz
 * hyp create at 19-12-14
 **/
public class QuartzManagerTest {

    public static String JOB_NAME = "动态任务调度";
    public static String TRIGGER_NAME = "动态任务触发器";


    @Test
    public void job() {
        try {
            System.out.println("【系统启动】开始(每1秒输出一次)...");
            QuartzManager.addJob(JOB_NAME, HelloJob.class, "0/1 * * * * ?");

            Thread.sleep(5000);
            System.out.println("【修改时间】开始(每5秒输出一次)...");
            QuartzManager.modifyJobTime(JOB_NAME, "0/5 * * * * ?");

            Thread.sleep(6000);
            System.out.println("【移除定时】开始...");
            QuartzManager.removeJob(JOB_NAME);
            System.out.println("【移除定时】成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}