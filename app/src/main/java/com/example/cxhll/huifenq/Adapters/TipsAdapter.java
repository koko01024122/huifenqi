package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.TipsItem;

import java.util.List;

/**
 * Created by cxandpll on 17-2-20.
 */

public class TipsAdapter extends ArrayAdapter<TipsItem> {
    static int resourceId;
    public TipsAdapter(Context context, int resource, List<TipsItem> objects) {
        super(context, resource, objects);
    resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TipsItem item=getItem(position);
    if (convertView==null){
        convertView= LayoutInflater.from(getContext()).inflate(resourceId,null);
    }

        TextView title= (TextView) convertView.findViewById(R.id.role_item);
            title.setText(item.getTitle());

        return convertView;


    }
}
