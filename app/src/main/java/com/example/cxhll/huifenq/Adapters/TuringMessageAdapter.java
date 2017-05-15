package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.Messages;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7 0007.
 */

public class TuringMessageAdapter extends ArrayAdapter<Messages> {
private static int resourceId;
    public TuringMessageAdapter(Context context, int resource, List<Messages> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    Messages item=getItem(position);
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(resourceId,null);
        }

        TextView left= (TextView) convertView.findViewById(R.id.turing_left);
        TextView right= (TextView) convertView.findViewById(R.id.turing_right);
        if (item.getType()==1){
            left.setText(item.getMessage());
            right.setVisibility(View.GONE);
        }else {
            right.setText(item.getMessage());
            left.setVisibility(View.GONE);
        }
        return convertView;
    }
}
