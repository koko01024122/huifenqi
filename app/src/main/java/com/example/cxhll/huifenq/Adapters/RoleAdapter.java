package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.RoleItem;

import java.util.List;

/**
 * Created by CXHLL on 2016/11/28.
 */

public class RoleAdapter extends ArrayAdapter<RoleItem> {
private int	resourceID;
	public RoleAdapter(Context context, int resource,  List<RoleItem> objects) {
		super(context, resource,objects);
		resourceID=resource;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RoleItem item=getItem(position);
		View view= LayoutInflater.from(getContext()).inflate(resourceID,null);
		TextView tv= (TextView) view.findViewById(R.id.role_item);
		tv.setText(item.getRm_name());


		return view;
	}
}
