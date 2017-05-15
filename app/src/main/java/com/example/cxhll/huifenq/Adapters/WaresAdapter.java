package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.cxhll.huifenq.Activitys.McHome;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.WaresItem;
import com.example.cxhll.huifenq.tools.MyLisrView;
import com.example.cxhll.huifenq.tools.SlideListView2;

import java.util.ArrayList;

import static android.text.TextUtils.concat;

/**
 * Created by CXHLL on 2016/11/24.
 */

public class WaresAdapter extends ArrayAdapter<WaresItem> {
    private String TAG = "RmAty.class";
    private int resourceId;

    ArrayList<WaresItem> ites;
    private SlideListView2 lisrView;

    public WaresAdapter(Context context, int resource, ArrayList<WaresItem> object) {
        super(context, resource, object);
        resourceId = resource;


    }

    public int getViewTypeCount() {
        return 2;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WaresItem item = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        TextView name = (TextView) view.findViewById(R.id.mc_name);

        //lisrView= (SlideListView2) view.findViewById(R.id.mc_list);
        //relname=品牌+型号+ram+rom+网络、
        //	String reaName=item.getBrand().concat(item.getName()).concat(item.getRam())).concat(item.getModel()).concat(item.getModel());
        String reaName = item.getBrand() + " " + item.getName() + " " + item.getRam() + item.getModel() + item.getMemory();
        name.setText(reaName);


        TextView price = (TextView) view.findViewById(R.id.mc_price);
        price.setText(item.getPrice());
        String url = null;
        ImageView img = (ImageView) view.findViewById(R.id.mc_img);
        String ststemimg = item.getSystemImg();
        try {
            url = item.getUrl();
            Log.d(TAG, "getView:URL是 " + url);

            Bitmap bitmap = BitmapFactory.decodeFile(url);

            img.setImageBitmap(bitmap);
        } catch (NullPointerException e) {

        }
        Log.d(TAG, "getView:图片设置完成 ");

        try {
            if (ststemimg.equals("0")) {

            } else if (ststemimg.equals(1)) {
                //搜索相应的系统图
            }
        } catch (NullPointerException e) {
        }


        TextView stage = (TextView) view.findViewById(R.id.mc_state);
        stage.setText("库存： " + item.getState());
        TextView sale = (TextView) view.findViewById(R.id.mc_sale);
        sale.setText(item.getSale());
        if (item.getState().equals("1")) {
            sale.setText("销售中");
        } else {
            sale.setText("已下架");
        }
        try {
            Log.d(TAG, "getView: " + url);
            if (url == null) {
                if (item.getName().equals("苹果6s") || item.getName().equals("iphone6s") || item.getName().equals("Iphone6s")) {
                    img.setImageResource(R.drawable.iphonese);
                    Log.d(TAG, "getView: iphone6设置完成");
                } else if (item.getName().equals("苹果7") || item.getName().equals("iphone7") || item.getName().equals("Iphone7")) {
                    img.setImageResource(R.drawable.iphone7);
                } else if (item.getName().equals("苹果SE") || item.getName().equals("iphonese") || item.getName().equals("IphoneSE")) {
                    img.setImageResource(R.drawable.iphonese);
                } else if (item.getName().equals("苹果7plus") || item.getName().equals("iphone7plus") || item.getName().equals("Iphone7plus")) {
                    img.setImageResource(R.drawable.iphone7i);
                } else if (item.getName().equals("OPPOR9") || item.getName().equals("OPPOR9") || item.getName().equals("OPPOR9")) {

                }
            }
        } catch (NullPointerException e) {


        }

        return view;
    }


}
