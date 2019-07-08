package com.runningsnail.demos;

import com.runningsnail.demos.common.utils.HiLogger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author yongjie created on 2019-06-11.
 */
public class MyCustomJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("执行 MyCustomJob");
    }
}
