package com.example.cxhll.huifenq;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.cxhll.huifenq.Activitys.MesList;
import com.example.cxhll.huifenq.Activitys.OrderListAty;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by CXHLL on 2016/10/15.
 */

public class BootCompleteReceliver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent intent1=new Intent(context,MessRuningService.class);
		context.startService(intent1);
	//	intent.setAction("ACTIONS_STAR");
		NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

		Intent intent3 =new Intent(context, OrderListAty.class);
			PendingIntent pi=PendingIntent.getActivity(context,0,intent3,PendingIntent.FLAG_CANCEL_CURRENT);
		Notification notification=new Notification.Builder(context)
				.setContentTitle("新消息")
				.setContentText("您的订单通过了")
				.setSmallIcon(R.drawable.back)
				.setContentIntent(pi)
				.setDefaults(Notification.DEFAULT_ALL)
				.build();
		manager.notify(1,notification);
		Log.d("Hometest.class", "onReceive:收到了一条广播 ");


	}



}
