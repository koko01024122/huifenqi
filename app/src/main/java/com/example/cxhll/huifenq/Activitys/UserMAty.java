package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.cxhll.huifenq.Adapters.UserAdapter;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.UserItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CXHLL on 2016/11/12.
 */

public class UserMAty extends BaseActivity {

	private ListView mUserList;
	private ArrayList<UserItem> mUserinfos;
	private UserAdapter userAdapter;
	private  HashMap<Object,Object> infos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.um);
		setDbHelper(this);
		setBar("用户管理",2);

		intentX=new Intent(UserMAty.this,CreateUser.class);
		intentX.putExtra("update","0");
		intentY=new Intent(this,SeachActivity.class);
		intentY.putExtra("info","2");
		infos=new HashMap<Object,Object>();

		mUserList= (ListView) findViewById(R.id.um_userlist);
		mUserinfos=new ArrayList<UserItem>();
		infos=dbHelper.select_userList(dbHelper,"null");
		mUserinfos= (ArrayList<UserItem>) infos.get("userlist");

		userAdapter=new UserAdapter(UserMAty.this,R.layout.user_item,mUserinfos);
		mUserList.setAdapter(userAdapter);




	}

	@Override
	protected void onStart() {
		super.onStart();
		mUserinfos=new ArrayList<UserItem>();
		infos=dbHelper.select_userList(dbHelper,"null");
		mUserinfos= (ArrayList<UserItem>) infos.get("userlist");

		userAdapter=new UserAdapter(UserMAty.this,R.layout.user_item,mUserinfos);
		mUserList.setAdapter(userAdapter);
		mUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent intent =new Intent(UserMAty.this,CreateUser.class);
				UserItem user=mUserinfos.get(i);
				mUserList.setAdapter(userAdapter);
				String phonenum=user.getmUiphone();
				intent.putExtra("update","1");
				String id=user.getId();
				intent.putExtra("id",id);
				intent.putExtra("phonenum",phonenum);
				startActivity(intent);
			}
		});

	}

}
