package com.balsikandar.crashreporter.sample;

import android.app.Application;
import android.os.Environment;

import com.balsikandar.crashreporter.CrashReporter;

import java.io.File;

/**
 * Created by bali on 02/08/17.
 */

public class CrashReporterSampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            //initialise reporter with external path
            CrashReporter.initialize(this);

            // test disable notification (call CrashReporterActivity)
            CrashReporter.disableNotification();
        }
    }
}
