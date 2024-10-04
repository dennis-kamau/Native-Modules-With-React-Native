package com.nativemoduleswithreactnative;

import java.util.*; 
import android.os.Build;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;


public class Notification {

 private Context context;

 public Notification(Context context) {
  this.context = context;
 }

 private void createNotificationChannel(String channelId, String channelName, String channelDescription, Integer notificationPriority) {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
   NotificationChannel channel = new NotificationChannel(channelId, channelName, notificationPriority);
   channel.setDescription(channelDescription);

   NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
   notificationManager.createNotificationChannel(channel);
  }
 }

 public void createNotification(Integer notificationId, String notificationTitle, String notificationText, Map<String, String> notificationChannel, String notificationSource, String recordId) {
  Integer notificationPriority = NotificationManager.IMPORTANCE_DEFAULT;

  if (notificationChannel.get("Priority") == "High") {
   notificationPriority = NotificationManager.IMPORTANCE_HIGH;
  }

  if (notificationChannel.get("Priority") == "Low") {
   notificationPriority = NotificationManager.IMPORTANCE_LOW;
  }

  createNotificationChannel(notificationChannel.get("Id"), notificationChannel.get("Name"), notificationChannel.get("Description"), notificationPriority);

  NotificationCompat.Builder notification = new NotificationCompat.Builder(context, notificationChannel.get("Id"))
   .setSmallIcon(R.mipmap.ic_launcher)
   .setContentTitle(notificationTitle)
   .setContentText(notificationText)
   .setPriority(notificationPriority)
   .setAutoCancel(true);

  NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
  notificationManager.notify(notificationId, notification.build());


 }
}