package com.example.cxhll.huifenq.Activitys;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.cxhll.huifenq.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

public class QrcodeAty extends BaseActivity {
private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_aty);
         img= (ImageView) findViewById(R.id.rqcode_show);
        createrqcode();

    }
    public void createrqcode(){
        String url="123";
        Hashtable<EncodeHintType,String> hints=new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
        try {
            BitMatrix bitMatrix=new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE,100,100,hints);
        int[] pixels=new int[100*100];
            for (int y=0;y<100;y++){
                for (int x=0;x<100;x++){
                    if (bitMatrix.get(x,y)){
                        pixels[y*100+x]=0xff000000;

                    }else {
                        pixels[y*100+x]=0xffffffff;
                    }
                }
            }
            Bitmap bitmap=Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels,0,100, 0, 0, 100, 100);
            img.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
