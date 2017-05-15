package com.example.cxhll.huifenq.NetWorks;

import android.util.Log;



import java.io.IOException;
import java.io.StringReader;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class NetSendMessage {
    String text;

    public String NetSendMessage(String info) throws IOException {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, info);
        Request request = new Request.Builder().url("http://www.tuling123.com/openapi/api")
                .post(body).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            text = response.body().string();

            Log.d("TuringActivity.class", "NetSendMessage:"+text.toString());
           // Gson gson = new Gson();
          //  JsonArray jsonArray=new JsonParser().parse(text).getAsJsonArray();
          //  JsonObject jsonObject=jsonArray.get(0).getAsJsonObject();
           // text=jsonObject.get("text").getAsString();
           // ExpMessage expMessage = gson.fromJson(response.body().toString(), ExpMessage.class);
            //text = expMessage.getText();




        } else {
            throw new IOException("Unexpected code" + response);
        }
        return text;
    }
}
