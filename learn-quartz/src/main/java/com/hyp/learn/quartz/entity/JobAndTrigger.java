package com.hyp.learn.quartz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.quartz.entity
 * hyp create at 19-12-14
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobAndTrigger {
    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String triggerName;
    private String triggerGroup;
    private Long pepeatInterval;
    private Long timesTriggered;
}
