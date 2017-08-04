<img src=https://raw.githubusercontent.com/balsikandar/CrashReporter/blob/master/assets/crash_reporter_banner.png >

# CrashReporter

[![API](https://img.shields.io/badge/API-9%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=9)
[ ![Download](https://api.bintray.com/packages/balsikandarnsit/maven/Crash-Reporter/images/download.svg) ](https://bintray.com/balsikandarnsit/maven/Crash-Reporter/_latestVersion)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](https://opensource.org/licenses/Apache-2.0)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/balsikandar/CrashReporter/blob/master/LICENSE)

### CrashReporter is a handy tool to capture app crashes and save them in a file.
### Crash Reporter API

- Track all crashes
- Use Log Exception API to log Exception or error messages
- All crashes and exceptions are saved in device
- Choose your own path to save crash reports and exceptions

### Crash reporter doesn't takes any permission or root access
### Using Crash Reporter Library in your application
add below dependency in your app's gradle
```
compile 'com.balsikandar.android:crashreporter:1.0.0'
```
### If you only want to use Crash reporter in debug builds add
```
debugCompile 'com.balsikandar.android:crashreporter:1.0.0'
```
# Crash Reporter On Duty
- It'll capture all unhandled crashes and write them to a file in below directory
```
/Android/data/your-app-package-name/files/crashReports
```
- To save crashes in a path of your choice, add below line in onCreate method of your Application class
```
CrashReporter.initialize(this, crashReporterPath);
```
Note: You don't need to call CrashReporter.initialize() if you want logs to be saved in default directory

### Using log Exception API
### If you want to capture exceptions then you can use below APIs
for ex :
```
 try{
     //do your stuff
 }catch (Exception e){
     CrashReporter.logException(e);
 }
```
You can call any of below APIs in catch block.
```
logException(Exception exception)
```
Pass exception thrown in this method
```
logException(String exceptionMsg) 
```
Log your own error message 
```
logException(String exceptionPath, Exception exception)
```
Provide your own path to save exceptions, passing null will save to default location
```
logException(String exceptionPath, String exceptionMsg)
```
Provide your own path and custome error message

Note : If you want to use external storage then add storage ermission
```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
in you manifest file.

### To get default crash reports path being saved call
```
CrashUtil.getDefaultPath()
```
you can access all crash/exception log files from this path and upload them to server for your need. Remember it's default path 
if you provide your own path you know where to find the logs...
### That's it for now

### Find this project useful ? :heart:
* Support it by clicking the :star: button on the upper right of this page. :v:

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
