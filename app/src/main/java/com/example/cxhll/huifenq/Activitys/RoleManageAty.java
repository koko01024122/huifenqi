package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cxhll.huifenq.Adapters.RoleAdapter;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.item.RoleItem;

import java.util.ArrayList;

/**
 * Created by CXHLL on 2016/11/26.
 */

public class RoleManageAty extends BaseActivity {
	private RelativeLayout rm_add;
	private ListView role_list;
	private RoleAdapter adap;
	private ArrayList<RoleItem> list;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rm);
		setBar("角色管理",2);
		intentX=new Intent(this,RmAty.class);
		intentX.putExtra("update","0");
		intentY=new Intent(this,SeachActivity.class);
		intentY.putExtra("info","5");
		role_list= (ListView) findViewById(R.id.rm_list);
	setDbHelper(RoleManageAty.this);
		list=dbHelper.select_rolelist(dbHelper);
		adap=new RoleAdapter(RoleManageAty.this,R.layout.rm_items,list);
		role_list.setAdapter(adap);

		role_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				RoleItem role=list.get(i);
				String id=role.getId();
				boolean update=true;
				Intent intent=new Intent(RoleManageAty.this,RmAty.class);
				intent.putExtra("update","1");
				intent.putExtra("id",id);
				startActivity(intent);
			}
		});

	}


	@Override
	protected void onStart() {
		list=dbHelper.select_rolelist(dbHelper);
		adap=new RoleAdapter(RoleManageAty.this,R.layout.rm_items,list);
		role_list.setAdapter(adap);
		super.onStart();
	}
}
