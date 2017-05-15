package com.example.cxhll.huifenq.Activitys;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Adapters.Adapt;
import com.example.cxhll.huifenq.Adapters.orderAdapt;
import com.example.cxhll.huifenq.Hometest;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.dao.MessageListHelper;
import com.example.cxhll.huifenq.item.Message;
import com.example.cxhll.huifenq.item.OrderListItem;

import java.util.ArrayList;

/**
 * Created by CXHLL on 2016/11/4.
 */

public class OrderListAty extends BaseActivity {
	private ListView listbody;
	private orderAdapt oAdp;
	private  ArrayList<OrderListItem> odlist;
	private TextView psss;
	private TextView nopass;
	private TextView fahuo;
	private TextView notfahuo;
	private Toolbar toolbar;
	private HuifqDbHelper dbHelper;

	private  TextView liststage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderlistbody);
	setBar("订单列表",0);
		Intent intentx=new Intent(OrderListAty.this,NewOrderAty.class);
		Intent intenty=new Intent(OrderListAty.this,SeachActivity.class);

		setIntents(intentx,intenty);

		setDbHelper(this);
		try{

		listbody= (ListView) findViewById(android.R.id.list);
		odlist=new ArrayList<OrderListItem>();
		if (dbHelper.select_power(dbHelper,"rm_orderm")==0){
			listbody.setVisibility(View.GONE);
		}


			if (dbHelper.select_power(dbHelper,"rm_allorder")==1) {
				odlist=dbHelper.select_orders(dbHelper,1);
				oAdp = new orderAdapt(OrderListAty.this, R.layout.orderlist, odlist);
				listbody.setAdapter(oAdp);
			}else if(dbHelper.select_power(dbHelper,"rm_allorder")==0){

				if(dbHelper.select_power(dbHelper,"rm_checkorder")==1){
					odlist=dbHelper.select_orders(dbHelper,0);
					oAdp = new orderAdapt(OrderListAty.this, R.layout.orderlist, odlist);
					listbody.setAdapter(oAdp);
				}
			}
		}catch (NullPointerException e){

		}



		fahuo= (TextView) findViewById(R.id.pa_pass_orders);//通过订单列表
		liststage= (TextView) findViewById(android.R.id.empty);
		fahuo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try{

					if (dbHelper.select_power(dbHelper,"rm_allorder")==1) {
						odlist=dbHelper.select_ordersstage(dbHelper,"6",1);
						oAdp=new orderAdapt(OrderListAty.this,R.layout.orderlist,odlist);
						listbody.setAdapter(oAdp);
					}else if(dbHelper.select_power(dbHelper,"rm_allorder")==0){

						if(dbHelper.select_power(dbHelper,"rm_checkorder")==1){
							odlist=dbHelper.select_ordersstage(dbHelper,"6",0);
							oAdp=new orderAdapt(OrderListAty.this,R.layout.orderlist,odlist);
							listbody.setAdapter(oAdp);
						}
					}
				}catch (NullPointerException e){

				}


				fahuo.setTextColor(Color.parseColor("#ea4130"));
				nopass.setTextColor(Color.parseColor("#8f8f8f"));
				notfahuo.setTextColor(Color.parseColor("#8f8f8f"));
				psss.setTextColor(Color.parseColor("#8f8f8f"));

				liststage.setText("您没有已收货订单");

			}
		});
		nopass= (TextView) findViewById(R.id.pa_notpass);//未通过订单列表
		nopass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try{

					if (dbHelper.select_power(dbHelper,"rm_allorder")==1) {
						odlist=dbHelper.select_ordersstage(dbHelper,"0",1);
						oAdp=new orderAdapt(OrderListAty.this,R.layout.orderlist,odlist);
						listbody.setAdapter(oAdp);
					}else if(dbHelper.select_power(dbHelper,"rm_allorder")==0){

						if(dbHelper.select_power(dbHelper,"rm_checkorder")==1){
							odlist=dbHelper.select_ordersstage(dbHelper,"0",0);
							oAdp=new orderAdapt(OrderListAty.this,R.layout.orderlist,odlist);
							listbody.setAdapter(oAdp);
						}
					}
				}catch (NullPointerException e){

				}


				liststage.setText("没有被拒订单");
				nopass.setTextColor(Color.parseColor("#ea4130"));
				fahuo.setTextColor(Color.parseColor("#8f8f8f"));
				notfahuo.setTextColor(Color.parseColor("#8f8f8f"));
				psss.setTextColor(Color.parseColor("#8f8f8f"));


			}
		});
		notfahuo= (TextView) findViewById(R.id.pa_makesures);//未通过订单列表
		notfahuo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try{

					if (dbHelper.select_power(dbHelper,"rm_allorder")==1) {
						odlist=dbHelper.select_ordersstage(dbHelper,"5",1);
						oAdp=new orderAdapt(OrderListAty.this,R.layout.orderlist,odlist);
						listbody.setAdapter(oAdp);
					}else if(dbHelper.select_power(dbHelper,"rm_allorder")==0){

						if(dbHelper.select_power(dbHelper,"rm_checkorder")==1){
							odlist=dbHelper.select_ordersstage(dbHelper,"5",0);
							oAdp=new orderAdapt(OrderListAty.this,R.layout.orderlist,odlist);
							listbody.setAdapter(oAdp);
						}
					}
				}catch (NullPointerException e){

				}


				liststage.setText("您没有待发货订单");
				notfahuo.setTextColor(Color.parseColor("#ea4130"));
				fahuo.setTextColor(Color.parseColor("#8f8f8f"));
				nopass.setTextColor(Color.parseColor("#8f8f8f"));
				psss.setTextColor(Color.parseColor("#8f8f8f"));

			}
		});
		psss= (TextView) findViewById(R.id.pa_shenheing);//未通过订单列表
		psss.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try{

					if (dbHelper.select_power(dbHelper,"rm_allorder")==1) {
						odlist=dbHelper.select_ordersstage(dbHelper,"1",1);
						oAdp=new orderAdapt(OrderListAty.this,R.layout.orderlist,odlist);
						listbody.setAdapter(oAdp);
					}else if(dbHelper.select_power(dbHelper,"rm_allorder")==0){

						if(dbHelper.select_power(dbHelper,"rm_checkorder")==1){
							odlist=dbHelper.select_ordersstage(dbHelper,"1",0);
							oAdp=new orderAdapt(OrderListAty.this,R.layout.orderlist,odlist);
							listbody.setAdapter(oAdp);
						}
					}
				}catch (NullPointerException e){

				}


				liststage.setText("您没有正在审核的订单");
				psss.setTextColor(Color.parseColor("#ea4130"));
				fahuo.setTextColor(Color.parseColor("#8f8f8f"));
				notfahuo.setTextColor(Color.parseColor("#8f8f8f"));
				nopass.setTextColor(Color.parseColor("#8f8f8f"));

			}
		});


	listbody.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent intent=new Intent(OrderListAty.this,OrderinfoAty.class);

				try{
				OrderListItem item=odlist.get(i);
				int id=item.getId();
				String name=item.getName();
				String price=item.getPrice();
				String stage=item.getStage();
				String phonenum=item.getPhonenum();
					String paystage=item.getPaystage();
				String addressname=item.getAddress_name();
				String adddressphone=item.getAddress_phoennum();
					String time=item.getTime();
				String mopay=item.getMopay();
				String content=item.getContent();
				String address=item.getAddress_province()+item.getAddress_city()+item.getAddress_area()+item.getAddress_detil();
				String trade_name=item.getTrade_name();
				intent.putExtra("id",String.valueOf(id));
				intent.putExtra("addressname",addressname);
				intent.putExtra("addressphoenum",adddressphone);
				intent.putExtra("o_time",time);
				intent.putExtra("name",name);
					intent.putExtra("paystage",paystage);
				intent.putExtra("address",address);
				intent.putExtra("address_phonenum",phonenum);
				intent.putExtra("trade_name",trade_name);
				intent.putExtra("mopay",mopay);
				intent.putExtra("price",price);
				intent.putExtra("stage",stage);
				intent.putExtra("content",content);
					startActivity(intent);
				}catch (NullPointerException e){

				}
			}
		});



	}





}
