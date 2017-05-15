package com.example.cxhll.huifenq.Activitys;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cxhll.huifenq.Adapters.Adapt;
import com.example.cxhll.huifenq.MessRuningService;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.dao.MessageListHelper;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.Message;

import java.util.ArrayList;

public class MesList extends BaseActivity {

private ListView listView;
//	private SimpleAdapter adapter;
	//private ArrayList<HashMap<String,Object>> list;
	private String TAG="MesList.this";
	private ArrayList<Message> list1;
private Adapt adapt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meslistaty_layout);
		setBar("消息中心",2);
		setDbHelper(this);

		listView= (ListView) findViewById(R.id.list_aty);

		if (dbHelper.select_power(dbHelper,"rm_mess")==0){
			listView.setVisibility(View.GONE);
		}


		Log.d(TAG, "数据插入成功");
		//list=new ArrayList<HashMap<String, Object>>();
		list1=new ArrayList<Message>();
		list1=dbHelper.select_mess(dbHelper);
	adapt=new Adapt(MesList.this,R.layout.messitem_layout,list1);
		//listView.setAdapter(adapt);
		Log.d(TAG, "数据获取成功 ");
	/*	adapter=new SimpleAdapter(MesList.this,
				list,
				R.layout.messitem_layout,
				new String[]{"reson","name","state"},
				new int[]{R.id.tv_mess_reson,R.id.tv_mess_name,R.id.tv_mess_state});*/
		listView.setAdapter(adapt);

		if(dbHelper.select_power(dbHelper,"rm_reportmess")==1){

	listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

		Message message=list1.get(i);
			String id= String.valueOf(message.getId());
			String oid=String.valueOf(message.getO_id());
			String content=message.getReson();
			Log.d(TAG, "onItemClick: "+message.getState());
			Intent intent=new Intent(MesList.this,MessAty.class);
			intent.putExtra("id",id);
			intent.putExtra("o_id",oid);
			intent.putExtra("reson",content);
			intent.putExtra("stage",String.valueOf(message.getState()));
			Log.d(TAG, "onItemClick: reson"+content);
				startActivity(intent);


		}
	});
		}else {

		}




	}

	@Override
	protected void onStart() {

		list1=dbHelper.select_mess(dbHelper);

		adapt=new Adapt(MesList.this,R.layout.messitem_layout,list1);
		listView.setAdapter(adapt);

		Log.d(TAG, "onStart: 这东西运行了");
		super.onStart();
	}

	@Override
	protected void onStop() {

		super.onStop();
	}


	/*public ArrayList<HashMap<String, Object>> select(){
		SQLiteDatabase db=messageListHelper.getWritableDatabase();
		Cursor c=db.query("messlist",null,null,null,null,null,null);
		int columnsSize=c.getColumnCount();

		while (c.moveToNext()){
			HashMap<String,Object> map=new HashMap<String,Object>();
			for (int i=0;i<columnsSize;i++){
				map.put("id",c.getString(0));
				map.put("reson",c.getString(1));

				map.put("name",c.getString(2));

				map.put("state",c.getString(3));

			}
			list.add(map);
		}
		return  list;
	}*/

}
