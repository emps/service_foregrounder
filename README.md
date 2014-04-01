# Service Foregrounder
======================

Cordova interface for keeping Android applications active while in the background. Gives you a way to start/stop the application from existence in the Android notification pull down by adding the icon from the app and a title/description. It's a nice way to provide information about what the app is doing in the bg as well as an easy way to get back into the app from the notification center.

Currently this only works on Android 4.1(Jelly Bean) and up.

## Installation

1) Copy the contents if **scr/android/com/** to your project's **src/com/** folder.

2) Add the interface to the **onCreate** method of your main activity that extends DroidGap:
```java
appView.addJavascriptInterface(new ServiceForegrounderInterface(this.getContext(), {YourMainActivity}.class, R.drawable.icon), "serviceForegrounder");
```

3) Modify your **AndroidManifest.xml** and add the **android:launchMode** and **android:alwaysRetainTaskState** attributes to your **activity** section with the following values:
```xml
<activity android:launchMode="singleInstance" android:alwaysRetainTaskState="true">
```

## Usage

The **serviceForegrounder** object will be available directly through **window**.

#### Starting

Call start with your title and description.
```javascript
window.serviceForegrounder.start('Title', 'Description');
```

#### Stopping

Call stop with a boolean for weather to actually remove the service from the notification center.
```javascript
window.serviceForegrounder.stop(true);
```

