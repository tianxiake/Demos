package com.runningsnail.demos.activity.downlaod;

import android.os.Bundle;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.liulishuo.okdownload.DownloadContext;
import com.liulishuo.okdownload.DownloadContextListener;
import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import java.io.File;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadTestActivity extends AppCompatActivity {

    private static final String TAG = "DownloadTestActivity";
    @BindView(R.id.tv_download_hint)
    TextView tvDownloadHint;
    private DownloadTask task;
    private DownloadContext context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_test);
        ButterKnife.bind(this);
//        File file = new File("/data/data/com.runningsnail.demos/cache/hello.mp4");
//        HiLogger.i(TAG, "fileLength: %s", file.length());

        final File cacheDir = this.getCacheDir();
        new Thread() {
            @Override
            public void run() {
                super.run();
                downloadTasksInSeries(cacheDir);
            }
        }.start();
//        downloadSingleTask(cacheDir);
//        DownloadContext.Builder builder = new DownloadContext.QueueSet()
//                .setParentPathFile(cacheDir)
//                .setMinIntervalMillisCallbackProcess(150)
//                .setPassIfAlreadyCompleted(false)
//                .commit();
//        builder.bind("http://192.168.222.146:8080/test/test_movie1.mp4");
//        builder.bind("http://192.168.222.146:8080/test/movie2.mp4").addTag(0, "value1");
//        builder.bind("http://192.168.222.146:8080/test/movie3.mp4");
//        builder.bind("http://192.168.222.146:8080/test/dragon.mp3");
//        builder.setListener(new DownloadContextListener() {
//            @Override
//            public void taskEnd(@NonNull DownloadContext context, @NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, int remainCount) {
//                HiLogger.i(TAG,"taskEnd taskEnd remainCount%s:",remainCount);
//            }
//
//            @Override
//            public void queueEnd(@NonNull DownloadContext context) {
//                HiLogger.i(TAG,"queueEnd queueEnd ");
//            }
//        });
//
//        DownloadContext context = builder.build();
//
//        context.startOnParallel(new DownloadListener2(){
//
//            @Override
//            public void taskStart(@NonNull DownloadTask task) {
//                HiLogger.d(TAG, "taskStart");
//            }
//
//            @Override
//            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause) {
//                HiLogger.d(TAG, "taskEnd EndCause:%s realCause:%s", cause, realCause);
//            }
//        });

//// cancel
//        task.cancel();
//
//// execute task synchronized
//        task.execute(listener);

    }

    private void downloadTasksInSeries(File cacheDir) {
        DownloadContext.Builder builder = new DownloadContext.QueueSet()
                .setParentPathFile(cacheDir)
                .setMinIntervalMillisCallbackProcess(150)
                .setAutoCallbackToUIThread(false)
                .setPassIfAlreadyCompleted(false)
                .commit();
        builder.bind("http://192.168.222.146:8080/test/test_movie1.mp4").setTag("000");
        builder.bind("http://192.168.222.146:8080/test/movie2_3.mp4").setTag("111");
        builder.bind("http://192.168.222.146:8080/test/movie3.mp4").setTag("222");
        builder.bind("http://192.168.222.146:8080/test/wind.mp3").setTag("333");
        builder.setListener(new DownloadContextListener() {
            @Override
            public void taskEnd(@NonNull DownloadContext context, @NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, int remainCount) {
                HiLogger.i(TAG, "cause:%s, realCause:%s remainCount:%s", cause, realCause, remainCount);
            }

            @Override
            public void queueEnd(@NonNull DownloadContext context) {
            }
        });
        context = builder.build();
        context.start(new DownloadListener() {
            @Override
            public void taskStart(@NonNull DownloadTask task) {
                HiLogger.d(TAG, "%s taskStart ", task.getTag());
//                BreakpointInfo info = task.getInfo();
//                HiLogger.i(TAG, "info TotalOffset:%s TotalLength:%s", info.getTotalOffset(), info.getTotalLength());
                File file = task.getFile();
                HiLogger.i(TAG, "%s file: %s", task.getTag(), file == null ? "null" : file.length());
//                long length1 = task.getFile().length();
//                HiLogger.i(TAG, "taskStart absolutePath: %s length1:%s", absolutePath, length1);

            }

            @Override
            public void connectTrialStart(@NonNull DownloadTask task, @NonNull Map<String, List<String>> requestHeaderFields) {
                HiLogger.d(TAG, "%s connectTrialStart", task.getTag());

            }

            @Override
            public void connectTrialEnd(@NonNull DownloadTask task, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
                HiLogger.d(TAG, "%s connectTrialEnd %s", task.getTag(), responseCode);
            }

            @Override
            public void downloadFromBeginning(@NonNull DownloadTask task, @NonNull BreakpointInfo info, @NonNull ResumeFailedCause cause) {
                HiLogger.d(TAG, "%s downloadFromBeginning totalLength: %s totalOffset:", task.getTag(), info.getTotalLength(), info.getTotalOffset());
            }

            @Override
            public void downloadFromBreakpoint(@NonNull DownloadTask task, @NonNull BreakpointInfo info) {
                HiLogger.d(TAG, "%s downloadFromBreakpoint total: %s offset: %s", task.getTag(), info.getTotalLength(), info.getTotalOffset());
            }

            @Override
            public void connectStart(@NonNull DownloadTask task, int blockIndex, @NonNull Map<String, List<String>> requestHeaderFields) {
                HiLogger.d(TAG, "%s connectStart", task.getTag());
            }

            @Override
            public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
                HiLogger.d(TAG, "%s connectEnd", task.getTag());
            }

            @Override
            public void fetchStart(@NonNull DownloadTask task, int blockIndex, long contentLength) {
                HiLogger.d(TAG, "%s fetchStart blockIndex:%s contentLength:%s", task.getTag(), blockIndex, contentLength);
            }

            @Override
            public void fetchProgress(@NonNull DownloadTask task, int blockIndex, long increaseBytes) {
                HiLogger.d(TAG, "%s fetchProgress blockIndex:%s increaseBytes:%s", task.getTag(), blockIndex, increaseBytes);
            }

            @Override
            public void fetchEnd(@NonNull DownloadTask task, int blockIndex, long contentLength) {
                HiLogger.d(TAG, "%s fetchEnd blockIndex:%s increaseBytes:%s", task.getTag(), blockIndex, contentLength);
            }

            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause) {
                HiLogger.d(TAG, "%s taskEnd EndCause:%s realCause:%s", task.getTag(), cause, realCause);
                DownloadTask[] tasks = context.getTasks();
                if (task == tasks[tasks.length - 1]) {
                    HiLogger.i(TAG, "%s last task", task.getTag());
                }
            }
        }, true);
    }

    private void downloadSingleTask(final File cacheDir) {
        task = new DownloadTask.Builder("http://192.168.222.146:8080/test/test_movie1.mp4", cacheDir)
                .setFilename("hello.mp4")
                // the minimal interval millisecond for callback progress
                .setMinIntervalMillisCallbackProcess(1000)
                // do re-download even if the task has already been completed in the past.
                .setPassIfAlreadyCompleted(false)
                .build();
//        DownloadTask task2 = new DownloadTask.Builder("http://192.168.222.146:8080/test/test_movie1.mp4", cacheDir)
//                .setFilename("hello.mp4")
//                // the minimal interval millisecond for callback progress
//                .setMinIntervalMillisCallbackProcess(1000)
//                // do re-download even if the task has already been completed in the past.
//                .setPassIfAlreadyCompleted(false)
//                .build();

//        DownloadTask task2 = new DownloadTask.Builder("http://192.168.222.146:8080/test/test_movie1.mp4", cacheDir)
//                .setFilename("hello.world")
//                // the minimal interval millisecond for callback progress
//                .setMinIntervalMillisCallbackProcess(1000)
//                // do re-download even if the task has already been completed in the past.
//                .setPassIfAlreadyCompleted(false)
//                .build();
//        task.execute();
        task.enqueue(new DownloadListener() {
            @Override
            public void taskStart(@NonNull DownloadTask task) {
                HiLogger.d(TAG, "taskStart");
                String absolutePath = task.getFile().getAbsolutePath();
                long length1 = task.getFile().length();
                HiLogger.i(TAG, "taskStart absolutePath: %s length1:%s", absolutePath, length1);

            }

            @Override
            public void connectTrialStart(@NonNull DownloadTask task, @NonNull Map<String, List<String>> requestHeaderFields) {
                HiLogger.d(TAG, "connectTrialStart");

            }

            @Override
            public void connectTrialEnd(@NonNull DownloadTask task, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
                HiLogger.d(TAG, "connectTrialEnd %s", responseCode);
            }

            @Override
            public void downloadFromBeginning(@NonNull DownloadTask task, @NonNull BreakpointInfo info, @NonNull ResumeFailedCause cause) {
                String absolutePath = task.getFile().getAbsolutePath();
                long length1 = task.getFile().length();
                HiLogger.i(TAG, "absolutePath: %s length1:%s", absolutePath, length1);
                HiLogger.d(TAG, "downloadFromBeginning totalLength: %s totalOffset:", info.getTotalLength(), info.getTotalOffset());
                long totalLength = info.getTotalLength();
                File file = new File(cacheDir, "hello.mp4");
                HiLogger.i(TAG, "filePath: %s", file.getAbsolutePath());
                long length = file.length();
                HiLogger.i(TAG, "length:" + length);
                if (totalLength == length) {
                    task.cancel();
                }
            }

            @Override
            public void downloadFromBreakpoint(@NonNull DownloadTask task, @NonNull BreakpointInfo info) {
                HiLogger.d(TAG, "downloadFromBreakpoint total: %s offset: %s", info.getTotalLength(), info.getTotalOffset());
                long totalLength = info.getTotalLength();
                File file = new File(cacheDir, "hello.mp4");
                HiLogger.i(TAG, "filePath: %s", file.getAbsolutePath());
                long length = file.length();
                HiLogger.i(TAG, "length:" + length);
                if (totalLength == length) {
                    task.cancel();
                }
            }

            @Override
            public void connectStart(@NonNull DownloadTask task, int blockIndex, @NonNull Map<String, List<String>> requestHeaderFields) {
                HiLogger.d(TAG, "connectStart");
            }

            @Override
            public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
                HiLogger.d(TAG, "connectEnd");
            }

            @Override
            public void fetchStart(@NonNull DownloadTask task, int blockIndex, long contentLength) {
                HiLogger.d(TAG, "fetchStart blockIndex:%s contentLength:%s", blockIndex, contentLength);
            }

            @Override
            public void fetchProgress(@NonNull DownloadTask task, int blockIndex, long increaseBytes) {
                HiLogger.d(TAG, "fetchProgress blockIndex:%s increaseBytes:%s", blockIndex, increaseBytes);
            }

            @Override
            public void fetchEnd(@NonNull DownloadTask task, int blockIndex, long contentLength) {
                HiLogger.d(TAG, "fetchEnd blockIndex:%s increaseBytes:%s", blockIndex, contentLength);
            }

            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause) {
                HiLogger.d(TAG, "taskEnd EndCause:%s realCause:%s", cause, realCause);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (task != null) {
            task.cancel();
        }
        if (context != null) {
            context.stop();
        }
    }
}
