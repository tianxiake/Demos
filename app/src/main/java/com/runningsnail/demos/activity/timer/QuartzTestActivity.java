package com.runningsnail.demos.activity.timer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.ArrayList;
import java.util.List;

public class QuartzTestActivity extends AppCompatActivity {

    private static final String TAG = "QuartzTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quartz_test);
    }

    @Override
    protected void onResume() {
        super.onResume();


        new Thread() {
            @Override
            public void run() {
                super.run();
                HiLogger.i(TAG, "startThread");
                try {
                    HiLogger.i(TAG, "准备工厂");
                    SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

                    Scheduler sched = schedFact.getScheduler();

                    sched.start();

                    // define the job and tie it to our HelloJob class
                    JobDetail job = JobBuilder.newJob(MyJob.class)
                            .withIdentity("myJob", "group1")
                            .build();

                    // Trigger the job to run now, and then every 40 seconds
                    Trigger trigger = TriggerBuilder.newTrigger()
                            .withIdentity("myTrigger", "group1")
                            .startNow()
                            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInSeconds(40)
                                    .repeatForever())
                            .build();

                    // Tell quartz to schedule the job using our trigger
                    sched.scheduleJob(job, trigger);


//                    // 通过 schedulerFactory 获取一个调度器
//                    SchedulerFactory sf = new StdSchedulerFactory();
//                    Scheduler sched = sf.getScheduler();
//                    sched.start();
//
//                    HiLogger.i(TAG, "准备工厂2");
//                    // 创建 jobDetail 实例，绑定 Job 实现类
//                    // 指明 job 的名称，所在组的名称，以及绑定 job 类
//                    JobDetail job = JobBuilder.newJob(MyJob.class)
//                            .withIdentity("job1", "group1")
//                            .build();
//
//                    HiLogger.i(TAG, "准备工厂3");
//                    // corn 表达式，先立即执行一次，然后每隔 5 秒执行 1 次
//                    Trigger trigger = TriggerBuilder.newTrigger()
//                            .withIdentity("trigger1", "group1")
//                            .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
//                            .build();
//
////                    // 初始化参数传递到 job
////                    job.getJobDataMap().put("myDescription", "Hello Quartz");
////                    job.getJobDataMap().put("myValue", 1990);
////                    List<String> list = new ArrayList<>();
////                    list.add("firstItem");
////                    job.getJobDataMap().put("myArray", list);
//                    HiLogger.i(TAG, "startSchedule1");
//                    // 把作业和触发器注册到任务调度中
//                    sched.scheduleJob(job, trigger);
//                    HiLogger.i(TAG, "startSchedule2");
                    // 启动计划程序（实际上直到调度器已经启动才会开始运行）

//                    // 等待 10 秒，使我们的 job 有机会执行
//                    Thread.sleep(10000);
//
//                    // 等待作业执行完成时才关闭调度器
//                    sched.shutdown(true);
                } catch (Exception e) {
                    HiLogger.e(TAG, "error", e);
                }
            }
        }.start();
    }
}
