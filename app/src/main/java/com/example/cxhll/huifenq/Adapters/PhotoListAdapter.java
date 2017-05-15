package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cxhll.huifenq.EMS.KdApiOrderDistinguish;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.tools.ImageHandle;

import java.util.List;

/**
 * Created by cxandpll on 17-2-21.
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoListAdapterViewHolder> {
private LayoutInflater mInflater;
    private List<String> mdatas;
   private PhotoListAdapterViewHolder viewHolder;
    private String url;
    private String TAG="Hometest.class";
    public PhotoListAdapter(Context context, List<String> data){
    mInflater=LayoutInflater.from(context);
        mdatas=data;

    }
    @Override
    public PhotoListAdapter.PhotoListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view=mInflater.inflate(R.layout.imageitem,parent,false);
        Log.d(TAG, "onCreateViewHolder: 进入适配器");
       viewHolder =new PhotoListAdapterViewHolder(view);
        viewHolder.mImg= (ImageView) view.findViewById(R.id.image_item);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoListAdapter.PhotoListAdapterViewHolder holder, int position) {
try{

    url=mdatas.get(position);
//BitmapFactory.Options opts=new BitmapFactory.Options();
    // opts.inJustDecodeBounds=true;

    Bitmap bitmap = BitmapFactory.decodeFile(url);
    // Log.d(TAG, "onBindViewHolder: 长为"+bitmap.getHeight());
    // Log.d(TAG, "onBindViewHolder: 宽为"+bitmap.getWidth());
    // bitmap.getByteCount();

    // opts.inJustDecodeBounds=false;
    //  opts.inSampleSize=2;
    //  bitmap=BitmapFactory.decodeFile(url,opts);
    Log.d(TAG, "onBindViewHolder: 长为"+bitmap.getHeight());
    Log.d(TAG, "onBindViewHolder: 宽为"+bitmap.getWidth());


    ImageHandle imageHandle=new ImageHandle();
    bitmap=imageHandle.comp(bitmap);
    // bitmap = BitmapFactory.decodeFile(url);
    viewHolder.mImg.setImageBitmap(bitmap);
    //  bitmap.recycle();




    Log.d(TAG, "onBindViewHolder: url为"+url);
    Log.d(TAG, "onBindViewHolder: 这个是onbindview");

}catch (NullPointerException e){}

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: 这里是getitem"+mdatas.size());
        return mdatas.size();
    }

    public static class PhotoListAdapterViewHolder extends RecyclerView.ViewHolder{

        
        public PhotoListAdapterViewHolder(View itemView) {
            super(itemView);

        }
        ImageView mImg;


    }
    public void netWork(){
        Log.d(TAG, "netWork: 这里是network");
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {

                int State = 0;

                if (msg.what == State) {

                    Bitmap img = (Bitmap) msg.obj;
       viewHolder.mImg.setImageBitmap(img);
                }
            } };
        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = BitmapFactory.decodeFile(url);
                Log.d(TAG, "run: 当前的url是"+url);
                ImageHandle imageHandle=new ImageHandle();
                bitmap=imageHandle.comp(bitmap);
                handler.sendMessage(handler.obtainMessage(0,bitmap));

            }

        };

        new  Thread(runnable).start();
    }

}



