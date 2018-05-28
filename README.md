<img src=https://github.com/balsikandar/CrashReporter/blob/master/assets/crash_reporter_banner.png >

# CrashReporter

[![Mindorks](https://img.shields.io/badge/mindorks-opensource-blue.svg)](https://mindorks.com/open-source-projects)
[![Mindorks Community](https://img.shields.io/badge/join-community-blue.svg)](https://mindorks.com/join-community)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CrashReporter-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6190) [![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[ ![Download](https://api.bintray.com/packages/balsikandarnsit/maven/Crash-Reporter/images/download.svg) ](https://bintray.com/balsikandarnsit/maven/Crash-Reporter/_latestVersion)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](https://opensource.org/licenses/Apache-2.0)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/balsikandar/CrashReporter/blob/master/LICENSE)

### CrashReporter is a handy tool to capture app crashes and save them in a file.
Here is an [article](https://blog.mindorks.com/android-debugging-crashreporter-on-duty-f8ecfc63f3c6) related to this library.

### Why CrashReporter? 

While developing features we get crashes and if device is not connected to logcat we miss the crash log. In worst case scenario we might not be able to reproduce the crash and endup wasting effort. This library captures all unhandled crashes and saves them locally on device. I found a problem with other libraries that they capture crashes and then uploads them to server and sometimes few crashes aren't logged to server. That's the purpose of this library use it as a debug feature to capture crashes locally and immediately.

### Run the sample
<img src=https://github.com/balsikandar/CrashReporter/blob/master/assets/crash_reporter_work_flow.gif >

### Crash Reporter APIs

- Track all crashes
- Use Log Exception API to log Exception
- All crashes and exceptions are saved in device
- Choose your own path to save crash reports and exceptions
- Share Instantly crash log with your team with other device data.

### Crash reporter doesn't takes any permission or root access
### Using Crash Reporter Library in your application
add below dependency in your app's gradle
```
compile 'com.balsikandar.android:crashreporter:1.0.9'
```
### If you only want to use Crash reporter in debug builds only add
```
debugCompile 'com.balsikandar.android:crashreporter:1.0.9'
```
Note : If you get error like this "no resource identifier found for attribute 'alpha' in package 'android'" use below dependency. This may happen due to two different versions of design support library as CrashReporter also uses design support library internally.

```
debugCompile ('com.balsikandar.android:crashreporter:1.0.9'){
        exclude group: 'com.android.support', module: 'design'
}
```

## Crash Reporter On Duty
- It'll capture all unhandled crashes and write them to a file in below directory
```
/Android/data/your-app-package-name/files/crashReports
```
- To save crashes in a path of your choice, add below line in onCreate method of your Application class
```
CrashReporter.initialize(this, crashReporterPath);
```
Note: You don't need to call CrashReporter.initialize() if you want logs to be saved in default directory. If you want to use external storage then add storage permission in you manifest file.

```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

### Using log Exception API
### If you want to capture exceptions then you can use below API
for ex :
```
 try{
     //do your stuff
 }catch (Exception e){
     CrashReporter.logException(e);
 }
```
Pass exception thrown in below method

```
logException(Exception exception)
```

### To get default crash reports path
```
CrashUtil.getDefaultPath()
```
you can access all crash/exception log files from this path and upload them to server for your need. Remember it's default path 
if you provide your own path you know where to find the logs...

### TODO
Context awareness to track crashes and fix them.
Identify crashes and categorise them in groups

### Find this project useful ? :heart:
* Support it by clicking the :star: button on the upper right of this page. :v:

### That's it for now

### Contact - Let's connect and share knowledge
- [Twitter](https://twitter.com/balsikandar)
- [Github](https://github.com/balsikandar)
- [Medium](https://medium.com/@balsikandar.nsit)
- [Facebook](https://www.facebook.com/balsikandar)

### License

   ```
   Copyright (C) 2016 Bal Sikandar
   Copyright (C) 2011 Android Open Source Project

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   ```
   ### Contributing to this Repo
   Create a pull request and Dive In.
