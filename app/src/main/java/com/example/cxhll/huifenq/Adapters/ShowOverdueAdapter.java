package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.ShowOverdueIem;

import java.util.List;

/**
 * Created by cxandpll on 17-2-27.
 */

public class ShowOverdueAdapter extends ArrayAdapter<ShowOverdueIem> {

    private static int resourceid;

    public ShowOverdueAdapter(Context context, int resource, List<ShowOverdueIem> objects) {
        super(context, resource, objects);
        resourceid = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ShowOverdueIem item=getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceid, null);
        }
        TextView name= (TextView) convertView.findViewById(R.id.overdue_show_name);
        name.setText(item.getName());
        TextView time= (TextView) convertView.findViewById(R.id.overdue_show_time);
        time.setText(item.getTime());
        ImageView timecolor= (ImageView) convertView.findViewById(R.id.time_color);

        int times= Integer.parseInt(item.getTime());
        if (times<3){
                timecolor.setBackgroundColor(Color.parseColor("#0b5301"));

        }else if (times>3&&times<7){
            timecolor.setBackgroundColor(Color.parseColor("#d99100"));

        }else {
            timecolor.setBackgroundColor(Color.parseColor("#991406"));

        }
        name.setText(item.getName());

        time.setText("逾期"+item.getTime()+"天");
        TextView charger= (TextView) convertView.findViewById(R.id.overdue_show_charger);
        if (item.getCharger().equals("noone")){
            charger.setText("未分配");
        }else {
            charger.setText(item.getCharger());
        }

        return convertView;
    }
}
