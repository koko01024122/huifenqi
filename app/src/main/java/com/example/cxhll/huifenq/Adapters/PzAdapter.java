package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.PingZItem;

import java.util.List;

/**
 * Created by CXHLL on 2016/12/19.
 */

public class PzAdapter extends ArrayAdapter<PingZItem> {
    private static  int resourceid;
    public PzAdapter(Context context, int resource, List<PingZItem> objects) {
        super(context, resource, objects);
        resourceid=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      PingZItem item=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceid,null);
        try{
        ImageView imgitem= (ImageView) view.findViewById(R.id.pingzhengimg);
        Bitmap bitmap= BitmapFactory.decodeFile(item.getUrl());
        imgitem.setImageBitmap(bitmap);
        }catch (NullPointerException e){

        }
        return view;
    }
}
