package com.runningsnail.demos.activity.timer;

import com.runningsnail.demos.common.utils.HiLogger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author yongjie created on 2019-06-11.
 */
public class MyJob implements Job {

    private static final String TAG = "MyJob";
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        HiLogger.i(TAG,"execute");
    }
}
