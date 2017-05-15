package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.WaresItem;
import com.example.cxhll.huifenq.tools.SlideListView2;

import java.util.ArrayList;

/**
 * Created by CXHLL on 2016/11/24.
 */

public class NewOrderWaresAdapter extends ArrayAdapter<WaresItem> {
	private String TAG="RmAty.class";
	private int resourceId;

	ArrayList<WaresItem> ites;
	private SlideListView2 lisrView;

	public NewOrderWaresAdapter(Context context, int resource, ArrayList<WaresItem> object) {
		super(context, resource,object);
		resourceId=resource;


	}

	public int getViewTypeCount(){
		return 2;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WaresItem item=getItem(position);

		View view= LayoutInflater.from(getContext()).inflate(resourceId,null);

		TextView name= (TextView) view.findViewById(R.id.new_order_ware_name);

 //lisrView= (SlideListView2) view.findViewById(R.id.mc_list);
		//relname=品牌+型号+ram+rom+网络、
	//	String reaName=item.getBrand().concat(item.getName()).concat(item.getRam())).concat(item.getModel()).concat(item.getModel());
		String reaName=item.getBrand()+" "+item.getName()+" "+item.getRam()+item.getModel()+item.getMemory();
		name.setText(reaName);
		TextView price= (TextView) view.findViewById(R.id.new_order_ware_price);
		price.setText("￥"+item.getPrice());



			return view;
		}

}
