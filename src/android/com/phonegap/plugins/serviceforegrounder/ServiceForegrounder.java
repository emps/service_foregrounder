package com.phonegap.plugins.serviceforegrounder;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class ServiceForegrounder extends Service {

  private final static int NOTIFICATION_ID = 123;
  private final static String TAG = ServiceForegrounder.class.getSimpleName();
  private final IBinder binder = new ServiceForegrounderBinder();

  public ServiceForegrounder()
  {
  }

  public void start(String notificationTitle, String notificationText, Context context, Class<?> activity, int drawable)
  {
    if(notificationTitle == null) notificationTitle = TAG + " TITLE";
    if(notificationText == null) notificationText = TAG + " TEXT";
    Log.d(TAG, "start() title: " + notificationTitle + " text: " + notificationText);
    Intent intent = new Intent(context, activity);
    intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    {
      Notification notification = new Notification.Builder(context)
           .setContentTitle(notificationTitle)
           .setContentText(notificationText)
           .setContentIntent(pendingIntent)
           .setSmallIcon(drawable)
           .build();

      startForeground(NOTIFICATION_ID, notification);
      Log.d(TAG, "######################################################################################");
    }
  }

  public void stop(Boolean removeNotification)
  {
    Log.d(TAG, "stop() removeNotification: " + removeNotification);
    stopForeground(removeNotification);
  }

  public class ServiceForegrounderBinder extends Binder {
    ServiceForegrounder getService() {
      return ServiceForegrounder.this;
    }
  }

  /*
   ************************************************************************************************
   * Overriden Methods
   ************************************************************************************************
   */

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    return Service.START_NOT_STICKY;
  }

  @Override
  public IBinder onBind(Intent intent) {
    return binder;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Log.i(TAG, "onCreate()");
  }

  @Override
  public void onStart(Intent intent, int startId) {
    super.onStart(intent, startId);
    Log.i(TAG, "onStart() startId: " + startId);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.i(TAG, "onDestroy()");
  }
}
