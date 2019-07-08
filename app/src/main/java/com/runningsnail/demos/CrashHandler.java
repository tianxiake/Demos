package com.runningsnail.demos;


import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.common.utils.WriteFileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yongjie created on 2019-06-06.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        HiLogger.i(TAG, "error", e);
        writeToFile(t, e);
    }

    private void writeToFile(Thread t, Throwable e) {
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String timeFormat = simpleDateFormat.format(new Date());
        builder.append("=======Exception=======")
                .append('\n')
                .append(timeFormat)
                .append('\n')
                .append(t)
                .append("\n\n")
                .append(e)
                .append('\n');
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            builder.append(stackTraceElement);
            builder.append("\n");
        }
        builder.append("\n\n");

        File file = new File("/sdcard/ocn/test_error.txt");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        WriteFileUtils.writeString(file, true, builder.toString());
    }


}
