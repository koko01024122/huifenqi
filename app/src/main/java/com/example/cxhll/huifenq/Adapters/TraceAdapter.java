package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Trace;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.TraceItem;
import com.example.cxhll.huifenq.item.Traces;
import com.example.cxhll.huifenq.tools.TimeHelper;

import java.text.ParseException;
import java.util.List;

/**
 * Created by cxandpll on 17-2-13.
 */

public class TraceAdapter extends ArrayAdapter<Traces> {
    private  static int resourceId;
    List<Traces> objectsl;
    public TraceAdapter(Context context, int resource, List<Traces> objects) {
        super(context, resource, objects);
        resourceId=resource;
        objectsl=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TimeHelper timeHelper=new TimeHelper();
        Traces item=getItem(position);
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(resourceId,null);

        }
        TextView time= (TextView) convertView.findViewById(R.id.detil_time);
        TextView day= (TextView) convertView.findViewById(R.id.detil_day);
        TextView detil= (TextView) convertView.findViewById(R.id.trace_detil);
        ImageView line= (ImageView) convertView.findViewById(R.id.botton_line);
        detil.setText(item.getAcceptStation());
        if (position==0){
            detil.setTextColor(Color.BLACK);
            time.setTextColor(Color.BLACK);
        }else if (position==objectsl.size()-1){
            line.setVisibility(View.GONE);
        }
        try {
            day.setText(timeHelper.cutDays(item.getAcceptTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            time.setText(timeHelper.cutTime(item.getAcceptTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
