package com.phonegap.plugins.serviceforegrounder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class ServiceForegrounderInterface {

  private final static String TAG = ServiceForegrounderInterface.class.getSimpleName();
  private Context context;
  private Class<?> activity;
  private int drawable;
  private ServiceForegrounder boundService;
  private String title;
  private String text;

  public ServiceForegrounderInterface(Context ctx, Class<?>act, int draw)
  {
    context = ctx;
    activity = act;
    drawable = draw;
  }

  private ServiceConnection serviceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName className, IBinder service)
    {
      boundService = ((ServiceForegrounder.ServiceForegrounderBinder)service).getService();
      boundService.start(title, text, context, activity, drawable);
    }

    public void onServiceDisconnected(ComponentName className)
    {
      boundService.stop(true);
      boundService = null;
    }
  };

  public void start(String notificationTitle, String notificationText)
  {
    title = notificationTitle;
    text = notificationText;
    if(boundService != null) {
      boundService.start(title, text, context, activity, drawable);
    }
    context.startService(new Intent(context, ServiceForegrounder.class));
    context.bindService(new Intent(context, ServiceForegrounder.class), serviceConnection, Context.BIND_AUTO_CREATE);
  }

  public void stop(Boolean removeNotification)
  {
    context.stopService(new Intent(context, ServiceForegrounder.class));
    context.unbindService(serviceConnection);
  }

}

