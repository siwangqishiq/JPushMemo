package com.xinlan.jpushmemo;

import cn.jpush.android.api.JPushInterface;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Audio;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {// 推送的消息
			Bundle bundle = intent.getExtras();
			String str = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			showPushNotify(str,context);
		}
	}
	
	private void showPushNotify(String str,Context mContext)
	{
		NotificationManager manager= (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification= new Notification(R.drawable.ic_launcher, str, System.currentTimeMillis());
		PendingIntent contentIntent= PendingIntent.getActivity(mContext, 0, new Intent(), 0);
		notification.setLatestEventInfo(mContext, "", str, contentIntent);
		notification.defaults|= Notification.DEFAULT_SOUND;
		notification.defaults|= Notification.DEFAULT_VIBRATE;
		notification.flags= Notification.FLAG_AUTO_CANCEL;
		notification.sound= Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");
		manager.notify(R.drawable.ic_launcher, notification);
	}
}// end class
