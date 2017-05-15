package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.item.Moneys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by CXHLL on 2016/12/24.
 */

public class MoneyAdp extends ArrayAdapter<Moneys> {
    private HuifqDbHelper dbHelper;
    private static int resourceid;

    public MoneyAdp(Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceid = resource;
        dbHelper = new HuifqDbHelper(context, "huifenqi.db", null, 2);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Moneys moneys = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceid, null);
        TextView sum = (TextView) view.findViewById(R.id.sums);
        sum.setText(moneys.getSum());
        TextView time = (TextView) view.findViewById(R.id.money_time);
        TextView stages = (TextView) view.findViewById(R.id.money_stage);
        try {
            time.setText(moneys.getTime());
            int stage = Integer.parseInt(moneys.getStage());

            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                Date times = date.parse(moneys.getTime());

                Calendar calendar = Calendar.getInstance();

                int M = calendar.get(Calendar.MONTH) + 1;
                int Year = calendar.get(Calendar.YEAR);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.setTime(times);
                int themonth = calendar.get(Calendar.MONTH) + 1;
                if (Year == calendar.get(Calendar.YEAR)) {
                    if (M == themonth) {
                        if (day == calendar.get(Calendar.DAY_OF_MONTH)) {
                            if (calendar.get(Calendar.MINUTE) < 10) {
                                time.setText("今天" + calendar.get(Calendar.HOUR_OF_DAY) + ":0" + calendar.get(Calendar.MINUTE));
                            } else {
                                time.setText("今天" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
                            }

                        } else if (day > calendar.get(Calendar.DAY_OF_MONTH)) {
                            dbHelper.update_tstate(dbHelper, moneys.getId());

                            time.setText(themonth + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日" + Calendar.HOUR_OF_DAY + "时");
                        }
                    } else if (M > themonth) {
                        time.setText(themonth + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
                    }
                } else {
                    time.setText(calendar.get(Calendar.YEAR) + "年" + themonth + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
                }
                if (stage == 0) {
                    stages.setText("处理中");
                    stages.setTextColor(Color.parseColor("#ea4130"));
                } else if (stage == 1) {
                    stages.setText("已到账");
                    stages.setTextColor(Color.parseColor("#164e01"));
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {

        }
        if (moneys.getType().equals("0")) {
            stages.setTextColor(Color.parseColor("#164e01"));
            stages.setText("入账");

        }

        return view;
    }
}
