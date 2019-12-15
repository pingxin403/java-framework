package com.hyp.learn.quartz;

import com.github.pagehelper.Page;
import com.hyp.learn.quartz.entity.JobAndTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.quartz
 * hyp create at 19-12-14
 **/

@RestController("/quartz")
public class QuartzController {
    @ResponseBody
    @RequestMapping(value = "/addjob", method = RequestMethod.POST)
    public void addjob(@RequestParam(value = "jobClassName") String jobClassName) throws Exception {
        setJob(jobClassName);
    }

    public static void setJob(String jobClassName) throws Exception {
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();

        Scheduler sched = sf.getScheduler();

        // 启动调度器
        sched.start();

        switch (jobClassName) {
            case "HelloJob":
                JobDetail job = newJob(HelloJob.class).withIdentity("HelloJob", "group1").build();
                Trigger trigger = newTrigger().withIdentity("HelloJob", "group1").startNow().withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever()).build();
                sched.scheduleJob(job, trigger);
                break;

            case "NewJob":
                JobDetail job2 = newJob(NewJob.class).withIdentity("NewJob", "group1").build();
                Trigger trigger2 = newTrigger().withIdentity("NewJob", "group1").startNow().withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever()).build();
                sched.scheduleJob(job2, trigger2);
                break;

            default:
                break;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/pausejob", method = RequestMethod.POST)
    public void pausejob(@RequestParam(value = "jobClassName") String jobClassName) throws Exception {
        jobPause(jobClassName);
    }

    public static void jobPause(String jobClassName) throws Exception {
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        switch (jobClassName) {
            case "HelloJob":
                sched.pauseJob(JobKey.jobKey("HelloJob", "group1"));
                break;

            case "NewJob":
                sched.pauseJob(JobKey.jobKey("NewJob", "group1"));
                break;

            default:
                break;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/resumejob", method = RequestMethod.POST)
    public void resumejob(@RequestParam(value = "jobClassName") String jobClassName) throws Exception {
        jobresume(jobClassName);
    }

    public static void jobresume(String jobClassName) throws Exception {
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        if (sched != null) {
            switch (jobClassName) {
                case "HelloJob":
                    sched.resumeJob(JobKey.jobKey("HelloJob", "group1"));
                    break;

                case "NewJob":
                    sched.resumeJob(JobKey.jobKey("NewJob", "group1"));
                    break;

                default:
                    break;
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deletejob", method = RequestMethod.POST)
    public void deletejob(@RequestParam(value = "jobClassName") String jobClassName) throws Exception {
        jobdelete(jobClassName);
    }

    public static void jobdelete(String jobClassName) throws Exception {
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        switch (jobClassName) {
            case "HelloJob":
                sched.pauseTrigger(TriggerKey.triggerKey("HelloJob", "group1"));
                sched.unscheduleJob(TriggerKey.triggerKey("HelloJob", "group1"));
                sched.deleteJob(JobKey.jobKey("HelloJob", "group1"));
                break;

            case "NewJob":
                sched.pauseTrigger(TriggerKey.triggerKey("NewJob", "group1"));
                sched.unscheduleJob(TriggerKey.triggerKey("NewJob", "group1"));
                sched.deleteJob(JobKey.jobKey("NewJob", "group1"));
                break;

            default:
                break;
        }

    }

//    @ResponseBody
//    @RequestMapping(value="/queryjob")
//    public Map<String, Object> queryjob(@RequestParam(value="pageNum")Integer pageNum, @RequestParam(value="pageSize")Integer pageSize)
//    {
//        Page<JobAndTrigger> jobAndTrigger = iJobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("JobAndTrigger", jobAndTrigger);
//        map.put("number", jobAndTrigger.getTotal());
//        return map;
//    }

    @ResponseBody
    @RequestMapping(value="/reschedulejob", method = RequestMethod.POST)
    public void rescheduleJob(@RequestParam(value="jobClassName")String jobClassName) throws Exception
    {
        jobreschedule(jobClassName);
    }

    public static void jobreschedule(String jobClassName) throws Exception
    {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, "group1");
        Trigger newTrigger = scheduler.getTrigger(triggerKey);
        scheduler.rescheduleJob(triggerKey, newTrigger);
        scheduler.start();
    }
}
