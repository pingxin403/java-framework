package com.hyp.learn.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.quartz
 * hyp create at 19-12-14
 **/
public class HelloJob implements Job {
    private static Logger log = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        log.error("New Job执行时间: " + printTime);
    }
}
