package com.example.cxhll.huifenq.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cxhll.huifenq.tools.ImageHandle;
import com.example.cxhll.huifenq.dao.MessageListHelper;
import com.example.cxhll.huifenq.R;


import java.util.ArrayList;

/**
 * Created by CXHLL on 2016/11/4.
 * 这个地方还差一些东西
 * 1.把图片存到数据库
 * 2.数据来自网络，然后转储到数据库
 * 3.ImageView的背景需要图片
 * 4.orderList点击事件还没有做
 * 5.图片压缩的原理应该研究研究
 * 6.以及OnActivityresult和选择图片功能
 */

public class orderInfosAty extends BaseActivity {
	private ImageView img0;
	private ImageView pingzheng1;
	private ImageView pingzheng2;
	private ImageView pingzheng3;
	private TextView name;
	private TextView detalphonenum;
	private TextView reason;
	private TextView state;
	private TextView onum;
	private TextView tname;
	private TextView tprice;
	private TextView receiver;
	private TextView rphonenum;
	private ArrayList<String> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orders_infos);
		Intent intent=getIntent();
		String id=intent.getStringExtra("id");


		reason = (TextView) findViewById(R.id.refu_reason);

		list=new ArrayList<>();
		reason.setText(list.get(2));
		state = (TextView) findViewById(R.id.order_stage_green);
		state.setText(list.get(3));
		onum = (TextView) findViewById(R.id.order_num);
		onum.setText(list.get(4));
		tname = (TextView) findViewById(R.id.trade_name);
		tname.setText(list.get(5));
		tprice = (TextView) findViewById(R.id.trade_price);
		tprice.setText(list.get(6));
		receiver = (TextView) findViewById(R.id.receiver);
		receiver.setText(list.get(7));
		rphonenum = (TextView) findViewById(R.id.receiver_phonenum);
		rphonenum.setText(list.get(8));

	}
	private void selectPicture() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
		intent.setType("image/*");//从所有图片中进行选
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {//从相册选择照片不裁切
			try {
				Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
				String[] filePathColumn = {MediaStore.Images.Media.DATA};
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);  //获取照片路径
				cursor.close();
				Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
				ImageHandle imageHandle = new ImageHandle();
				bitmap = imageHandle.comp(bitmap);
				img0.setImageBitmap(bitmap);
				
			} catch (Exception e) {
				// TODO Auto-generatedcatch block
				e.printStackTrace();

			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
