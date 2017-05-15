package com.example.cxhll.huifenq.Activitys;

import android.app.Activity;
import android.content.Intent;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;


import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;




import com.example.cxhll.huifenq.Adapters.McItemAdapter;
import com.example.cxhll.huifenq.Adapters.WaresAdapter;


import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;

import com.example.cxhll.huifenq.item.WaresItem;




import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by CXHLL on 2016/11/10.
 */

public class McHome extends BaseActivity {

	/**
	 *
	 * seachedit的提示事件
	 *
	 *  上校鸡块 8*1 百事5*2 黑椒汁 3*1 孜然辣椒 2 骨肉相连3*1 串香鸡柳
	 * 还差一个McItemAdapter
	 */
	private EditText seachedit;
	private RelativeLayout mNewm;



	public static final String TAG = "McHome.class";
	private Activity mContext;
	private ListView recyclerView;
	private ArrayList<WaresItem> lists;
	private WaresAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext=this;
		setContentView(R.layout.mc_home);
		intentX=new Intent(McHome.this,CreateWaresAty.class);
		intentX.putExtra("update","1");
		Log.d(TAG, "onCreate: 设置contex");
		setBar("商品列表",0);
		intentY=new Intent(this,SeachActivity.class);
		intentY.putExtra("info","6");
	setDbHelper(this);
		Log.d(TAG, "onCreate: 设置bar");
		/**
		 * 此处需要从数据库获取数据
		 */
		lists =new ArrayList<WaresItem>();


		lists=dbHelper.selectWares("null",dbHelper);
		recyclerView= (ListView) findViewById(R.id.mc_lists);
		mAdapter=new WaresAdapter(mContext,R.layout.mc_item,lists);
		Log.d(TAG, "onCreate: findlayout");
		Log.d(TAG, "onCreate: setmenu");
		Log.d(TAG, "onCreate: setItemclick");
		RelativeLayout nothing1= (RelativeLayout)findViewById(R.id.ware_nothing);
		recyclerView.setEmptyView(nothing1);
		recyclerView.setAdapter(mAdapter);
		seachedit= (EditText) findViewById(R.id.mc_seach);
		mNewm= (RelativeLayout) findViewById(R.id.mc_add);
		mNewm.setVisibility(View.GONE);




	}


	@Override
	protected void onStart() {

		lists=dbHelper.selectWares("null",dbHelper);

		mAdapter=new WaresAdapter(mContext,R.layout.mc_item,lists);
		recyclerView.setAdapter(mAdapter);
		recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
				Log.d(TAG, "onItemLongClick: 长按");
				return true;
			}
		});
		if (dbHelper.select_power(dbHelper,"rm_edit_comm")==1){
			recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
					WaresItem item=lists.get(i);
					String id=item.getId();
					Intent intent=new Intent(McHome.this,CreateWaresAty.class);
					intent.putExtra("update","0");
					intent.putExtra("id",id);
					Log.d(TAG, "onItemClick: 点了一下");
					startActivity(intent);
				}
			});
		}

		Log.d(TAG, "onStart: 设置完成点击事件");
		//recyclerView.setLayoutManager(linearLayoutManager);
		super.onStart();
	}






}
