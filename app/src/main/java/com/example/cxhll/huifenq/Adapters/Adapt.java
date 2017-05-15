package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cxhll.huifenq.item.Message;
import com.example.cxhll.huifenq.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by CXHLL on 2016/10/17.
 */

public class Adapt extends ArrayAdapter<Message> {

    private int resourceId;

    public Adapt(Context context, int resource, ArrayList<Message> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
        }
        Message message = getItem(position);

        TextView name = (TextView) convertView.findViewById(R.id.tv_mess_name);
        TextView reson = (TextView) convertView.findViewById(R.id.tv_mess_reson);
        TextView state = (TextView) convertView.findViewById(R.id.tv_mess_state);
        TextView statepass = (TextView) convertView.findViewById(R.id.tv_mess_state_pass);
        TextView time = (TextView) convertView.findViewById(R.id.mess_time);
        name.setText(message.getName());
        reson.setText(message.getReson());

        if (message.getState() == 0) {
            state.setVisibility(View.VISIBLE);

            statepass.setVisibility(View.GONE);
        } else if (message.getState() == 1) {
            state.setVisibility(View.GONE);
            statepass.setVisibility(View.VISIBLE);
            statepass.setText("初审中");
        } else if (message.getState() == 2) {
            state.setVisibility(View.GONE);
            statepass.setVisibility(View.VISIBLE);
            statepass.setText("电审中");
        } else if (message.getState() == 3) {
            state.setVisibility(View.GONE);
            statepass.setVisibility(View.VISIBLE);
            statepass.setText("复审中");
        } else if (message.getState() == 4) {
            state.setVisibility(View.GONE);
            statepass.setVisibility(View.VISIBLE);
            statepass.setText("终审中");
        } else if (message.getState() == 5) {
            state.setVisibility(View.GONE);
            statepass.setVisibility(View.VISIBLE);
            statepass.setText("已通过");
        } else if (message.getState() == 9) {
            state.setVisibility(View.GONE);
            statepass.setVisibility(View.VISIBLE);
            statepass.setText("待签约");
        }
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String shijian = message.getM_time();
        try {
            Date times = date.parse(shijian);

            Calendar calendar = Calendar.getInstance();

            int M = calendar.get(Calendar.MONTH) + 1;
            int Year = calendar.get(Calendar.YEAR);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.setTime(times);
            int theonth = calendar.get(Calendar.MONTH) + 1;
            if (Year == calendar.get(Calendar.YEAR)) {
                if (M == theonth) {
                    if (day == calendar.get(Calendar.DAY_OF_MONTH)) {
                        if (calendar.get(Calendar.MINUTE) < 10) {
                            time.setText("今天" + calendar.get(Calendar.HOUR_OF_DAY) + ":0" + calendar.get(Calendar.MINUTE));
                        } else {
                            time.setText("今天" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
                        }

                    } else if (day > calendar.get(Calendar.DAY_OF_MONTH)) {
                        time.setText(theonth + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日" + Calendar.HOUR_OF_DAY + "时");
                    }
                } else if (M > theonth) {
                    time.setText(theonth + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
                }
            } else {
                time.setText(calendar.get(Calendar.YEAR) + "年" + theonth + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }


        return convertView;
    }
}
