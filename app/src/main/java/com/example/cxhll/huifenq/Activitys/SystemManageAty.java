package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Login;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;

import java.io.BufferedWriter;
import java.io.FileOutputStream;

/**
 * Created by CXHLL on 2016/11/26.
 */

public class SystemManageAty extends BaseActivity {
	private Button changepassword;
	private RelativeLayout um;
	private RelativeLayout rm;
	private CardView cd;
	private int a=0;
	private Button makesure;
	private TextInputLayout psd1;
	private TextInputLayout psd2;

	private String TAG="SystemManageAty.class";
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.system_manage);
		setDbHelper(this);
		setBar("系统管理",2);








		um= (RelativeLayout) findViewById(R.id.um);
		um.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent=new Intent(SystemManageAty.this,UserMAty.class);
				startActivity(intent);
			}
		});
		rm= (RelativeLayout) findViewById(R.id.rm);
		rm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent=new Intent(SystemManageAty.this,RoleManageAty.class);
				startActivity(intent);
			}
		});
		if (dbHelper.select_power(dbHelper,"rm_rabc")==0){
		rm.setVisibility(View.GONE);
		}
		if (dbHelper.select_power(dbHelper,"rm_makeson")==0){
			um.setVisibility(View.GONE);
		}

	}



}
