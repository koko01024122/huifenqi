package com.example.cxhll.huifenq;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cxhll.huifenq.Activitys.BaseActivity;
import com.example.cxhll.huifenq.Activitys.MesList;
import com.example.cxhll.huifenq.Activitys.NoticeActivity;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;

/**
 * Created by CXHLL on 2016/11/19.
 */

public class ShenheAty extends BaseActivity {
	private Button pass;
	private Button notpass;
	private Button send;
	private TextView state;
	private EditText mess;
	private int id;
	private   String aid;
	private String stage;
	private  int cstage;
	private String TAG="Hometest.class";
	private BroadcastReceiver mbd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shenhe);
		final Intent intent=getIntent();
		id=intent.getIntExtra("id",1);
		setBar("模拟审核",0);
		Log.d(TAG, "开始审核中onCreate:id的值为 "+id);
		aid= String.valueOf(id);
		Log.d(TAG, "onCreate: aid为"+aid);
		Log.d(TAG, "onCreate: "+aid+"id="+id);

		stage =dbHelper.passstage(dbHelper,aid);
		if (stage.equals("")){
			stage="0";
		}
		Log.d(TAG, "onCreate:----------********** "+aid);
		cstage= Integer.parseInt(stage);
		Log.d(TAG, "onCreate: cs为"+cstage);
		pass= (Button) findViewById(R.id.pass_button);
		pass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (cstage<4) {


					aid= String.valueOf(id);

					cstage++;
					stage= String.valueOf(cstage);
					Log.d(TAG, "onClick: stage为"+stage);
					dbHelper.pass(dbHelper, aid, stage);

				}else if (cstage==4){
					cstage++;
					aid= String.valueOf(id);
					stage= String.valueOf(cstage);
					Log.d(TAG, "onClick: stage为"+stage);
					dbHelper.pass(dbHelper, aid, stage);
					dbHelper.update_with(dbHelper,aid);
					dbHelper.create_td(dbHelper,dbHelper.select_price(dbHelper,aid),0);
					Log.d(TAG, "onClick:当前状态为 "+cstage);
					mbd=new BootCompleteReceliver();
					IntentFilter intentFilter=new IntentFilter();
					intentFilter.addAction("ACTIONS_STAR");
					registerReceiver(mbd,intentFilter);
					Intent intent1=new Intent("android.intent.action.ACTIONS_STAR");
					//intent.setAction("ACTIONS_STAR");
				//	intent.putExtra("mess","您的订单通过啦");
					sendBroadcast(intent1);
					finish();
					Log.d(TAG, "onClick: 发送广播");
				}
			}
		});
		notpass= (Button) findViewById(R.id.nopass_button);
		notpass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dbHelper.pass(dbHelper, aid, "0");
				dbHelper.update_edu(dbHelper,intent.getStringExtra("phonenum"),-Float.parseFloat(intent.getStringExtra("price")));
				finish();
			}
		});
		send= (Button) findViewById(R.id.sendmess_button);
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!mess.equals("")){
					dbHelper.create_mess(dbHelper, String.valueOf(id),mess.getText().toString());

					NotificationManager manager = (NotificationManager)
							getSystemService(NOTIFICATION_SERVICE);
					Intent intent1 =new Intent(ShenheAty.this, NoticeActivity.class);
					PendingIntent pi=PendingIntent.getActivity(ShenheAty.this,0,intent1,PendingIntent.FLAG_CANCEL_CURRENT);
					Notification notification=new Notification.Builder(ShenheAty.this)
							.setContentTitle("新消息")
							.setContentText(mess.getText().toString())
							.setSmallIcon(R.drawable.back)
							.setContentIntent(pi)
							.setDefaults(Notification.DEFAULT_ALL)
							.build();
					manager.notify(1,notification);


					finish();


				}
			}
		});
		state= (TextView) findViewById(R.id.shenhe_state);
		mess= (EditText) findViewById(R.id.mess_out);
		}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		try{
		unregisterReceiver(mbd);
		}catch (Exception e){}
	}


}
