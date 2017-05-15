package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.Message;
import com.example.cxhll.huifenq.item.Moneys;
import com.example.cxhll.huifenq.item.OrderListItem;
import com.example.cxhll.huifenq.item.With;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by CXHLL on 2016/12/30.
 */

public class WithAdapter extends ArrayAdapter<With> {

    private int mresourID;
    public WithAdapter(Context context, int resource, ArrayList<With> objects) {
        super(context, resource,objects);
        mresourID=resource;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        With item=getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(mresourID,null);
        TextView time= (TextView) view.findViewById(R.id.with_timesss);
        TextView sale= (TextView) view.findViewById(R.id.with_sale);
        sale.setText(item.getSales());
        time.setText(item.getTime());
        SimpleDateFormat date=new SimpleDateFormat("yyyyMM");
        Date times = null;
  
            try {
                times = date.parse(item.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }catch (NullPointerException e){

            }

            Calendar calendar = Calendar.getInstance();
        try{
            calendar.setTime(times);
        }catch (NullPointerException e){}
        int month=calendar.get(Calendar.MONTH)+1;
        time.setText(calendar.get(Calendar.YEAR)+"年"+month+"月");
            // sale.setText(item.getSales());
            return view;


        }}
