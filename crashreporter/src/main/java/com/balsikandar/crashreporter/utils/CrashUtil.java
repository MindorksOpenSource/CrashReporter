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
import java.util.Locale;

public class CrashUtil {

    private CrashUtil() {
        //this class is not publicly instantiable
    }


    public static String getTimeFromTimeInMillis(long timeInMillis) {
        //get formatted time "HH:mm:ss" from System.currentTimeMillis()
        long hours = (int) ((timeInMillis / (1000 * 60 * 60)) % 24);
        long minutes = (int) ((timeInMillis / (1000 * 60)) % 60);
        long seconds = (int) ((timeInMillis / 1000) % 60);

        return String.format(Locale.getDefault(), "%02d", hours) + ":"
                + String.format(Locale.getDefault(), "%02d", minutes) + ":"
                + String.format(Locale.getDefault(), "%02d", seconds);
    }

    public static void saveCrashReport(String crashReportPath, String crashLog) {
        String crashTime = getTimeFromTimeInMillis(System.currentTimeMillis());
        String filename = crashTime + ".txt";

        saveInFile(crashReportPath, crashLog, filename);
    }

    public static void logException(String crashReportPath, String crashLog) {
        String crashTime = getTimeFromTimeInMillis(System.currentTimeMillis());
        String filename = crashTime + ".txt";

        saveInFile(crashReportPath, crashLog, filename);
    }

    public static void logException(String crashReportPath, Exception exception, String tag) {
        String crashTime = getTimeFromTimeInMillis(System.currentTimeMillis());
        String filename = crashTime + "_exception" + ".txt";

        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        exception.printStackTrace(printWriter);
        String crashLog = result.toString();
        printWriter.close();

        saveInFile(crashReportPath, crashLog, filename);
    }

    private static void saveInFile(String crashReportPath, String crashLog, String filename) {
        try {
            //if user sends null path
            if (TextUtils.isEmpty(crashReportPath)) {
                crashReportPath = getDefaultPath();
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                    crashReportPath + "/" + filename));
            bufferedWriter.write(crashLog);
            bufferedWriter.flush();
            bufferedWriter.close();

            logD("CrashUtil", "crash report saved in : " + crashReportPath);
        } catch (Exception e) {
            logD("CrashUtil", e.getMessage());
            e.printStackTrace();
        }
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
