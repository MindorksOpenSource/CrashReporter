package com.balsikandar.crashreporter.utils;

import android.text.TextUtils;
import android.util.Log;

import com.balsikandar.crashreporter.BuildConfig;
import com.balsikandar.crashreporter.CrashReporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CrashUtil {

    private CrashUtil() {
        //this class is not publicly instantiable
    }


    private static String getCrashLogTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    public static void saveCrashReport(String crashReportPath, String crashLog) {
        String crashTime = getCrashLogTime() + "_crash";
        String filename = crashTime + ".txt";

        saveInFile(crashReportPath, crashLog, filename);
    }

    public static void logException(String crashReportPath, String crashLog) {
        String crashTime = getCrashLogTime() + "_exception";
        String filename = crashTime + ".txt";

        saveInFile(crashReportPath, crashLog, filename);
    }

    public static void logException(String crashReportPath, Exception exception, String tag) {

        String crashTime = !TextUtils.isEmpty(tag) ? tag + "_" + getCrashLogTime() : getCrashLogTime();
        String filename = crashTime + "_exception" + ".txt";

        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        exception.printStackTrace(printWriter);
        String crashLog = result.toString();
        printWriter.close();

        saveInFile(crashReportPath, crashLog, filename);
    }

    private static void saveInFile(final String crashReportPath, final String crashLog, final String filename) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String crashLogPath = crashReportPath;

                    //if user sends null path
                    if (TextUtils.isEmpty(crashLogPath)) {
                        crashLogPath = getDefaultPath();
                    }

                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                            crashLogPath + "/" + filename));
                    bufferedWriter.write(crashLog);
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    logD("CrashUtil", "crash report saved in : " + crashLogPath);
                } catch (Exception e) {
                    logE("CrashUtil", e.getMessage());
                    logE("CrashUtil", "you may haven't given storage permission");
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public static String getDefaultPath() {
        String defaultPath = CrashReporter.getContext().getExternalFilesDir(null).getAbsolutePath()
                + File.separator + "crashReports";

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
