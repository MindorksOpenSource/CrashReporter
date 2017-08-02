package com.balsikandar.crashreporter.handler;

import com.balsikandar.crashreporter.utils.CrashUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


public class CrashReporterExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler exceptionHandler;

    private String crashReportPath;

    public CrashReporterExceptionHandler(String crashReportPath) {
        this.exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.crashReportPath = crashReportPath;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        e.printStackTrace(printWriter);
        String crashLog = result.toString();
        printWriter.close();


        CrashUtil.saveCrashReport(crashReportPath, crashLog);

        exceptionHandler.uncaughtException(t, e);
    }

}
