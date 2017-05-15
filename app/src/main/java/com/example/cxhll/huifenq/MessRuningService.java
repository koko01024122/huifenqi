package com.example.cxhll.huifenq;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * Created by CXHLL on 2016/10/15.
 */

public class MessRuningService extends Service {
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;

	}
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Log.d("LongRuningService", "runtime__________________: "+new Date().toString());
			}
		}).start();
		AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
		int anHour=60*60*500;
		long triggerAtTime= SystemClock.elapsedRealtime()+anHour;
		Intent i=new Intent(this,BootCompleteReceliver.class);
		PendingIntent pi=PendingIntent.getBroadcast(this,0,i,0);
		//manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);



		return super.onStartCommand(intent, flags, startId);
	}
}
