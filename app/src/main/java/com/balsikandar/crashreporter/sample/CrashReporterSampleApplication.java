package com.balsikandar.crashreporter.sample;

import android.app.Application;

import com.balsikandar.crashreporter.CrashReporter;

/**
 * Created by bali on 02/08/17.
 */

public class CrashReporterSampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            //initialise reporter
//            CrashReporter.initialize(this);
        }
    }
}
