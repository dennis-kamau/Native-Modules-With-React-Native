
package com.nativemoduleswithreactnative;

import java.util.*; 
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

public class NotificationModule extends ReactContextBaseJavaModule {
 
 private Notification notification = null;

 private Map<String, Map<String, String>> notificationChannels = new HashMap<String, Map<String, String>>();

 public NotificationModule(ReactApplicationContext reactContext) {
  super(reactContext);
  this.notification = new Notification(reactContext);

  initNotificationChannels();
 }

 @Override
 public String getName() {
  return "NotificationModule";
 }

 private void initNotificationChannels() {
  // Priority - High | Default | Low

  Map<String, String> visitsNotifyChannel = new HashMap<String, String>();

  Map<String, String> locationNotifyChannel = new HashMap<String, String>();
  locationNotifyChannel.put("Id", "LOCATION");
  locationNotifyChannel.put("Name", "Location Tracking Notifications");
  locationNotifyChannel.put("Description", "Location tracking status");
  locationNotifyChannel.put("Priority", "Default");

  Map<String, String> infoNotifyChannel = new HashMap<String, String>();
  infoNotifyChannel.put("Id", "INFO");
  infoNotifyChannel.put("Name", "App Info Alerts");
  infoNotifyChannel.put("Description", "App information");
  infoNotifyChannel.put("Priority", "Default");
  
  notificationChannels.put(locationNotifyChannel.get("Id"), locationNotifyChannel);
  notificationChannels.put(infoNotifyChannel.get("Id"), infoNotifyChannel);

 } 

 @ReactMethod
 public void showNotification(Integer notificationId, String channelId, String notificationTitle, String notificationText, String notificationSource, String recordId, Promise promise) {

  try {

   notification.createNotification(notificationId, notificationTitle, notificationText, notificationChannels.get(channelId), notificationSource, recordId);

   promise.resolve(true);
  } catch(Exception e) {
   promise.reject("NotificationModule Error", e);
  }
 }
}

