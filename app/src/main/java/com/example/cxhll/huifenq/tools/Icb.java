package com.example.cxhll.huifenq.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by mogu on 2016/11/28.
 */

public class Icb {
    String TAG="RmAty.class";
    public byte[] icb(Bitmap bitmap){
        Log.d(TAG, "icb: 选择图片类");
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
          bitmap.compress(Bitmap.CompressFormat.WEBP,50,bos);

        Log.d(TAG, "icb: 转换第二部"+bos.toByteArray().length);

        return  bos.toByteArray();

    }
    public Bitmap bci(byte[] blob){
        Log.d(TAG, "bci: 开始转换为图片"+blob);
        Bitmap bitmap= BitmapFactory.decodeByteArray(blob,0, blob.length);
        Log.d(TAG, "bci: "+blob);
        return bitmap;
    }
}
