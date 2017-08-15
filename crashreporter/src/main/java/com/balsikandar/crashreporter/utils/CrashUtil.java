package com.balsikandar.crashreporter.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.balsikandar.crashreporter.BuildConfig;
import com.balsikandar.crashreporter.CrashReporter;
import com.balsikandar.crashreporter.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.NOTIFICATION_SERVICE;

public class CrashUtil {

    private static final String TAG = CrashUtil.class.getSimpleName();

    private CrashUtil() {
        //this class is not publicly instantiable
    }

    private static String getCrashLogTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    public static void saveCrashReport(final Thread.UncaughtExceptionHandler exceptionHandler,
                                       final Thread thread, final Throwable throwable) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String crashReportPath = CrashReporter.getCrashReportPath();
                    String filename = getCrashLogTime() + Constants.CRASH_SUFFIX + Constants.FILE_EXTENSION;
                    writeToFile(crashReportPath, filename, getStackTrace(throwable));

                    showNotification(throwable.getLocalizedMessage(), true);

                } catch (Exception e) {
                    logE(TAG, e.getMessage());
                    logE(TAG, "you may haven't given storage permission");
                    e.printStackTrace();
                } finally {
                    exceptionHandler.uncaughtException(thread, throwable);
                }
            }
        }).start();

    }

    public static void logException(final Exception exception) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String crashReportPath = CrashReporter.getCrashReportPath();
                    final String filename = getCrashLogTime() + Constants.EXCEPTION_SUFFIX + Constants.FILE_EXTENSION;
                    writeToFile(crashReportPath, filename, getStackTrace(exception));

                    showNotification(exception.getLocalizedMessage(), false);

                } catch (Exception e) {
                    logE(TAG, e.getMessage());
                    logE(TAG, "you may haven't given storage permission");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void writeToFile(String crashReportPath, String filename, String crashLog)
            throws IOException {
        //if user sends null path
        if (TextUtils.isEmpty(crashReportPath)) {
            crashReportPath = getDefaultPath();
        }

        File crashDir = new File(crashReportPath);
        if (!crashDir.isDirectory()) {
            crashReportPath = getDefaultPath();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                crashReportPath + File.separator + filename));
        bufferedWriter.write(crashLog);
        bufferedWriter.flush();
        bufferedWriter.close();

        logD(TAG, "crash report saved in : " + crashReportPath);
    }

    private static void showNotification(String localisedMsg, boolean isCrash) {

        if (CrashReporter.isNotificationEnabled()) {
            Context context = CrashReporter.getContext();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.ic_warning_black_24dp);

            Intent intent = CrashReporter.getLaunchIntent();
            intent.putExtra(Constants.LANDING, isCrash);
            intent.setAction(Long.toString(System.currentTimeMillis()));

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentIntent(pendingIntent);

            builder.setContentTitle(context.getString(R.string.view_crash_report));

            if (TextUtils.isEmpty(localisedMsg)) {
                builder.setContentText(context.getString(R.string.check_your_message_here));
            } else {
                builder.setContentText(localisedMsg);
            }

            builder.setAutoCancel(true);
            builder.setColor(ContextCompat.getColor(context, R.color.colorAccent_CrashReporter));

            NotificationManager notificationManager = (NotificationManager) context.
                    getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(Constants.NOTIFICATION_ID, builder.build());
        }
    }

    private static String getStackTrace(Throwable e) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        e.printStackTrace(printWriter);
        String crashLog = result.toString();
        printWriter.close();
        return crashLog;
    }

    public static String getDefaultPath() {
        String defaultPath = CrashReporter.getContext().getExternalFilesDir(null).getAbsolutePath()
                + File.separator + Constants.CRASH_REPORT_DIR;

        File file = new File(defaultPath);
        file.mkdirs();
        return defaultPath;
    }

    public static void logE(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void logD(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }
}
