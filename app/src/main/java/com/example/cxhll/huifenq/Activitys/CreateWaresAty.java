package com.example.cxhll.huifenq.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.tools.Icb;
import com.example.cxhll.huifenq.tools.ImageHandle;

import java.util.ArrayList;

/**
 * Created by CXHLL on 2016/11/13.
 */

public class CreateWaresAty extends BaseActivity {
	private Spinner mWareType;
	private EditText mWareName;
	private EditText mWarePrice;
	private EditText mWareBand;
	private EditText mWareram;
	private EditText mWarerom;
	private EditText mWareOther;
	private  Bitmap img0;
	private Button mUpimg;
	private CheckBox wareimg;
	private ListView speclist;
	private TextInputLayout name;
	private TextInputLayout price;
	private TextInputLayout band;
	private TextInputLayout ram;
	private TextInputLayout rom;
	private TextInputLayout other;
	private Button mEnd;
	private byte[] img;
	private ArrayAdapter<String> adp;

	private String TAG="CreateWaresAty.class";
	private Button selectphoto;
	private String imgurl;
	private RadioButton up;
	private RadioButton down;
	private  Intent intent;
	private String stage="1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mi);

		intent=getIntent();
		try{
			if (intent.getStringExtra("update").equals("1")){
				setBar("添加商品",2);
			}else {
				setBar("编辑商品",2);
			}
		}catch (NullPointerException e){}
		up= (RadioButton) findViewById(R.id.ware_up);
		down= (RadioButton) findViewById(R.id.ware_down);
		selectphoto= (Button) findViewById(R.id.mi_upimg);
		mWareType= (Spinner) findViewById(R.id.mi_type);

		ArrayList<String> typelist=new ArrayList<String>();

		typelist.add("请选择商品类型");
		typelist.add("手机");
		typelist.add("电脑");
		typelist.add("其他");
		adp=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,typelist);
		adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mWareType.setAdapter(adp);
		
		mWareType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				if (mWareType.getSelectedItem().equals("手机")){
					ram.setHint("运行内存");

					rom.setHint("储存容量");

					other.setHint("网络制式");


				}else if (mWareType.getSelectedItem().equals("电脑")){
					ram.setHint("运行内存");

					rom.setHint("储存容量");

					other.setHint("显卡配置");
				} else if (mWareType.getSelectedItem().equals("其他")){
					ram.setHint("参数1");

					rom.setHint("参数2");

					other.setHint("参数3");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});


		mEnd= (Button) findViewById(R.id.mi_end);
		name= (TextInputLayout) findViewById(R.id.mi_name);
		selectphoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				selectPicture();
			}
		});
		mWareName=name.getEditText();

		name.setHint("商品名称（型号）");
		band= (TextInputLayout) findViewById(R.id.mi_brand_line);

		mWareBand=band.getEditText();
		band.setHint("商品品牌");
		ram= (TextInputLayout) findViewById(R.id.mi_ram);
		mWareram=ram.getEditText();
		ram.setHint("参数1");
		rom= (TextInputLayout) findViewById(R.id.mi_rom);
		mWarerom=rom.getEditText();
		rom.setHint("参数2");
		price= (TextInputLayout) findViewById(R.id.mi_price);
		mWarePrice=price.getEditText();
		price.setHint("商品价格");
		other= (TextInputLayout) findViewById(R.id.mi_gpu);
		mWareOther=other.getEditText();
		other.setHint("参数3");
		try{
			mEnd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "onClick: 去检查");
				check();
			}
		});
		}catch (NullPointerException e){}

		try {
			String update = intent.getStringExtra("update");
			Log.d(TAG, "onCreate: " + intent.getStringExtra("update"));

			if (update.equals("1")) {





			} else {
				update = "0";
				String id = intent.getStringExtra("id");


				Log.d(TAG, "onCreate: id为" + id);

				update(id);
			}
		}catch (NullPointerException e){

		}
		up.setChecked(true);
		up.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				down.setChecked(false);
				stage="1";
				up.setChecked(true);
			}
		});
		down.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				down.setChecked(true);
				stage="0";

				up.setChecked(false);
			}
		});

	}
	private void selectPicture() {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
		intent.setType("image/*");//从所有图片中进行选
		startActivityForResult(intent, 1);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode==RESULT_OK) {//从相册选择照片不裁切
			try {
				Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor =getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);  //获取照片路径
				imgurl=picturePath;
				cursor.close();
				Bitmap bitmap= BitmapFactory.decodeFile(picturePath);
				ImageHandle imageHandle=new ImageHandle();
				bitmap=imageHandle.comp(bitmap);
				Icb icb=new Icb();
				img=icb.icb(bitmap);

			} catch (Exception e) {
				// TODO Auto-generatedcatch block
				e.printStackTrace();

			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}




	public void check(){
		boolean b1,b2,b3,b4,b5,b6;
			b1=false;
		b2=false;
		b3=false;
		b4=false;
		b5=false;
		b6=false;

		if(mWareBand.getText().toString().equals("")){
			b1=false;
			mWareBand.setError("请输入商品品牌");
		}else{
			b1=true;
		}
		if(mWareName.getText().toString().equals("")){
			b2=false;
			mWareName.setError("请输入商品名称");
		}else{
			b2=true;
		}

		if(mWarePrice.getText().toString().equals("")){
			b6=false;
			mWarePrice.setError("请输入商品价格");
		}else{
			b6=true;
		}

/**
 *以上部分是不需要分别对待的
 *以下部分是手机特性
 */

		if(mWareType.getSelectedItem().toString().equals("手机")){

			if(mWareOther.getText().toString().equals("")){
				b5=false;
				mWareOther.setError("请输入网络制式");
			}else{
				b5=true;
			}

			if(mWareram.getText().toString().equals("")){
				b3=false;
				mWareram.setError("请输入运行内存容量");
			}else{
				b3=true;
			}

			if(mWarerom.getText().toString().equals("")){
				b4=false;
				mWarerom.setError("请输入储存容量");
			}else{
				b4=true;
			}

		}
		/**
		 *
		 *以下部分是电脑特性
		 */

		if(mWareType.getSelectedItem().toString().equals("请选择商品类型")){
			Toast.makeText(this,"请选择商品类型",Toast.LENGTH_SHORT).show();

		}


		if(mWareType.getSelectedItem().toString().equals("电脑")){
			if(mWareOther.getText().toString().equals("")){
				b5=false;
				mWareOther.setError("请输入显卡配置");
			}else{
				b5=true;
			}
			if(mWareram.getText().toString().equals("")){
				b3=false;
				mWareram.setError("请输入运行内存容量");
			}else{
				b3=true;
			}

			if(mWarerom.getText().toString().equals("")){
				b4=false;
				mWarerom.setError("请输入储存容量");
			}else{
				b4=true;
			}
		}

		if(mWareType.getSelectedItem().toString().equals("其他")){
			ram.setHint("参数1");
			mWareram.setHint("参数1");
			b3=true;
			b4=true;
			b5=true;
			rom.setHint("参数2");
			mWarerom.setHint("参数2");
			mWareOther.setHint("参数3");
			other.setHint("参数3");
		}





		if(b1 && b2 && b3 && b4 && b5 && b6){
			String update = null;
			String id=null;
			try{
		update=intent.getStringExtra("update");
			Log.d(TAG, "check: "+update);
			id=intent.getStringExtra("id");
			}catch (NullPointerException e){
				finish();
			}
			if (update.equals("1")){
			Log.d(TAG, "onClick: ----------------------"+mWareBand.getText().toString());
			dbHelper.createWares(String.valueOf(mWareType.getSelectedItemId()),
					mWareName.getText().toString(),
					mWarePrice.getText().toString(),
					mWareBand.getText().toString(),
					"",
					mWareram.getText().toString(),
					mWarerom.getText().toString(),
					mWareOther.getText().toString(),
					stage,img,imgurl,dbHelper);
				finish();
			}else if (update.equals("0")){
				Log.d(TAG, "check:进入了else");
				dbHelper.updateWares(String.valueOf(mWareType.getSelectedItemId()),
						mWareName.getText().toString(),
						mWarePrice.getText().toString(),
						mWareBand.getText().toString(),
						"0",
						mWareram.getText().toString(),
						mWarerom.getText().toString(),
						mWareOther.getText().toString(),
						stage,img,imgurl,dbHelper,id);
				finish();
			}


		}
	}
	public void update(String id){
		Log.d(TAG, "update: 进入到update");
		ArrayList<String> infos=new ArrayList<String>();
		infos=dbHelper.selectWareInfo(id,dbHelper);
		Log.d(TAG, "update: "+infos.get(1));
		mWareType.setSelection(Integer.parseInt(infos.get(1)));
		mWareName.setText(infos.get(2));
		mWarePrice.setText(infos.get(3));
		mWareBand.setText(infos.get(4));
		mWareOther.setText(infos.get(8));
		mWareram.setText(infos.get(6));
		mWarerom.setText(infos.get(7));
		imgurl=infos.get(10);


	}
}
