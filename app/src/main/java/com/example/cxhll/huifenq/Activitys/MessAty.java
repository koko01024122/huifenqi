package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Adapters.PhotoListAdapter;
import com.example.cxhll.huifenq.Adapters.SuppAdapt;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.tools.ImageHandle;
import com.example.cxhll.huifenq.dao.MessageListHelper;
import com.example.cxhll.huifenq.item.Orderinfo;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.Supp;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import java.util.ArrayList;

public class MessAty extends BaseActivity {
	private TextView orderinfobutton;
	private int mbtstate;
	private RelativeLayout reorderinfo;
	private TextView name;
	private TextView detalphonenum;
	private TextView reason;
	private TextView state;
	private TextView onum;
	private TextView tname;
	private ArrayList<String> urllsit;
	private TextView tprice;
	private String url;
	private TextView rphonenum;
	private TextView order_num;
	private TextView trade_name;
	private TextView trade_price;
	private TextView stag_info;
	private TextView receiver;//收货人姓名
	private TextView receiver_phonenum ;//收货人电话
	private TextView order_stage_green;
	private TextView order_stage_red;
	private TextView refu_reason;
	private TextView order_cusoname;
	private Orderinfo orderinfo;
	private String TAG = "MessAty.this";
	private String m_id;
	private ImageView img1;
	private ImageView img2;
	private ImageView img3;
	private CardView picbutt;
	private Bitmap bitmap;
	private int time;

	private ListView bill_list;
	private SuppAdapt suppAdapt;
	private ArrayList<Supp> supps;
	private Button call;
	private Button suppButton;
	private ArrayList<String> list;

	private LinearLayout prc2_ll;
	private ImageView img4;
	private ImageView img5;
	private ImageView img6;
	private String img1url;
	private String img1ur2;
	private String img1ur3;
	private String img1ur4;
	private String img1ur5;
	private String img1ur6;
	private String remark;
	private EditText remarktv;
	private TextInputLayout namet1;
	private TextInputLayout contt1;
	private TextInputLayout phonenumt1;
	private TextInputLayout contt2;
	private TextInputLayout phonenumt2;
	private TextInputLayout contt3;
	private TextInputLayout phonenumt3;
	private String name1;
	private String cont1;
	private String phonenum1;
	private TextInputLayout namet2;
	private String name2;
	private String cont2;
	private String phonenum2;
	private TextInputLayout namet3;
	private String name3;
	private String cont3;
	private String phonenum3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.messaty_layout);
		urllsit=new ArrayList<>();
		namet1= (TextInputLayout) findViewById(R.id.name1);
		EditText e11=namet1.getEditText();
		name1=e11.getText().toString();
		contt1= (TextInputLayout) findViewById(R.id.con1);
		EditText ct1=contt1.getEditText();
		cont1=ct1.getText().toString();
		phonenumt1= (TextInputLayout) findViewById(R.id.phone1);
		EditText pt1=phonenumt1.getEditText();
		phonenum1=pt1.getText().toString();

		namet2= (TextInputLayout) findViewById(R.id.name2);
		EditText e2=namet2.getEditText();
		name2=e2.getText().toString();
		contt2= (TextInputLayout) findViewById(R.id.con2);
		EditText ct2=contt2.getEditText();
		cont2=ct2.getText().toString();
		phonenumt2= (TextInputLayout) findViewById(R.id.phone2);
		EditText pt2=phonenumt2.getEditText();
		phonenum2=pt2.getText().toString();

		namet3= (TextInputLayout) findViewById(R.id.name3);
		EditText e13=namet3.getEditText();
		name3=e13.getText().toString();
		contt3= (TextInputLayout) findViewById(R.id.con3);
		EditText ct3=contt3.getEditText();
		cont3=ct3.getText().toString();
		phonenumt3= (TextInputLayout) findViewById(R.id.phone3);
		EditText pt3=phonenumt3.getEditText();
		phonenum3=pt3.getText().toString();

		supps = new ArrayList<Supp>();

		list=new ArrayList<String>();
		call = (Button) findViewById(R.id.call);
		prc2_ll= (LinearLayout) findViewById(R.id.bill_pic2);
		img4= (ImageView) findViewById(R.id.img4);
		img5= (ImageView) findViewById(R.id.img5);
		img6= (ImageView) findViewById(R.id.img6);
		remarktv= (EditText) findViewById(R.id.remarks_et);
		remark=remarktv.getText().toString();
		img4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {


				selectPicture();

			}
		});

		img5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {



				selectPicture();

			}
		});

		img6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				selectPicture();


			}
		});

		final Intent intent = getIntent();
		m_id = intent.getStringExtra("id");
		String oid=intent.getStringExtra("o_id");
		String reson=intent.getStringExtra("reson");
		Log.d(TAG, "onCreate: "+reson);
		Log.d(TAG, "onCreate: oId的值是"+oid);
		list=dbHelper.select_orders(dbHelper,oid);




		//bill_list.setAdapter(suppAdapt);


		img1 = (ImageView) findViewById(R.id.img1);
		orderinfo = new Orderinfo();
		mbtstate = 0;
		name = (TextView) findViewById(R.id.tv_deta_name);
		name.setText(list.get(9));
		setBar(list.get(9)+"的消息",2);
		//name.setText(String.valueOf(orderinfo.getName()));

		Log.d(TAG, "onCreate: " + orderinfo.getId());

		call.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Uri uri=Uri.parse("tel:"+list.get(5));
				Intent intent1 = new Intent(Intent.ACTION_DIAL,uri);

				startActivity(intent1);
			}
		});
		//这里缺一个客户的手机号
		detalphonenum = (TextView) findViewById(R.id.tv_deta_phonenum);
		detalphonenum.setText(list.get(5));
		reason = (TextView) findViewById(R.id.refu_reason);
		reason.setText(reson);
		TextView time= (TextView) findViewById(R.id.order_time);
		time.setText(list.get(10));
		orderinfobutton = (TextView) findViewById(R.id.order_info);
		reorderinfo = (RelativeLayout) findViewById(R.id.rorderinfo);
		orderinfobutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (mbtstate == 0) {
					reorderinfo.setVisibility(View.VISIBLE);
					mbtstate = 1;
				} else if (mbtstate == 1) {
					reorderinfo.setVisibility(View.GONE);
					mbtstate = 0;
				}

			}
		});

		img2 = (ImageView) findViewById(R.id.img2);
		img1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {


				selectPicture();


			}


		});

		img2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {


				selectPicture();


			}
		});
		img3 = (ImageView) findViewById(R.id.img3);
		img3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				selectPicture();


			}
		});
		TextView cname= (TextView) findViewById(R.id.order_cusoname);
		cname.setText(list.get(9));

		order_num= (TextView) findViewById(R.id.order_num);
		order_num.setText(list.get(6));
		trade_name= (TextView) findViewById(R.id.trade_name);
		trade_name.setText(list.get(0));

		trade_price= (TextView) findViewById(R.id.trade_price);
		trade_price.setText(list.get(1));
		stag_info = (TextView) findViewById(R.id.stag_info);//分期信息
		TextView address= (TextView) findViewById(R.id.address);
		address.setText(list.get(4));
		stag_info.setText(list.get(3));
		receiver= (TextView) findViewById(R.id.receiver);//收货人姓名
		receiver.setText(list.get(11));
		receiver_phonenum = (TextView) findViewById(R.id.receiver_phonenum);//收货人电话
		receiver_phonenum.setText(list.get(12));
		order_stage_green= (TextView) findViewById(R.id.order_stage_green);
		String stage=intent.getStringExtra("stage");
		order_stage_red= (TextView) findViewById(R.id.order_stage_red);
		picbutt= (CardView) findViewById(R.id.supp_send);
		picbutt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try{
				dbHelper.create_supp(dbHelper,m_id,img1url,img1ur2,img1ur3,img1ur4,img1ur5,img1ur6,cont1,cont2,cont3,name1,name2,name3,phonenum1,phonenum2,phonenum3,remark);
				}catch (NullPointerException e){}
				Toast.makeText(MessAty.this,"补充完成",Toast.LENGTH_SHORT).show();
				finish();
				}
		});
		if (stage.equals("0")){
			order_stage_green.setVisibility(View.GONE);
			order_stage_red.setVisibility(View.VISIBLE);
			order_stage_red.setText("已拒单");
		}else if (stage.equals("1")){
			order_stage_green.setVisibility(View.VISIBLE);
			order_stage_red.setVisibility(View.GONE);
			order_stage_green.setText("初审中");
		}else if (stage.equals("2")){
			order_stage_green.setVisibility(View.VISIBLE);
			order_stage_red.setVisibility(View.GONE);
			order_stage_green.setText("电审中");
		}else if (stage.equals("3")){
			order_stage_green.setVisibility(View.VISIBLE);
			order_stage_red.setVisibility(View.GONE);
			order_stage_green.setText("复审中");
		}else if (stage.equals("4")){
			order_stage_green.setVisibility(View.VISIBLE);
			order_stage_red.setVisibility(View.GONE);
			order_stage_green.setText("终审中");
		}else if (stage.equals("5")){
			order_stage_green.setVisibility(View.VISIBLE);
			order_stage_red.setVisibility(View.GONE);

			order_stage_green.setText("已通过");

		}

		try{
			refu_reason= (TextView) findViewById(R.id.refu_reason);

		}catch (NullPointerException e){}
		order_cusoname= (TextView) findViewById(R.id.order_cusoname);

	}


	private void selectPicture() {
		// TODO Auto-generated method stub
		Intent intent=new Intent(MessAty.this,ImagesSelectorActivity.class);
		intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER,6);
		intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE,100000);
		intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA,false);
		urllsit=new ArrayList<String>();
		intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST,urllsit);
		startActivityForResult(intent,123);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==123){
			Log.d(TAG, "onActivityResult: 开始选择图片");
			if (resultCode==RESULT_OK){
				urllsit = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
				assert urllsit!=null;

				//Log.d(TAG, "onActivityResult: "+urllsit.get(1));
				Log.d(TAG, "onActivityResult: urllist的长度是"+urllsit.size());
				try{
					if (urllsit.size()>3){

						prc2_ll.setVisibility(View.VISIBLE);
					}
img1url=urllsit.get(0);
					img1.setImageBitmap(BitmapFactory.decodeFile(img1url));
				img1ur2=urllsit.get(1);
					img2.setImageBitmap(BitmapFactory.decodeFile(img1ur2));
				img1ur3=urllsit.get(2);
					img3.setImageBitmap(BitmapFactory.decodeFile(img1ur3));
				img1ur4=urllsit.get(3);
					img4.setImageBitmap(BitmapFactory.decodeFile(img1ur4));
				img1ur5=urllsit.get(4);
					img5.setImageBitmap(BitmapFactory.decodeFile(img1ur5));
				img1ur6=urllsit.get(5);
					img6.setImageBitmap(BitmapFactory.decodeFile(img1ur6));





				}catch (Exception e){

				}

			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onRestart() {
		Intent intent = getIntent();
		String id = intent.getStringExtra("id");
		//supps = dbHelper.selectsupp(id, dbHelper);
		suppAdapt = new SuppAdapt(MessAty.this, R.layout.suppitem, supps);


		super.onRestart();
	}

}
