package com.example.cxhll.huifenq;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuView;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Activitys.AnnouActivity;
import com.example.cxhll.huifenq.Activitys.BaseActivity;
import com.example.cxhll.huifenq.Activitys.ExpressActivity;
import com.example.cxhll.huifenq.Activitys.HomeOrderShowActivity;
import com.example.cxhll.huifenq.Activitys.McHome;
import com.example.cxhll.huifenq.Activitys.MesList;
import com.example.cxhll.huifenq.Activitys.MessAty;
import com.example.cxhll.huifenq.Activitys.MoneyAty;
import com.example.cxhll.huifenq.Activitys.MoneysActivity;
import com.example.cxhll.huifenq.Activitys.NewOrderAty;
import com.example.cxhll.huifenq.Activitys.NewTrainOrderAty;
import com.example.cxhll.huifenq.Activitys.NoticeActivity;
import com.example.cxhll.huifenq.Activitys.OrderListAty;
import com.example.cxhll.huifenq.Activitys.OrderManageActivity;
import com.example.cxhll.huifenq.Activitys.OrderinfoAty;
import com.example.cxhll.huifenq.Activitys.OverListActivity;
import com.example.cxhll.huifenq.Activitys.OverdueCustomesListActivity;
import com.example.cxhll.huifenq.Activitys.QrcodeAty;
import com.example.cxhll.huifenq.Activitys.SettingActivity;
import com.example.cxhll.huifenq.Activitys.SystemManageAty;
import com.example.cxhll.huifenq.Activitys.TipsActivity;
import com.example.cxhll.huifenq.Activitys.TuringActivity;
import com.example.cxhll.huifenq.Adapters.Adapt;
import com.example.cxhll.huifenq.Adapters.orderAdapt;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.dao.MessageListHelper;
import com.example.cxhll.huifenq.item.Message;
import com.example.cxhll.huifenq.item.OrderListItem;

import com.example.cxhll.huifenq.tools.UncaughtExceptionHandler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;

import com.makeramen.roundedimageview.RoundedImageView;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.BaseResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

import static android.R.attr.bitmap;
import static android.R.attr.priority;
import static com.example.cxhll.huifenq.R.id.dialog;
import static com.example.cxhll.huifenq.R.id.login_butt;
import static com.example.cxhll.huifenq.R.id.photos;

public class Hometest extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {
ArrayList<String> getUrl;
	private boolean backs=false;
	private  ArrayList<String> mresults=new ArrayList<>();
	private String TAG="Hometest.class";
	private ListView shenhelist;
	private orderAdapt oAdp;

	private ArrayList<OrderListItem> odlist;
	private ArrayList<OrderListItem> odlist2;
	private LineChart chart;
	private ImageView qrcode;
	private NavigationView head;
	private ImageView img;
	private TextView name;
	private TextView c_name;
	private TextView test;
	private 		LinearLayout overdue;
	private String cname="北京榆钱投资管理有限公司";
	private String names;
	private ImageView img0;
	private RoundedImageView photos;
	private LinearLayout server;
private 	String url;
HuifqDbHelper dbHelper=new HuifqDbHelper(this,"huifenqi.db",null,5);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Intent intent=getIntent();
Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this));

		//dbHelper.cunqian(dbHelper);
		 names=intent.getStringExtra("name");
		setContentView(R.layout.activity_hometest2);
	 overdue= (LinearLayout) findViewById(R.id.overdue_start);
	server= (LinearLayout) findViewById(R.id.server);
		TextView mState9= (TextView) findViewById(R.id.nine);
		TextView mState5= (TextView) findViewById(R.id.five);
		TextView mState0= (TextView) findViewById(R.id.zero);
		TextView mState1234= (TextView) findViewById(R.id.otsf);

		/*测试数据
		dbHelper.create_testtd(dbHelper,2001,1);
		dbHelper.create_testtd(dbHelper,1231,1);
		dbHelper.create_testtd(dbHelper,2111,0);
		dbHelper.create_testtd(dbHelper,211,1);
		dbHelper.create_testtd(dbHelper,2211,0);
		dbHelper.create_testtd(dbHelper,2121,1);
		dbHelper.create_testtd(dbHelper,4001,1);
		dbHelper.create_testtd(dbHelper,12301,1);
		dbHelper.create_testtd(dbHelper,211,0);
		dbHelper.create_testtd(dbHelper,2121,0);
*/
		Log.d(TAG, "onCreate: 返回的值为"+dbHelper.notpass(dbHelper,"6"));
		mState9.setText(dbHelper.notpass(dbHelper,"9"));
		mState5.setText(dbHelper.notpass(dbHelper,"5"));
		mState0.setText(dbHelper.notpass(dbHelper,"0"));
		mState1234.setText(dbHelper.notpass(dbHelper,"1"));
FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.floatingActionButton);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				opennewOrder();

			}
		});


		Intent intents=new Intent(Hometest.this,MessRuningService.class);
		startService(intents);
		Log.d(TAG, "服务已启动 ");
		String a=dbHelper.homeStage(dbHelper);
		Log.d(TAG, "onCreate: hometest中的homestage+++++++++");
		if (a.equals("0")){
			dbHelper.logingout(dbHelper);
			finish();
			Intent intent2=new Intent(Hometest.this, Login.class);
			startActivity(intent2);
		}
		c_name= (TextView) findViewById(R.id.c_name);
		c_name.setText(cname);

		qrcode= (ImageView) findViewById(R.id.qrcode);

		name= (TextView) findViewById(R.id.home_name);
		name.setText(names);

		qrcode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
		openrqcode();

			}
		});

		head= (NavigationView) findViewById(R.id.nav_view);
		View view=	head.getHeaderView(0);

		TextView name= (TextView) view.findViewById(R.id.test2);

		 photos= (RoundedImageView) view.findViewById(R.id.photos);

		Bitmap bitmap;


		try {
			bitmap = BitmapFactory.decodeFile(dbHelper.selectPhoto(dbHelper));

		if (!dbHelper.selectPhoto(dbHelper).equals("0")){

			bitmap = BitmapFactory.decodeFile(dbHelper.selectPhoto(dbHelper));
			photos.setImageBitmap(bitmap);

		}
		}catch (Exception e){

		}
		photos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				img0=photos;
				selectPicture();



			}
		});
		name.setText(names);
		test= (TextView) view.findViewById(R.id.test);
		test.setText(cname);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		dbHelper.test(dbHelper);
		setSupportActionBar(toolbar);


		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();
setcharts();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

		navigationView.setNavigationItemSelectedListener(this);

	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {

		getMenuInflater().inflate(R.menu.home, menu);

		// Inflate the menu; this adds items to the action bar if it is present.

	//	menu.getItem(6).setEnabled(false);


		final MenuItem item=menu.getItem(0);
		item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				Intent intent =new Intent(Hometest.this, NoticeActivity.class);
				startActivity(intent);
				item.setIcon(R.drawable.notification);
				return true;
			}
		});


			//getMenuInflater().inflate(R.menu.activity_hometest2_drawer,menu);

	//	item.setVisible(true);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {


		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement


		return super.onOptionsItemSelected(item);
	}


	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {


		// Handle navigation view item clicks here.
		int id = item.getItemId();

		Log.d(TAG, "MenuM:Item的ID是 "+id);
	 if (id == R.id.all_order) {
			Intent intent=new Intent(Hometest.this, OrderManageActivity.class);
			startActivity(intent);

			Log.d(TAG, "onNavigationItemSelected: 第二個啓動");

		} else if (id == R.id.all_shangpin) {
			Intent intent=new Intent(Hometest.this, McHome.class);
			startActivity(intent);

		}
		else if (id == R.id.money) {

				Intent intent=new Intent(Hometest.this, MoneysActivity.class);
			startActivity(intent);

		}
			else if (id == R.id.system) {
			Intent intent=new Intent(Hometest.this, SystemManageAty.class);
			startActivity(intent);
		}else if (id == R.id.nav_setting) {
			Intent intent=new Intent(Hometest.this, SettingActivity.class);
			startActivity(intent);
		}else if (id == R.id.nav_night) {
		 Intent intent=new Intent(Hometest.this, SettingActivity.class);
		 startActivity(intent);
	 }

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	protected void onStart() {
		server.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Hometest.this, TuringActivity.class);
				startActivity(intent);
			}
		});
		overdue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "onClick: 点击了这个一下");
				Intent intent=new Intent(Hometest.this, OverdueCustomesListActivity.class);
				//intent.putExtra("state","9");
				Log.d(TAG, "onClick: 准备启动");
				startActivity(intent);
			}
		});
		LinearLayout partone= (LinearLayout) findViewById(R.id.home_mpdle_one);
		partone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Hometest.this, HomeOrderShowActivity.class);
				intent.putExtra("state","9");
				startActivity(intent);
			}
		});
		LinearLayout parttwo= (LinearLayout) findViewById(R.id.home_mpdle_two);
		parttwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Hometest.this, HomeOrderShowActivity.class);
				intent.putExtra("state","5");
				startActivity(intent);
			}
		});
		LinearLayout partthree= (LinearLayout) findViewById(R.id.home_mpdle_three);
		partthree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Hometest.this, HomeOrderShowActivity.class);
				intent.putExtra("state","0");
				startActivity(intent);
			}
		});

		LinearLayout partfour= (LinearLayout) findViewById(R.id.home_mpdle_four);
		partfour.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

					Intent intent=new Intent(Hometest.this, HomeOrderShowActivity.class);
				intent.putExtra("state","1");
				startActivity(intent);
			}
		});

		LinearLayout anno= (LinearLayout) findViewById(R.id.anno);
		anno.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent=new Intent(Hometest.this, AnnouActivity.class);
				startActivity(intent);
			}
		});
		LinearLayout expresshelper= (LinearLayout) findViewById(R.id.express_helper);
		expresshelper.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent1=new Intent(Hometest.this, ExpressActivity.class);
				startActivity(intent1);
			}
		});
		try{
		chart.invalidate();
		odlist2=new ArrayList<OrderListItem>();
		orderlist();
		String a=dbHelper.homeStage(dbHelper);
		if (a.equals("0")){
			dbHelper.logingout(dbHelper);
			Intent intent=new Intent(Hometest.this, Login.class);
			startActivity(intent);


			finish();
		}
		}catch (Exception e){}
		super.onStart();
	}
	public Bitmap createrqcode(String url){

		Log.d(TAG, "createrqcode: 进入coders");
		Bitmap bitmap=null;
		Log.d(TAG, "openrqcode: 设定bitmap");
		Hashtable<EncodeHintType,String> hints=new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
		try {
			BitMatrix bitMatrix=new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE,300,300,hints);
			int[] pixels=new int[300*300];
			for (int y=0;y<300;y++){
				for (int x=0;x<300;x++){
					if (bitMatrix.get(x,y)){
						pixels[y*300+x]=0xff000000;

					}else {
						pixels[y*300+x]=0xffffffff;
					}
				}
			}
			bitmap=Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels,0,300, 0, 0,300, 300);
			Log.d(TAG, "createrqcode: 获取到bitmap"+bitmap);
		} catch (WriterException e) {
			Log.d(TAG, "createrqcode: 出错了");
			e.printStackTrace();
		}catch (NullPointerException e){

		}
		return  bitmap;
	}

	@Override
	protected void onRestart() {
setcharts();
		super.onRestart();
	}
	public  void  setcharts(){

	//	test.setText(cname);
		chart= (LineChart) findViewById(R.id.home_chart);
		chart.animateY(1000);
		chart.fitScreen();
		chart.setDescription("");
		chart.setNoDataTextDescription("您还没有订单，快去下单吧");
		ArrayList<String> xValue=new ArrayList<String>();
		Calendar c=Calendar.getInstance();
		int Month=c.get(Calendar.MONTH)+1;
		int Year=c.get(Calendar.YEAR);
		int day=c.get(Calendar.DAY_OF_MONTH);
		Log.d(TAG, "setcharts: 现在时间"+Year+Month+day);
		if (day-6<0){
			day=31+day;
			if (Month==1){
				Month=12;
			}else{
				Month--;
			}
		}
		Log.d(TAG, "setcharts: 现在时间"+Year+Month+day);
		day=day-6;
		Log.d(TAG, "setcharts: 现在时间"+Year+Month+day);
		for (int i=0;i<=7;i++){
			Log.d(TAG, "setcharts: 现在时间"+Year+Month+day);
			xValue.add(Month+"月"+day+"日");
			day++;
			if (Month==1||Month==3||Month==5||Month==7||Month==8||Month==10||Month==12){
				if (day==32){
					day=1;
					if (Month==12){
						Month=1;
					}else {
						Month++;
					}
					}
			}else if (Month==4||Month==6||Month==9||Month==11){
				if (day==31){
					day=1;
					if (Month==12){
						Month=1;
					}else {
						Month++;
					}
				}

			}else if (Year%400==0||Year%4==0&&Year%100!=0){
				if (Month==2){
					if (day==30){
						day=1;
						if (Month==12){
							Month=1;
						}else {
							Month++;
						}
					}else if (day==29){
						day=1;
						if (Month==12){
							Month=1;
						}else {
							Month++;
						}
					}
				}
			}
		}

		ArrayList<String> a=dbHelper.selecthome(dbHelper);
		int b,d,e,f,g,h,i;
		b= Integer.parseInt(a.get(0));
		d= Integer.parseInt(a.get(1));
		e= Integer.parseInt(a.get(2));
		f= Integer.parseInt(a.get(3));
		g= Integer.parseInt(a.get(4));
		h= Integer.parseInt(a.get(5));
		i= Integer.parseInt(a.get(6));


		ArrayList<Entry> yValue=new ArrayList<Entry>();
		yValue.add(new Entry(i,0));
		yValue.add(new Entry(h, 1));
		yValue.add(new Entry(g, 2));
		yValue.add(new Entry(f, 3));
		yValue.add(new Entry(e, 4));
		yValue.add(new Entry(d, 5));


		yValue.add(new Entry(b, 6));

		LineDataSet lineDataSet=new LineDataSet(yValue,Month+"月销量");

		lineDataSet.setLineWidth(1.75f);
		lineDataSet.setCircleSize(3f);
		lineDataSet.setColor(Color.RED);
		lineDataSet.setCircleColor(Color.WHITE);
		lineDataSet.setHighLightColor(Color.WHITE);
		ArrayList<LineDataSet> lineDataSets=new ArrayList<LineDataSet>();
		lineDataSets.add(lineDataSet);

		LineData lineData=new LineData(xValue,lineDataSets);
		chart.setDescriptionTextSize(16f);
		chart.setData(lineData);

	}
	public void orderlist(){
		shenhelist= (ListView) findViewById(R.id.c_newestlist);
try{
		Log.d(TAG, "onCreate: ");
		if (dbHelper.select_power(dbHelper,"rm_allorder")==1) {
			odlist=dbHelper.select_orders(dbHelper,1);
			oAdp = new orderAdapt(Hometest.this, R.layout.orderlist, odlist);
			shenhelist.setAdapter(oAdp);
		}else if(dbHelper.select_power(dbHelper,"rm_allorder")==0){
			if(dbHelper.select_power(dbHelper,"rm_checkorder")==1){
				odlist=dbHelper.select_orders(dbHelper,0);
				try {
					odlist2.add(odlist.get(0));
				}catch (IndexOutOfBoundsException e){

				}

				Log.d(TAG, "orderlist: "+odlist2);
				oAdp = new orderAdapt(Hometest.this, R.layout.orderlist, odlist2);
				shenhelist.setAdapter(oAdp);
			}
		}
}catch (NullPointerException e){

}
	}

	private void openrqcode(){
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		LayoutInflater inflater =getLayoutInflater();
		Log.d(TAG, "openrqcode: 进入code");
		try{
		View view=inflater.inflate(R.layout.activity_qrcode_aty, (ViewGroup) findViewById(R.id.activity_qrcode_aty));
			Log.d(TAG, "openrqcode:绑定view");
			ImageView imgs= (ImageView) view.findViewById(R.id.rqcode_show);
			Log.d(TAG, "openrqcode: 绑定img");
		TextView copy= (TextView) view.findViewById(R.id.clickcopy);
RoundedImageView qr_photo= (RoundedImageView) findViewById(R.id.qr_photo);

			try {

				Bitmap bitmap;
				Log.d(TAG, "openrqcode: 创建bitmap");
				bitmap = BitmapFactory.decodeFile(dbHelper.selectPhoto(dbHelper));
					
				if (!dbHelper.selectPhoto(dbHelper).equals("0")){
					Log.d(TAG, "openrqcode: 获取到bitmap");
					bitmap = BitmapFactory.decodeFile(dbHelper.selectPhoto(dbHelper));
					qr_photo.setImageBitmap(bitmap);

				}else{
					qr_photo.setImageResource(R.drawable.photos);
				}
			}catch (Exception e){

			}
			TextView name= (TextView) view.findViewById(R.id.qr_name);
			name.setText(names);
			Log.d(TAG, "openrqcode: 绑定copy");
		copy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ClipboardManager cm= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				cm.setText(names);
				Snackbar.make(view,"复制成功",Snackbar.LENGTH_SHORT).show();


			}
		});
		//builder.setTitle(names+"的二维码");
			Log.d(TAG, "openrqcode: "+names);
			Bitmap bitmaps;
			Log.d(TAG, "openrqcode: 创建bitmap");
			bitmaps=createrqcode(names);
			Log.d(TAG, "openrqcode: 获取Bitmap");
		imgs.setImageBitmap(bitmaps);
			imgs.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ClipboardManager cm= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					cm.setText(names);
					Snackbar.make(v,"复制成功",Snackbar.LENGTH_SHORT).show();
				}
			});
			builder.setView(view);
			builder.show();
	}catch (Exception e){

		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode==KeyEvent.KEYCODE_BACK){
			onDoubleClickBack();
		}

		return false;
	}
private void onDoubleClickBack(){
	Timer out=null;
	if (backs==false){
		backs=true;
		Toast.makeText(Hometest.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
		out=new Timer();
		out.schedule(new TimerTask() {
			@Override
			public void run() {
				backs=false;
			}
		},2000);
	}else {
		finish();
		System.exit(0);
	}
}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		Log.d(TAG, "onPrepareOptionsMenu: ---------进入了onp");

		return super.onPrepareOptionsMenu(menu);
	}

	public void visvialemenu(){


		//getMenuInflater().inflate(R.menu.hometest, menu);
	}
	private void selectPicture() {
		// TODO Auto-generated method stub
		Intent intent=new Intent(Hometest.this,ImagesSelectorActivity.class);
		intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER,1);
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
				getUrl = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
					assert getUrl!=null;
				photos.setImageBitmap(BitmapFactory.decodeFile(getUrl.get(0)));
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	private void opennewOrder() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = getLayoutInflater();
		Log.d(TAG, "openrqcode: 进入code");
		try {
			View view = inflater.inflate(R.layout.neworderdialog, (ViewGroup) findViewById(R.id.activity_qrcode_aty));
			Log.d(TAG, "openrqcode:绑定view");
			LinearLayout phones = (LinearLayout) view.findViewById(R.id.new_phones);
			phones.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Hometest.this, NewOrderAty.class);
					startActivity(intent);
				
				}
			});
			LinearLayout learns = (LinearLayout) view.findViewById(R.id.new_learns);
			learns.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Hometest.this, NewTrainOrderAty.class);
					startActivity(intent);
				}
			});
			LinearLayout woman = (LinearLayout) view.findViewById(R.id.new_womans);
			woman.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Hometest.this, NewOrderAty.class);
					startActivity(intent);
				}
			});
			if (dbHelper.select_power(dbHelper, " rm_neworder1") == 0) {
				phones.setVisibility(View.GONE);
			}
			if (dbHelper.select_power(dbHelper, " rm_neworder2") == 0) {
				learns.setVisibility(View.GONE);
			}
			if (dbHelper.select_power(dbHelper, " rm_neworder3") == 0) {
				woman.setVisibility(View.GONE);
			}


			builder.setView(view);
			builder.show();
		} catch (Exception e) {

		}

	}

}
