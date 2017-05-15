package com.example.cxhll.huifenq;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

/**
 * Created by CXHLL on 2016/10/15.
 */

public class LongRuningService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}


}
