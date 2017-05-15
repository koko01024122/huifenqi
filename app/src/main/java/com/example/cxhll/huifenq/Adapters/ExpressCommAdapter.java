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
import com.example.cxhll.huifenq.item.Express_CommItem;

import java.util.List;

/**
 * Created by cxandpll on 17-2-15.
 */

public class ExpressCommAdapter extends ArrayAdapter<Express_CommItem> {

    private static int resourceId;

    public ExpressCommAdapter(Context context, int resource, List<Express_CommItem> objects) {
        super(context, resource, objects);
        resourceId = resource;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Express_CommItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);

        }
        TextView shipperName = (TextView) convertView.findViewById(R.id.shipper_name);
        TextView shipperWord = (TextView) convertView.findViewById(R.id.shipper_word);
        ImageView shipperImg = (ImageView) convertView.findViewById(R.id.shipper_img);
        shipperName.setText(item.getShipperName());
        shipperWord.setText(item.getShipperWord());
        String code = item.getShipperCode();
        if (code.equals("SF")) {
            shipperImg.setImageResource(R.drawable.sf);
        } else if (code.equals("YT")) {
            shipperImg.setImageResource(R.drawable.yt);
        } else if (code.equals("ZT")) {
            shipperImg.setImageResource(R.drawable.zt);
        } else if (code.equals("HT")) {
            shipperImg.setImageResource(R.drawable.ht);
        } else if (code.equals("EMS")) {
            shipperImg.setImageResource(R.drawable.ems);
        } else if (code.equals("DB")) {
            shipperImg.setImageResource(R.drawable.db);
        } else if (code.equals("YS")) {
            shipperImg.setImageResource(R.drawable.ys);
        } else if (code.equals("YD")) {
            shipperImg.setImageResource(R.drawable.yd);
        }


        return convertView;
    }
}
