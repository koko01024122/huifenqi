package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.McItem;

import java.util.List;

/**
 * Created by CXHLL on 2016/11/10.
 */

public class McItemAdapter extends ArrayAdapter<McItem> {
    private int resourceId;

    public McItemAdapter(Context context, int resource, List<McItem> objects) {
        super(context, resource, objects);
        resourceId = resource;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        McItem mcItem = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView mcname = (TextView) view.findViewById(R.id.mc_name);
        TextView mcprice = (TextView) view.findViewById(R.id.mc_price);
        TextView mcstate = (TextView) view.findViewById(R.id.mc_state);
        ImageView mcimg = (ImageView) view.findViewById(R.id.mc_img);
        TextView mcsale = (TextView) view.findViewById(R.id.mc_sale);
        mcname.setText(mcItem.getMcName());
        mcprice.setText(mcItem.getMcPrice());
        mcsale.setText(String.valueOf(mcItem.getMcSale()));
        mcstate.setText(String.valueOf(mcItem.getMcState()));
        mcimg.setImageBitmap(mcItem.getMcImg());
        return super.getView(position, convertView, parent);
    }
}
