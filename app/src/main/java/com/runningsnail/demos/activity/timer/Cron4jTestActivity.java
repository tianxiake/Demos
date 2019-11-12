package com.runningsnail.demos.activity.timer;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import it.sauronsoftware.cron4j.Scheduler;
import it.sauronsoftware.cron4j.SchedulingPattern;
import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutionContext;

public class Cron4jTestActivity extends AppCompatActivity implements Runnable {

    private static final String TAG = "Cron4jTestActivity";
    private Scheduler s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cron4j_test);


        s = new Scheduler();
        // Schedule a once-a-minute task.
        s.schedule("* * * * *", this);
        // Starts the scheduler.
        s.start();
        // Will run for ten minutes.
        s.schedule("* * * * *", new Task() {
            @Override
            public void execute(TaskExecutionContext context) throws RuntimeException {
                HiLogger.i(TAG,"task");
            }

        });



    }

    @Override
    public void run() {
        HiLogger.i(TAG, "run");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        s.stop();
    }
}
