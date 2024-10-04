import { NativeModules } from 'react-native';

/**
 * `NotificationModule` interface
 */
export interface INotificationModule {
 showNotification(notification: INotification): Promise<string | boolean |null>;
}

interface INotification {
 notificationId?: number,
 channelId: "INFO" | "LOCATION",
 notificationTitle: string,
 notificationText: string,
 notificationSource?: string,
 recordId?: string
}

/**
 * `NativeModules.NotificationModule`
 */

export const NotificationModule: INotificationModule = {
 ...NativeModules.NotificationModule,

 showNotification: (notification) => {

  if (notification.notificationId == undefined) {
   notification["notificationId"] = Math.ceil(Math.random() * 6900);
  }

  if (notification.notificationSource == undefined) {
   notification["notificationSource"] = "";
  }

  if (notification.recordId == undefined) {
   notification["recordId"] = "";
  }

  return NativeModules.NotificationModule.showNotification(notification["notificationId"], notification["channelId"], notification["notificationTitle"], notification["notificationText"], notification["notificationSource"], notification["recordId"]);
 }
}