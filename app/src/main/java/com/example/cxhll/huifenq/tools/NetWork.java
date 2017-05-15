package com.example.cxhll.huifenq.tools;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by CXHLL on 2017/2/11.
 */

public class NetWork {
    String TAG="NetWork.class";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public int send(String url,String body){
        OkHttpClient okHttpClient=new OkHttpClient();

        RequestBody requestBody=RequestBody.create(JSON , body);
        Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try {
            Response response=okHttpClient.newCall(request).execute();

                if (response.isSuccessful()){
                    Log.d(TAG, "send: "+response.body().string());
                    return 200;
                }else
                {
                    return  400;
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
