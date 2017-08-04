package com.balsikandar.crashreporter;

import android.content.Context;

import com.balsikandar.crashreporter.custom.CrashReporterNotInitializedException;
import com.balsikandar.crashreporter.handler.CrashReporterExceptionHandler;
import com.balsikandar.crashreporter.utils.CrashUtil;

public class CrashReporter {

    private static Context applicationContext;

    private CrashReporter() {
        // This class in not publicly instantiable
    }

    public static void initialize(Context context) {
        applicationContext = context;
        setUpExceptionHandler(null);
        if (BuildConfig.DEBUG) {
            CrashUtil.logD("CrashReporter", "your crash report files will be saved in \"android/data/your-app-pkg/crashReports/\" path");
        }
    }

    public static void initialize(Context context, String crashReportPath) {
        applicationContext = context;
        setUpExceptionHandler(crashReportPath);
    }

    public static Context getContext() {
        if (applicationContext == null) {
            try {
                throw new CrashReporterNotInitializedException("Initialize CrashReporter : call CrashReporter.initialize(context, crashReportPath)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return applicationContext;
    }

    private static void setUpExceptionHandler(String crashReportPath) {
        if (!(Thread.getDefaultUncaughtExceptionHandler() instanceof CrashReporterExceptionHandler)) {
            Thread.setDefaultUncaughtExceptionHandler(new CrashReporterExceptionHandler(
                    crashReportPath));
        }
    }

    //LOG Exception APIs
    public static void logException(Exception exception) {
        CrashUtil.logException(null/*pass null for path*/, exception, null/*pass null for tag*/);
    }

    public static void logException(Exception exception, String tag) {
        CrashUtil.logException(null/*pass null for path*/, exception, tag);
    }

    public static void logException(String exceptionMsg) {
        CrashUtil.logException(null/*pass null for path*/, exceptionMsg);
    }

    public static void logException(String exceptionSavePath, Exception exception) {
        CrashUtil.logException(exceptionSavePath, exception, null/*pass null for tag*/);
    }

    public static void logException(String exceptionSavePath, String exceptionMsg) {
        CrashUtil.logException(exceptionSavePath, exceptionMsg);
    }

}
