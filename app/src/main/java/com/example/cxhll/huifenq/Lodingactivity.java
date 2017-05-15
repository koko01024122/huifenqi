package com.example.cxhll.huifenq;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Activitys.BaseActivity;
import com.example.cxhll.huifenq.Activitys.GuideActivity;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;

public class Lodingactivity extends BaseActivity {
	/***
	 * 这部分的数据库操作有些问题，但是大体逻辑是通的，下面的time可以获取到
	 */


private TextView login_huifenqi;
	private String TAG="Lodingactivity.class";
	private  Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lodingactivity);

		setDbHelper(this);


		login_huifenqi= (TextView) findViewById(R.id.login_huifenqi);
		Log.d(TAG, "onCreate: 绑定成功");
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if (msg.what==1){
					setContentView(R.layout.activity_lodingactivity);
				}
				super.handleMessage(msg);
			}
		};

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					Thread.sleep(3000);


				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Log.d(TAG, "onCreate: 数据库dbhelpersuccess");
				String time=dbHelper.star(dbHelper);

				if (time.equals("0")){
					Intent intent=new Intent(Lodingactivity.this,Login.class);

					startActivity(intent);
					finish();
				}else if(time.equals("1")){
					Intent intent=new Intent(Lodingactivity.this,Hometest.class);

					String name=dbHelper.starUser(dbHelper);
					intent.putExtra("name",name);
					startActivity(intent);
					finish();
				}else if (time.equals("3")){
					dbHelper.create_user(dbHelper);
					dbHelper.onetime(dbHelper);
					dbHelper.create_kehu(dbHelper);
					dbHelper.cReate_username(dbHelper);
					dbHelper.lodingcreate(dbHelper);
					Intent intent=new Intent(Lodingactivity.this,GuideActivity.class);
					startActivity(intent);
					dbHelper.updateStar(dbHelper);
					finish();

				}else if (time.equals("4")){

					;

				}

			}
		}).start();

	}

		public void run() {


		}





}



