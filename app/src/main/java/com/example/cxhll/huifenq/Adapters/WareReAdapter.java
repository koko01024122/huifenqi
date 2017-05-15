package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.WaresItem;
import com.example.cxhll.huifenq.tools.SlideListView2;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by CXHLL on 2016/12/25.
 */

public  class WareReAdapter extends SwipeMenuAdapter<WareReAdapter.MyViewHolder> {
    private List<WaresItem> datas;
    private LayoutInflater inflater;
    private Context mContext;
    private String TAG="WareReAdapter.class";

    public WareReAdapter(Context context,List<WaresItem> datas){
        this.mContext=context;
        Log.d(TAG, "WareReAdapter:进入了adp ");
        this.datas=datas;
        inflater=LayoutInflater.from(context);
    }





    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateContentView: 进入createcountentView");
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.mc_item,parent,false);
    }

    @Override
    public MyViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        View view=inflater.inflate(R.layout.mc_item, (ViewGroup) realContentView,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        Log.d(TAG, "onCompatCreateViewHolder: 进入compatcreateViewHolder");
        return myViewHolder;

    }


    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: 进入bind");
        WaresItem item=datas.get(position);
        String reaName=item.getBrand().concat(item.getName()).concat(item.getMemory()).concat(item.getRam()).concat(item.getModel());
        holder.	name.setText(reaName);
        String ststemimg = item.getSystemImg();
        String url=item.getUrl();
        Bitmap bitmap= BitmapFactory.decodeFile(url);
        Log.d(TAG, "WareReAdapter:进入了adp NAME---"+item.getName());
        holder.price.setText(item.getPrice());
        holder.img.setImageBitmap(bitmap);
        try{
            if (ststemimg.equals("0")){

            }else if (ststemimg.equals(1)){
                //搜索相应的系统图
            }
        }catch (NullPointerException e){}
        holder.stage.setText(item.getState());
        holder.sale.setText(item.getSale());
        if (item.getState().equals("1")){
            holder.sale.setText("销售中");
        }else{
            holder.sale.setText("已下架");
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: 进入count");
        return datas.size();
    }
    public  class  MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name;
        TextView  price ;
        ImageView img;
        TextView stage;

        TextView sale;

        public MyViewHolder(View view) {

            super(view);
            name=(TextView) view.findViewById(R.id.mc_name);
            price = (TextView) view.findViewById(R.id.mc_price);
            img  = (ImageView) view.findViewById(R.id.mc_img);
            stage= (TextView) view.findViewById(R.id.mc_state);
            sale= (TextView) view.findViewById(R.id.mc_sale);


        }
    }
}
