package com.example.cxhll.huifenq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Success extends AppCompatActivity {
private TextView role;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_success);
		try{
		role= (TextView) findViewById(R.id.role);
		Intent intent=getIntent();
		role.setText(intent.getStringExtra("role"));
	}catch (NullPointerException e){

		}
	}
}
