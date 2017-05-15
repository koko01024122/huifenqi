package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cxhll.huifenq.Adapters.TraceAdapter;
import com.example.cxhll.huifenq.EMS.KdApiOrderDistinguish;
import com.example.cxhll.huifenq.EMS.KdniaoSubscribeAPI;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.Traces;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpressFinishActivity extends BaseActivity {
String json;
    ProgressBar aeflaoding;
    TextView state;
    TextView reason;
    LinearLayout pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_finish);
        setBar("提交信息",2);
        Intent intent=getIntent();
        pass= (LinearLayout) findViewById(R.id.aef_pass);
       json=intent.getStringExtra("json");
        aeflaoding= (ProgressBar) findViewById(R.id.aef_laoding);
        state= (TextView) findViewById(R.id.send_state);
        reason= (TextView) findViewById(R.id.send_reason);

    }

    @Override
    protected void onStart() {
        super.onStart();
    setLogic();
    }

    public void setLogic(){


        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {

                int HEAD=0;
                int BODY=1;
                if (msg.what==HEAD){
                    boolean head=false;
                    head= (boolean) msg.obj;
                        aeflaoding.setVisibility(View.GONE);
                    pass.setVisibility(View.VISIBLE);
                    pao();
                }else if (msg.what==BODY){
                    state.setText("提交资料失败");
                    state.setTextColor(Color.parseColor("#8b8a8a"));
                    reason.setText((String)msg.obj);
                    aeflaoding.setVisibility(View.GONE);
                    pass.setVisibility(View.VISIBLE);

                }
            }
        };

//开启一个线程，用于提交快递信息

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                JSONObject resultjson = null;
                String reason;
                KdniaoSubscribeAPI sub=new KdniaoSubscribeAPI();

                try {
                 String  result=  sub.orderTracesSubByJson(json);
                   resultjson=new JSONObject(result);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                boolean head= false;
                try {
                    head = resultjson.getBoolean("Success");
                    if (!head){
                        reason=resultjson.getString("Reason");
                        handler.sendMessage(handler.obtainMessage(1,reason));
                    }else{
                        handler.sendMessage(handler.obtainMessage(0,head));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Log.d(TAG, "run: 发送消息");


            }
        };
        new Thread(runnable).start();
    }



    public void pao(){
        final Handler handler1=new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 3:
                        reason.setText("此页面将在"+3+"秒后关闭");
                        break;
                    case 4:
                        reason.setText("此页面将在"+2+"秒后关闭");
                        break;
                    case 5:
                        reason.setText("此页面将在"+1+"秒后关闭");
                        break;
                }
                super.handleMessage(msg);
            }

        };



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int d=3;
                    for (int i=3;i<7;i++){
                        Thread.sleep(1000);
                        Message message=new Message();
                        message.what=i;
                        handler1.sendMessage(message);
                        Log.d(TAG, "run: "+i);

                        if (i==6){
                            finish();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }).start();

    }



}
