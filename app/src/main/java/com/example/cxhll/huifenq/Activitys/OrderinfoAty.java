package com.example.cxhll.huifenq.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cxhll.huifenq.Adapters.PhotoListAdapter;
import com.example.cxhll.huifenq.Adapters.PzAdapter;
import com.example.cxhll.huifenq.Hometest;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.item.Orderinfo;
import com.example.cxhll.huifenq.item.PingZItem;

import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import java.util.ArrayList;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.BaseResultEvent;

import static android.R.attr.id;
import static android.R.attr.layout_toRightOf;
import static com.example.cxhll.huifenq.tools.ImageHandle.TAKE_PHOTO;

/**
 * Created by CXHLL on 2016/11/7.
 */

public class OrderinfoAty extends BaseActivity {
	private TextView order_num;
	private TextView trade_name;
	private TextView trade_price;
	private TextView stag_info;
	private TextView receiver;//收货人姓名
	private TextView receiver_phonenum ;//收货人电话
	private TextView order_stage_green;
	private Bitmap bitmap;
	private TextView order_stage_red;
	private TextView refu_reason;
	private TextView order_cusoname;
	private String TAG="OrderinfoAty.class";
	private RelativeLayout up_pingzheng;
	ImageView img0;
	private String url;
	private LinearLayoutManager linearLayoutManager;
	private ArrayList<PingZItem> pzlist;
	private CardView realup;
	private PzAdapter adp;
	private  TextView tv1;
	private TextView tv2;
	private String id;
	private  ArrayList<String> getUrl;
	private RecyclerView imglist;
	private ArrayList<String> urllsit;
	private PhotoListAdapter imgadp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orders_infos);
		Log.d(TAG, "onCreate: 启动activity");
		Intent intent=getIntent();
		//imgpart= (RelativeLayout) findViewById(R.id.order_incu);
		imglist= (RecyclerView) findViewById(R.id.order_pthotos);
		linearLayoutManager=new LinearLayoutManager(this);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		imglist.setLayoutManager(linearLayoutManager);
		urllsit=new ArrayList<String>();
		try {
			urllsit=dbHelper.select_pingzheng(dbHelper,intent.getStringExtra("id"));

			imgadp=new PhotoListAdapter(this,urllsit);
			imglist.setAdapter(imgadp);
		}catch (NullPointerException e){

		}

		tv1= (TextView) findViewById(R.id.tv_refu_reason);
		tv1.setVisibility(View.GONE);
		tv2= (TextView) findViewById(R.id.refu_reason);
		tv2.setVisibility(View.GONE);
		try{
		//pingzhenglist= (ListView) findViewById(R.id.pingzhenglist);
			pzlist=new ArrayList<PingZItem>();
	//	adp=new PzAdapter(OrderinfoAty.this,R.layout.img_item,pzlist);
	//	pingzhenglist.setAdapter(adp);
		}catch (NullPointerException e){

		}

		id=intent.getStringExtra("id");
		Log.d(TAG, "onCreate: 此处的id为"+id);
		String name=intent.getStringExtra("name");
		String stage=intent.getStringExtra("stage");
		String address=intent.getStringExtra("address");
		String addressphonenum=intent.getStringExtra("address_phonenum");
		String trade_names=intent.getStringExtra("trade_names");
		realup= (CardView) findViewById(R.id.real_up);
		String mopay=intent.getStringExtra("mopay");
		String price=intent.getStringExtra("price");
		String paystage=intent.getStringExtra("paystage");
		String content=intent.getStringExtra("content");
		String o_time=intent.getStringExtra("ordertime");
		String waretype=intent.getStringExtra("waretype");
		String address_names=intent.getStringExtra("address_name");
		Log.d(TAG, "onCreate: "+addressphonenum);
		Log.d(TAG, "onCreate: "+trade_names);
		Log.d(TAG, "onCreate: +"+mopay);
		Log.d(TAG, "onCreate: "+price);
		Log.d(TAG, "onCreate: "+content);


		Log.d(TAG, "onCreate: stage="+stage);
		setBar(name+"订单详情",2);
		order_num= (TextView) findViewById(R.id.order_num);
		order_num.setText(id);
		trade_name= (TextView) findViewById(R.id.trade_name);
		trade_name.setText(trade_names);
		up_pingzheng= (RelativeLayout) findViewById(R.id.up_pingzheng2);
		realup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
setUpdate();


			}
		});
		trade_price= (TextView) findViewById(R.id.trade_price);
		trade_price.setText(price);
		stag_info = (TextView) findViewById(R.id.stag_info);//分期信息

		stag_info.setText(mopay+" * "+waretype);
		receiver= (TextView) findViewById(R.id.receiver);//收货人姓名
		receiver.setText(address_names);

		/**
		 * intent.putExtra("addressname",addressname);
		 intent.putExtra("addressphoenum",adddressphone);
		 intent.putExtra("o_time",time);
		 */
		TextView addresss= (TextView) findViewById(R.id.address);
		addresss.setText(address);
		receiver_phonenum = (TextView) findViewById(R.id.receiver_phonenum);//收货人电话
		receiver_phonenum.setText(addressphonenum);
		TextView time= (TextView) findViewById(R.id.order_time);
		time.setText(o_time);
		order_stage_green= (TextView) findViewById(R.id.order_stage_green);

		order_stage_red= (TextView) findViewById(R.id.order_stage_red);
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
			up_pingzheng.setVisibility(View.GONE);
			realup.setVisibility(View.GONE);
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
			up_pingzheng.setVisibility(View.VISIBLE);
			realup.setVisibility(View.VISIBLE);

			order_stage_green.setText("已通过");

		}else if (stage.equals("9")) {
			order_stage_green.setVisibility(View.VISIBLE);
			order_stage_red.setVisibility(View.GONE);
			up_pingzheng.setVisibility(View.GONE);
			realup.setVisibility(View.GONE);
			order_stage_green.setText("待签约");
		}else if (stage.equals("6")) {
			order_stage_green.setVisibility(View.VISIBLE);
			order_stage_red.setVisibility(View.GONE);
			up_pingzheng.setVisibility(View.VISIBLE);

			realup.setVisibility(View.GONE);
			order_stage_green.setText("已完成订单");
		}
try{
		refu_reason= (TextView) findViewById(R.id.refu_reason);
		refu_reason.setText(content);
}catch (NullPointerException e){}
		order_cusoname= (TextView) findViewById(R.id.order_cusoname);
		order_cusoname.setText(name);
	}

	private void selectPicture() {
		// TODO Auto-generated method stub
		Intent intent=new Intent(OrderinfoAty.this,ImagesSelectorActivity.class);
		intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER,5);
		intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE,100000);
		intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA,false);
		getUrl=new ArrayList<String>();
		intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST,getUrl);
		startActivityForResult(intent,123);
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==123){
			Log.d(TAG, "onActivityResult: 开始选择图片");
			if (resultCode==RESULT_OK){
				urllsit = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
				assert urllsit!=null;
				dbHelper.updatePingzheng(dbHelper,urllsit,id);
				//Log.d(TAG, "onActivityResult: "+urllsit.get(1));
				Log.d(TAG, "onActivityResult: urllist的长度是"+urllsit.size());
imgadp=new PhotoListAdapter(OrderinfoAty.this,urllsit);
				imglist.setAdapter(imgadp);
				dbHelper.pass(dbHelper, id, "6");

			}
		}

		Log.d(TAG, "onActivityResult: 返回图片");
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onStart() {
		imgadp=new PhotoListAdapter(this,urllsit);

		super.onStart();
	}
	public void setUpdate(){
		View view = LayoutInflater.from(this).inflate(R.layout.updateothers, null);
		final RelativeLayout album= (RelativeLayout) view.findViewById(R.id.up_album);
		album.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(OrderinfoAty.this,ImagesSelectorActivity.class);
				intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER,6);
				intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE,100000);
				intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA,false);
				intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST,getUrl);
				startActivityForResult(intent,123);

			}
		});
		RelativeLayout graph= (RelativeLayout) view.findViewById(R.id.graph);
		graph.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
				startActivityForResult(intent,5);

			}
		});
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(view).show();
	}
}
