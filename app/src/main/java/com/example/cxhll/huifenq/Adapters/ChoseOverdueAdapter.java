package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.ChoseOverdueIem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxandpll on 17-2-27.
 */

public class ChoseOverdueAdapter extends ArrayAdapter<ChoseOverdueIem> {
    private static int resourceid;

    public ChoseOverdueAdapter(Context context, int resource, List<ChoseOverdueIem> objects) {
        super(context, resource, objects);
        resourceid = resource;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ChoseOverdueIem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceid, null);

        }
        final CheckBox chose = (CheckBox) convertView.findViewById(R.id.overdue_checkbox);
        TextView name = (TextView) convertView.findViewById(R.id.overdue_chose_name);
        TextView time = (TextView) convertView.findViewById(R.id.overdue_chose_time);
        name.setText(item.getName());
        time.setText("逾期"+item.getTime()+"天");
        ImageView timecolor= (ImageView) convertView.findViewById(R.id.time_color);

        int times= Integer.parseInt(item.getTime());
        if (times<3){
            timecolor.setBackgroundColor(Color.parseColor("#0b5301"));

        }else if (times>3&&times<7){
            timecolor.setBackgroundColor(Color.parseColor("#d99100"));

        }else {
            timecolor.setBackgroundColor(Color.parseColor("#991406"));

        }
        chose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chose.isChecked()) {
                    item.setCheck(true);
                } else if (!chose.isChecked()) {
                    item.setCheck(false);
                }
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chose.isChecked()) {

                    chose.setChecked(false);
                } else if (!chose.isChecked()) {

                    chose.setChecked(true);
                }
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chose.isChecked()) {

                    chose.setChecked(false);
                } else if (!chose.isChecked()) {

                    chose.setChecked(true);
                }
            }
        });

        return convertView;
    }
}
