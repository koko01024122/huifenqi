package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;

public class AnnoShowActivity extends BaseActivity {
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anno_show);
        setBar("公告详情",2);
        intent=getIntent();

    }

    @Override
    protected void onStart() {
        super.onStart();
        setLogic();
    }

    public void setLogic(){
        TextView title= (TextView) findViewById(R.id.aas_title);
        title.setText(intent.getStringExtra("title"));
        TextView sender= (TextView) findViewById(R.id.aas_sender);
        sender.setText(intent.getStringExtra("sender"));
        TextView sendtime= (TextView) findViewById(R.id.aas_sendtime);
        sendtime.setText(intent.getStringExtra("sendtime"));
        TextView content= (TextView) findViewById(R.id.aas_content);
        content.setText(intent.getStringExtra("content"));
    }
}
