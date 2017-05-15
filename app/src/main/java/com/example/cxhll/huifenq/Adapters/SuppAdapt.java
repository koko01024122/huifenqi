package com.example.cxhll.huifenq.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.Supp;

import java.util.ArrayList;

/**
 * Created by CXHLL on 2016/10/29.
 */

public class SuppAdapt extends ArrayAdapter<Supp> {
	Activity activity=new Activity();
	private int resourceID;
	private String TAG="MessAty.class";
	private ImageView img0;
	public SuppAdapt(Context context, int resource, ArrayList<Supp> object) {
		super(context, resource,object);
		resourceID=resource;

	}

	public View getView(int position, View converView, ViewGroup parent){
		Supp supp=getItem(position);
		View view= LayoutInflater.from(getContext()).inflate(resourceID,null);
		TextView text1= (TextView) view.findViewById(R.id.supp_item_text1);
		text1.setText(supp.getText1());


		return view;
	}


}
