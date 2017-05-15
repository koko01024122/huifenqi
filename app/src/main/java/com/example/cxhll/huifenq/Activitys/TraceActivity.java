package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Adapters.TraceAdapter;
import com.example.cxhll.huifenq.EMS.KdApiOrderDistinguish;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.TraceItem;
import com.example.cxhll.huifenq.item.Traces;

import java.util.ArrayList;
import java.util.HashMap;

public class TraceActivity extends BaseActivity {
    ArrayList<Traces> lists=new ArrayList<Traces>();
    TraceAdapter adapter;
    ListView list;
    String shipperName;
    String shipperCode;
    String LogisticCode;
    String TAG="TraceActivity.class";
    TextView trace_cust_name;
    TextView trace_comm_name;
    ImageView shipperImg;
    ImageView save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace);
        setBar("物流详情",2);
        trace_cust_name=   (TextView) findViewById(R.id.trace_cust_name);
        trace_comm_name = (TextView) findViewById(R.id.trace_comm_name);
        save= (ImageView) findViewById(R.id.trace_save);
        shipperImg= (ImageView) findViewById(R.id.trace_comm_imgs);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setLogic();
    }
    private void setLogic(){


        Intent intent=getIntent();
        final String num=intent.getStringExtra("num");
        Log.d(TAG, "setLogic: num"+num);
       String state= dbHelper.select_express(dbHelper,num);
        if (!state.equals("0")){
            save.setVisibility(View.GONE);
        }else{
            save.setVisibility(View.VISIBLE);
        }
save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dbHelper.create_express(dbHelper,num,shipperName,num,"1");
        Toast.makeText(TraceActivity.this,"已保存到列表",Toast.LENGTH_SHORT).show();
    }
});
        Log.d(TAG, "setLogic: 单号为"+num);
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            KdApiOrderDistinguish kdApiOrderDistinguish=new KdApiOrderDistinguish();
            kdApiOrderDistinguish.star(num);
            ArrayList<Traces> list1=new ArrayList<Traces>();
            list1=kdApiOrderDistinguish.list;
            HashMap<String,Object> head=new HashMap<String, Object>();
            head=kdApiOrderDistinguish.head;

            Log.d(TAG, "run: "+list1.size());
            Log.d(TAG, "run: 发送消息");
            handler.sendMessage(handler.obtainMessage(0,head));
            handler.sendMessage(handler.obtainMessage(1,list1));
        }
    };
        new Thread(runnable).start();

        list= (ListView) findViewById(R.id.trace_infos_list);


      adapter=new TraceAdapter(TraceActivity.this,R.layout.traceitem,lists);
        list.setAdapter(adapter);
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            int HEAD=0;
            int BODY=1;
            if (msg.what==HEAD){
                HashMap<String,Object> head=new HashMap<String, Object>();
                head= (HashMap<String, Object>) msg.obj;
            shipperName= (String) head.get("Name");
                LogisticCode= (String) head.get("Num");
                shipperCode= (String) head.get("code");
                trace_comm_name.setText(LogisticCode);
                trace_cust_name.setText(shipperName);
                String code=  shipperCode;
                Log.d(TAG, "handleMessage: "+code+"  : "+shipperName+":  "+LogisticCode);
                if (code.equals("SF")) {
                    shipperImg.setImageResource(R.drawable.sf);
                } else if (code.equals("YTO")) {
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
            }else if (msg.what==BODY){
                Log.d(TAG, "handleMessage: 接收到消息");

                lists= (ArrayList<Traces>) msg.obj;
                Log.d(TAG, "handleMessage: "+lists.size());
                adapter=new TraceAdapter(TraceActivity.this,R.layout.traceitem,lists);
                list.setAdapter(adapter);

            }
        }
    };
}
