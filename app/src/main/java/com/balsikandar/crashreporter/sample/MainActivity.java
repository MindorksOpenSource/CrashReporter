package com.balsikandar.crashreporter.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.balsikandar.crashreporter.CrashReporter;
import com.balsikandar.crashreporter.ui.CrashReporterActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.nullPointer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = null;
                context.getResources();
            }
        });

        findViewById(R.id.indexOutOfBound).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList();
                list.add("hello");
                String crashMe = list.get(2);
            }
        });

        findViewById(R.id.classCastExeption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object x = new Integer(0);
                System.out.println((String)x);

            }
        });

        findViewById(R.id.arrayStoreException).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object x[] = new String[3];
                x[0] = new Integer(0);

            }
        });


        //Crashes and exceptions are also captured from other threads
        findViewById(R.id.catchException).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    context = null;
                    context.getResources();
                } catch (Exception e) {
                    //log caught Exception
                    CrashReporter.logException(e);
                }
            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    context = null;
//                    context.getResources();
//                } catch (Exception e) {
//                    //log caught Exception
//                    CrashReporter.logException(e);
//                }
//
//            }
//        }).start();

        mContext = this;
        findViewById(R.id.crashLogActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mContext, CrashReporterActivity.class);
                startActivity(intent);
            }
        });

    }
}
