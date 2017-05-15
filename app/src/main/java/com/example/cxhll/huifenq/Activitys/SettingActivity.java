package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Hometest;
import com.example.cxhll.huifenq.Login;
import com.example.cxhll.huifenq.R;

public class SettingActivity extends BaseActivity {
private Button mOut;
    private TextView mChangepsd;
    private TextView  mHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setDbHelper(this);
        setBar("设置中心",2);
        mChangepsd= (TextView) findViewById(R.id.change_psd);
        mHelp= (TextView) findViewById(R.id.hepleing);
        mHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,TipsActivity.class);
                startActivity(intent);
            }
        });
        mChangepsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(SettingActivity.this,ChangePsdActivity.class);
                startActivity(intent);
            }
        });
        mOut= (Button) findViewById(R.id.loginout);
        mOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingActivity.this, Login.class);
                startActivity(intent);
                dbHelper.logingout(dbHelper);
                finish();
            }
        });
        TextView about_hui= (TextView) findViewById(R.id.about_hui);
        about_hui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingActivity.this,VersionActivity.class);
                startActivity(intent);
            }
        });


    }
}
