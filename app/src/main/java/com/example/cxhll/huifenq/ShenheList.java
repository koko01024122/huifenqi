package com.example.cxhll.huifenq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cxhll.huifenq.Activitys.BaseActivity;
import com.example.cxhll.huifenq.Activitys.OrderListAty;
import com.example.cxhll.huifenq.Adapters.orderAdapt;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.item.OrderListItem;

import java.util.ArrayList;

/**
 * Created by CXHLL on 2016/12/5.
 */

public class ShenheList extends BaseActivity {
	private ListView shenhelist;
	private orderAdapt oAdp;
	private TextView textView;
	private ArrayList<OrderListItem> odlist;
	private String TAG="ShenheList.class";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shenhetest);
		setBar("模拟审核",0);

		shenhelist= (ListView) findViewById(R.id.shenhe_list);
		try{

			if (dbHelper.select_power(dbHelper,"rm_allorder")==1) {
				odlist=dbHelper.select_orders(dbHelper,1);
				oAdp = new orderAdapt(ShenheList.this, R.layout.orderlist, odlist);
				shenhelist.setAdapter(oAdp);
			}else if(dbHelper.select_power(dbHelper,"rm_allorder")==0){

				if(dbHelper.select_power(dbHelper,"rm_checkorder")==1){
					odlist=dbHelper.select_orders(dbHelper,0);
					oAdp = new orderAdapt(ShenheList.this, R.layout.orderlist, odlist);
					shenhelist.setAdapter(oAdp);
				}
			}
		}catch (NullPointerException e){

		}




		shenhelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				OrderListItem od=odlist.get(i);
				int id=od.getId();
				String phonenum=od.getPhonenum();
				String price=od.getPrice();
				Log.d(TAG, "审核中onItemClick: id是"+id);
				Intent intent =new Intent(ShenheList.this,ShenheAty.class);
				intent.putExtra("id",id);
				intent.putExtra("phonenum",phonenum);
				intent.putExtra("price",price);
				Log.d(TAG, "onItemClick: 00000000"+id+i);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart() {

		try{

			if (dbHelper.select_power(dbHelper,"rm_allorder")==1) {
				odlist=dbHelper.select_orders(dbHelper,1);
				oAdp = new orderAdapt(ShenheList.this, R.layout.orderlist, odlist);
				shenhelist.setAdapter(oAdp);
			}else if(dbHelper.select_power(dbHelper,"rm_allorder")==0){

				if(dbHelper.select_power(dbHelper,"rm_checkorder")==1){
					odlist=dbHelper.select_orders(dbHelper,0);
					oAdp = new orderAdapt(ShenheList.this, R.layout.orderlist, odlist);
					shenhelist.setAdapter(oAdp);
				}
			}
		}catch (NullPointerException e){

		}
		super.onStart();
	}


}
