package com.runningsnail.demos.activity.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsyncTaskTestActivity extends AppCompatActivity {

    private static final String TAG = "AsyncTaskTestActivity";
    @BindView(R.id.pb_progress)
    ProgressBar pbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new AsyncTask<String, Integer, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Boolean doInBackground(String... strings) {
                HiLogger.i(TAG, Arrays.toString(strings));
                for (int i = 1; i <= 100; i++) {
                    publishProgress(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {

                    }
                }
                return true;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                pbProgress.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
            }
        }.execute("hello", "world");
    }
}
