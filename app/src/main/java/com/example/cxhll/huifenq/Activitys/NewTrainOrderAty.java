package com.example.cxhll.huifenq.Activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Adapters.CustoAdapter;
import com.example.cxhll.huifenq.Adapters.NewOrderWaresAdapter;
import com.example.cxhll.huifenq.ChangeAddressPopwindow;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.CustoItem;
import com.example.cxhll.huifenq.item.WaresItem;
import com.example.cxhll.huifenq.tools.Pmt;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.ArrayList;

/**
 * Created by CXHLL on 2016/11/1.
 */

public class NewTrainOrderAty extends BaseActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{
	private EditText newPhonenum;//客户手机号
	private TextView canUseEdu;//可用额度
	private EditText newTradeName;//商品名称
	private EditText newTradePrice;
	private Spinner newStageNum;
	private float redu;
	private Spinner newGrace;
	private LinearLayout newGracell;
	private TextView newPayday;
	private EditText newPayMent;
	private RadioButton address_typeKD;
	private RadioButton address_typeZT;
	private EditText remarks;
	private Button sendNew;
	private RelativeLayout rlnew;
	private EditText receiver;
	private EditText receiverphonenum;
	private EditText addressdetil;
	private ArrayList<String> infolist;
	private ArrayAdapter stageNumAdap;
	private TextView mopay;
	private String addressType;
	private ArrayAdapter graceAdap;
	private ArrayList<String> graceList;
	private TextView phone_hini;
	private String name;
	private TextView edu_hini;
	private TextView show_address;
	private Spinner waretype;
	private ArrayList<String> typelist;
	private TextView checkday;
	private ArrayList<String> orderinfos;
	private EditText paymore;//订单加息；
	int payment=0;
	private String price;
	private String paymentnum;
private String address_province;
	private String address_city;
	private String address_area;
	String dizhi="";
	private Spinner waretypes;

	private ImageView brow_custo;
	private ImageView brow_ware;
	private ArrayList<String> dates;

	private ArrayAdapter<String> adp;

	private java.util.Calendar c = java.util.Calendar.getInstance();

/**
 * 此部分需要完善的东西有
 *
 * 5.还需要把分期信息给传到Spinner中
 */
public static final String DATEPICKER_TAG="datepicker";
	public static final String TIMEPICKER_TAG="timepicker";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newtrainorder_layout);
		Log.d(TAG, "onCreate: 进入新订单页面1");

		setBar("新订单",2);

		final Pmt pmt=new Pmt();
		java.util.Calendar calendar= java.util.Calendar.getInstance();

		checkday= (TextView) findViewById(R.id.check_payday);

		final DatePickerDialog datePickerDialog=DatePickerDialog.newInstance(this,calendar.get(java.util.Calendar.YEAR),calendar.get(java.util.Calendar.MONTH),calendar.get(java.util.Calendar.DAY_OF_MONTH),isVibrate());
		TimePickerDialog timePickerDialog=TimePickerDialog.newInstance(this,calendar.get(java.util.Calendar.HOUR_OF_DAY),calendar.get(java.util.Calendar.MINUTE),false,false);
		Log.d(TAG, "onCreate: 进入新订单页面2");
		checkday.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				InputMethodManager inm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inm.hideSoftInputFromWindow(view.getWindowToken(),0);
				datePickerDialog.setVibrate(isVibrate());

				datePickerDialog.setYearRange(c.get(java.util.Calendar.YEAR),c.get(java.util.Calendar.YEAR)+1);
				datePickerDialog.setCloseOnSingleTapDay(isCloseOnSingleTapDay());
				datePickerDialog.show(getSupportFragmentManager(),DATEPICKER_TAG);
			}
		});
		if (savedInstanceState != null) {
			DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);

			if (dpd != null) {
				dpd.setOnDateSetListener(this);
			}

			TimePickerDialog tpd = (TimePickerDialog) getSupportFragmentManager().findFragmentByTag(TIMEPICKER_TAG);
			if (tpd != null) {
				tpd.setOnTimeSetListener(this);
			}
		}

		ArrayAdapter<String> spadp;
		waretype= (Spinner) findViewById(R.id.sp_new_stagenum);
		typelist=new ArrayList<String>();

		typelist.add("6");
		typelist.add("9");
		typelist.add("12");
		typelist.add("15");
		typelist.add("18");
		typelist.add("21");
		typelist.add("24");
		Log.d(TAG, "onCreate: 进入新订单页面3");
		spadp=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,typelist);
		spadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		waretype.setAdapter(spadp);
	brow_custo= (ImageView) findViewById(R.id.browse_custo);
		brow_ware= (ImageView) findViewById(R.id.browse_ware);
		brow_ware.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			setWareList();
			}
		});


		brow_custo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			setCostuList();
			}
		});

		waretype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				try{
					if (newTradePrice.getText().toString().equals("")){
						mopay.setText("");
					}
					if (newTradePrice.getText().toString().equals(""))
					{
						price="0";
					}else{
						price=newTradePrice.getText().toString();
					}
					if (newPayMent.getText().toString().equals(""))
					{
						paymentnum="0";
					}else{
						paymentnum =newPayMent.getText().toString();
					}
				}catch (NullPointerException e){
					price="0";
					paymentnum="0";
				}


				mopay.setText(pmt.pmt(price,waretype.getSelectedItem().toString(),paymentnum,0f,0f,0f,0,0,0));
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});
		Log.d(TAG, "onCreate: 进入新订单页面4");
				final String select=waretype.getSelectedItem().toString();
		addressType="0";
		show_address= (TextView) findViewById(R.id.show_new_address);



		rlnew= (RelativeLayout) findViewById(R.id.rl_new_receiver);
		receiver= (EditText) findViewById(R.id.et_new_receiver);
		receiverphonenum= (EditText) findViewById(R.id.et_new_receiverphonenum);
		receiverphonenum.getText();
		addressdetil= (EditText) findViewById(R.id.et_new_address);
		newPhonenum = (EditText) findViewById(R.id.new_order_phonenum_put);
		newTradeName = (EditText) findViewById(R.id.et_new_tradename);
		mopay= (TextView) findViewById(R.id.new_mopaytext);
		Log.d(TAG, "onCreate: 进入新订单页面5");
		show_address.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				InputMethodManager inm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inm.hideSoftInputFromWindow(view.getWindowToken(),0);
				ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(NewTrainOrderAty.this);
				mChangeAddressPopwindow.setAddress("湖北", "武汉", "武昌区");
				mChangeAddressPopwindow.showAtLocation(show_address, Gravity.BOTTOM, 0, 0);
				mChangeAddressPopwindow
						.setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

							@Override
							public void onClick(String province, String city, String area) {
								// TODO Auto-generated method stub
								Log.d(TAG, "onClick: 开始选择");
								address_province=province;
								address_city=city;
								address_area=area;
								if (province.equals("天津")||province.equals("北京")||province.equals("上海")||province.equals("重庆")){
									address_city=city;
									address_province=province+"市";
									address_city=province+"市";
								}else{
									address_province=province+"省";
									address_city=city+"市";
									address_area=area;
								}




								show_address.setText(address_province +  address_city+address_area);

								}
						});
			}
		});

		canUseEdu = (TextView) findViewById(R.id.canuse_edu);
		newPhonenum.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				if (newTradeName.getText().toString().equals(""))
				{
					canUseEdu.setText("");
				}



			}
			/*

receiver.getText().toString().equals("")
						&&receiverphonenum.getText().toString().equals("")
						&&addressdetil.getText().toString().equals("")
				&&addressdetil.getText().toString().equals("")
						&&&&newTradePrice.getText().toString().equals("")){
					phone_hini.setText("");
 */


			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

				try {
					String edu = dbHelper.select_edu(dbHelper, newPhonenum.getText().toString());
					Log.d(TAG, "onTextChanged: 18202770371的额度为"+dbHelper.select_edu(dbHelper,"18202770371"));
					redu=Float.parseFloat(edu);
					if (edu.equals("00000")){
						canUseEdu.setText("");
					}else if(edu.equals("0"))
					{
						canUseEdu.setText("");
					}else {
						canUseEdu.setText(edu);
						name=dbHelper.select_name(dbHelper,newPhonenum.getText().toString());
					}
				}catch (IndexOutOfBoundsException e){

				}
			}



			public void afterTextChanged(Editable editable) {
try {
	String edu =  dbHelper.select_edu(dbHelper, newPhonenum.getText().toString());

	redu=Float.parseFloat(edu);
				if (edu.equals("00000")&&newPhonenum.getText().toString().length()==11){
					canUseEdu.setText("未注册");
				}else if(edu.equals("0")&&newPhonenum.getText().toString().length()==11)
				{
					canUseEdu.setText("未注册");
				}else {
					canUseEdu.setText(edu);
					name=dbHelper.select_name(dbHelper,newPhonenum.getText().toString());
				}
			}catch (IndexOutOfBoundsException e){

}
			}
		});


		newTradeName.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				if (newPhonenum.getText().toString().equals("")){

				}
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				newTradeName.setTextSize(16);
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});
		newTradePrice = (EditText) findViewById(R.id.et_new_tradeprice);
		newTradePrice.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    if (canUseEdu.getText().toString().equals("")){
                        newTradePrice.setError("用户未注册");

                    }
                    else if (newTradePrice.getText().toString().equals("")){
                        mopay.setText("");
                    }
                    else if(Integer.parseInt(newTradePrice.getText().toString())-payment>redu){

                        newTradePrice.setError("商品金额过高");
                    }
                    try{
                        if (newTradePrice.getText().toString().equals(""))
                        {
                            price="0";
                        }else{
                            price=newTradePrice.getText().toString();
                        }
                        if (newPayMent.getText().toString().equals(""))
                        {
                            paymentnum="0";
                        }else{
                            paymentnum =newPayMent.getText().toString();
                        }

                    }catch (NullPointerException e){
                        price="0";
                        paymentnum="0";
                    }
                    String mopays=pmt.pmt(price,waretype.getSelectedItem().toString(),paymentnum,0f,0f,0f,0,0,0);
                    float calcmopay= Float.parseFloat(mopays);
                    int fenqi= Integer.parseInt(waretype.getSelectedItem().toString());
                    if (calcmopay*fenqi>redu){

                        newTradePrice.setError("商品金额过高");
                    }
                    mopay.setText(mopays);
                    int paymentmoney= Integer.parseInt(newPayMent.getText().toString());
                    if (paymentmoney>Integer.parseInt(newTradePrice.getText().toString())){
                        newPayMent.setError("首付金额大于商品金额");
                        mopay.setText("");
                    }
                }catch (NumberFormatException e){

                }catch (NullPointerException e){

                }

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				try{
					if (canUseEdu.getText().toString().equals("")){
					newTradePrice.setError("用户未注册");

					}
					else if (newTradePrice.getText().toString().equals("")){
						mopay.setText("");
					}
					 else if(Integer.parseInt(newTradePrice.getText().toString())-payment>redu){

						newTradePrice.setError("商品金额过高");
					}
					try{
						if (newTradePrice.getText().toString().equals(""))
						{
							price="0";
						}else{
							price=newTradePrice.getText().toString();
						}
						if (newPayMent.getText().toString().equals(""))
						{
							paymentnum="0";
						}else{
							paymentnum =newPayMent.getText().toString();
						}

					}catch (NullPointerException e){
						price="0";
						paymentnum="0";
					}
					String mopays=pmt.pmt(price,waretype.getSelectedItem().toString(),paymentnum,0f,0f,0f,0,0,0);
					float calcmopay= Float.parseFloat(mopays);
					int fenqi= Integer.parseInt(waretype.getSelectedItem().toString());
					if (calcmopay*fenqi>redu){

						newTradePrice.setError("商品金额过高");
					}
					mopay.setText(mopays);
					int paymentmoney= Integer.parseInt(newPayMent.getText().toString());
					if (paymentmoney>Integer.parseInt(newTradePrice.getText().toString())){
						newPayMent.setError("首付金额大于商品金额");
						mopay.setText("");
					}
				}catch (NumberFormatException e){

				}catch (NullPointerException e){

				}

				}

			@Override
			public void afterTextChanged(Editable editable) {
                try{
                    if (canUseEdu.getText().toString().equals("")){
                        newTradePrice.setError("用户未注册");

                    }
                    else if (newTradePrice.getText().toString().equals("")){
                        mopay.setText("");
                    }
                    else if(Integer.parseInt(newTradePrice.getText().toString())-payment>redu){

                        newTradePrice.setError("商品金额过高");
                    }
                    try{
                        if (newTradePrice.getText().toString().equals(""))
                        {
                            price="0";
                        }else{
                            price=newTradePrice.getText().toString();
                        }
                        if (newPayMent.getText().toString().equals(""))
                        {
                            paymentnum="0";
                        }else{
                            paymentnum =newPayMent.getText().toString();
                        }

                    }catch (NullPointerException e){
                        price="0";
                        paymentnum="0";
                    }
                    String mopays=pmt.pmt(price,waretype.getSelectedItem().toString(),paymentnum,0f,0f,0f,0,0,0);
                    float calcmopay= Float.parseFloat(mopays);
                    int fenqi= Integer.parseInt(waretype.getSelectedItem().toString());
                    if (calcmopay*fenqi>redu){

                        newTradePrice.setError("商品金额过高");
                    }
                    mopay.setText(mopays);
                    int paymentmoney= Integer.parseInt(newPayMent.getText().toString());
                    if (paymentmoney>Integer.parseInt(newTradePrice.getText().toString())){
                        newPayMent.setError("首付金额大于商品金额");
                        mopay.setText("");
                    }
                }catch (NumberFormatException e){

                }catch (NullPointerException e){

                }

            }
		});
paymore= (EditText) findViewById(R.id.pay_more);

		try{
		newStageNum = (Spinner) findViewById(R.id.sp_new_stagenum);

		newGrace = (Spinner) findViewById(R.id.sp_new_tgrace);
			ArrayAdapter<String> spadp1;
			waretype= (Spinner) findViewById(R.id.sp_new_stagenum);
			ArrayList<String> typelist1 = new ArrayList<String>();

			typelist1.add("6");
			typelist1.add("7");
			typelist1.add("8");
			typelist1.add("9");
			Log.d(TAG, "onCreate: 进入新订单页面3");
			spadp1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,typelist1);
			spadp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			newGrace.setAdapter(spadp1);
		newGracell = (LinearLayout) findViewById(R.id.ll_new_grace);
		newPayday = (TextView) findViewById(R.id.tv_new_payday);
		newPayMent = (EditText) findViewById(R.id.pay_ment);
			newPayMent.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

				}

				@Override
				public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
					try{

						if (newTradePrice.getText().toString().equals("")){
							mopay.setText("");
						}
						if(Integer.parseInt(newTradePrice.getText().toString())-payment>redu){

							newTradePrice.setError("商品金额过高");
						}
						try{
							if (newTradePrice.getText().toString().equals(""))
							{
								price="0";
							}else{
								price=newTradePrice.getText().toString();
							}
							if (newPayMent.getText().toString().equals(""))
							{
								paymentnum="0";
							}else{
								paymentnum =newPayMent.getText().toString();
							}
						}catch (NullPointerException e){
							price="0";
							paymentnum="0";
						}
						String mopays=pmt.pmt(price,waretype.getSelectedItem().toString(),paymentnum,0f,0f,0f,0,0,0);
						float calcmopay= Float.parseFloat(mopays);

						int fenqi= Integer.parseInt(waretype.getSelectedItem().toString());
						int paymentmoney= Integer.parseInt(newPayMent.getText().toString());
						if (paymentmoney>Integer.parseInt(newTradePrice.getText().toString())){
							newPayMent.setError("首付金额大于商品金额");
							mopay.setText("");
						}
						if (calcmopay*fenqi>8000f){

							newTradePrice.setError("商品金额过高");
						}else{

                        }
						mopay.setText(mopays);

					}catch (NumberFormatException e){

					}catch (NullPointerException e){

					}
				}

				@Override
				public void afterTextChanged(Editable editable) {
                    try{

                        if (newTradePrice.getText().toString().equals("")){
                            mopay.setText("");
                        }
                        if(Integer.parseInt(newTradePrice.getText().toString())-payment>redu){

                            newTradePrice.setError("商品金额过高");
                        }
                        try{
                            if (newTradePrice.getText().toString().equals(""))
                            {
                                price="0";
                            }else{
                                price=newTradePrice.getText().toString();
                            }
                            if (newPayMent.getText().toString().equals(""))
                            {
                                paymentnum="0";
                            }else{
                                paymentnum =newPayMent.getText().toString();
                            }
                        }catch (NullPointerException e){
                            price="0";
                            paymentnum="0";
                        }
                        String mopays=pmt.pmt(price,waretype.getSelectedItem().toString(),paymentnum,0f,0f,0f,0,0,0);
                        float calcmopay= Float.parseFloat(mopays);

                        int fenqi= Integer.parseInt(waretype.getSelectedItem().toString());
                        int paymentmoney= Integer.parseInt(newPayMent.getText().toString());
                        if (paymentmoney>Integer.parseInt(newTradePrice.getText().toString())){
                            newPayMent.setError("首付金额大于商品金额");
                            mopay.setText("");
                        }
                        if (calcmopay*fenqi>8000f){

                            newTradePrice.setError("商品金额过高");
                        }else{

                        }
                        mopay.setText(mopays);

                    }catch (NumberFormatException e){

                    }catch (NullPointerException e){

                    }
				}
			});
		 payment=Integer.parseInt(newPayMent.getText().toString());
		}catch (NumberFormatException e){

		}catch (NullPointerException e){

		}
		address_typeKD = (RadioButton) findViewById(R.id.address_type_kuaidi);
		address_typeKD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				address_typeKD.setChecked(true);
				address_typeZT.setChecked(false);
				addressType="0";
				Log.d(TAG, "onClick: "+addressType);
				rlnew.setVisibility(View.VISIBLE);

			}
		});
		address_typeZT = (RadioButton) findViewById(R.id.address_type_ziti);
		address_typeZT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				address_typeKD.setChecked(false);
				address_typeZT.setChecked(true);
				addressType="1";
				rlnew.setVisibility(View.GONE);
			}
		});


		remarks = (EditText) findViewById(R.id.remarks);



		sendNew = (Button) findViewById(R.id.send_order);



		sendNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			button();
			}
		});









	}

	private boolean isCloseOnSingleTapDay() {
	return false;
	}

	private boolean isVibrate() {

		return false;
	}


	@Override
	public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
		checkday.setText("每月"+day+"日");
		if (day==29||day==30||day==31){
			Toast.makeText(this,"还款日不能为29，30，31日,请重新选择",Toast.LENGTH_SHORT).show();
		}
		dateCheck(day,month+1,year);
	}

	@Override
	public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

	}

	@Override
	protected void onStart() {
		try{
			Intent intent=getIntent();
			if (intent.getStringExtra("retry").equals("1")) {
				String id = intent.getStringExtra("id");


				newPhonenum.setText(intent.getStringExtra("phonenum"));
				newTradePrice.setText(intent.getStringExtra("price"));
				newTradeName.setText(intent.getStringExtra("tradename"));
				show_address.setText(intent.getStringExtra("province")+intent.getStringExtra("city")+intent.getStringExtra("area"));
				addressdetil.setText(intent.getStringExtra("addressdetil"));
			}
		}catch (NullPointerException e){}


		super.onStart();
	}

	public void button(){
		boolean b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,b01,b02;
		if (waretypes.getSelectedItem().toString().equals("请选择商品类型")){
			b01=false;
			Toast.makeText(NewTrainOrderAty.this,"请选择商品类型",Toast.LENGTH_SHORT).show();
		}else {
			b01=true;
		}
		if (canUseEdu.getText().toString().equals("")){
			canUseEdu.setText("未找到用户");
			b0=false;
		}else {
			b0=true;
		}
		if(!newPhonenum.getText().toString().equals(""))
		{
			b1=true;
		}else{
			b1=false;
			newPhonenum.setError("请输入客户手机号");

		}
		if (newPhonenum.getText().toString().length()!=11){
			newPhonenum.setError("请输入正确的手机号");
			b5=false;
		}else {
			b5=true;
		}
		if(!newTradeName.getText().toString().equals(""))
		{
			b2=true;
		}else{
			b2=false;
			newTradeName.setError("请输入商品名称");

		}
		if(!newTradePrice.getText().toString().equals(""))
		{
			b3=true;
		}else{
			b3=false;
			newTradePrice.setError("请输入商品价格");

		}
		/***
		 * 	if(show_address.getText().toString().equals("轻触选择地区"))
		 {
		 b8=false;
		 Toast.makeText(this,"请选择收货地址",Toast.LENGTH_SHORT).show();
		 }else{
		 b8=true;
		 }
		 */
		if(checkday.getText().toString().equals("轻触选择还款日"))
		{
			Toast.makeText(this,"请选择还款日期",Toast.LENGTH_SHORT).show();
			b4=false;
		}else{
			b4=true;
		}
		//if(!o_type.getSelectItem.equals("商品类型"))
	//	{
		//	b5=true;
	//	}else{
		//	b5=false;
			//Toast.makeText(this,"请选择商品类型",Toast.LENGTH_SHORT).show();

	//	}
		if(addressType.equals("1")){
			if(!receiver.getText().toString().equals(""))
			{
				b6=true;
			}else{
				b6=false;
				receiver.setError("请输入收货人姓名");

			}
			if(!receiverphonenum.getText().toString().equals(""))
			{
				b7=true;
			}else{
				b7=false;
				receiverphonenum.setError("请输入收货人电话");

			}
			if (receiverphonenum.getText().toString().length()!=11){
				receiverphonenum.setError("请输入正确的手机号");
				b02=false;
			}else {
				b02=true;
			}
			if(show_address.getText().toString().equals("轻触选择地区"))
			{
				b8=false;
				Toast.makeText(this,"请选择收货地址",Toast.LENGTH_SHORT).show();
			}else{
				b8=true;
			}
			if(!addressdetil.getText().toString().equals(""))
			{
				b9=true;
			}else{
				b9=false;
				addressdetil.setError("请输入客户详细地址");
			}
		}else if (addressType.equals("0")){
			b6=true;
			b7=true;
			b8=true;
			b9=true;
			b02=true;
		}
		b6=true;
		b7=true;
		b8=true;
		b9=true;
		b02=true;
//&& b5
		if(b1 && b2 && b3 && b4 &&b5 && b6 && b7 && b8 && b9&&b0 &&b01){

			if (addressType.equals("1")){
				dizhi=show_address.getText().toString()+addressdetil.getText().toString();
			}else if (addressType.equals("0"))
			{
				dizhi="客户自提";
			}

			//获取0-6之间的随机数
			int a=(int)(Math.random()*6);
			String state=String.valueOf(a);
			dbHelper.creat_order(dbHelper,
					newPhonenum.getText().toString(),
					newTradeName.getText().toString(),
					newTradePrice.getText().toString(),
					state,
					newPayMent.getText().toString(),
					newPayday.getText().toString(),
					addressType,
					address_province,
					address_city,
					address_area,
					addressdetil.getText().toString(),
					remarks.getText().toString(),
					String.valueOf(newStageNum.getSelectedItemPosition()),
					mopay.getText().toString(),
					name,
					receiver.getText().toString(),
					receiverphonenum.getText().toString(),
					waretype.getSelectedItem().toString());//月供和分期期数
			Log.d(TAG, "button: "+dizhi);
			Log.d(TAG, "onClick: 准备用newORDER");
			Intent intent=new Intent(NewTrainOrderAty.this,OrderSuccAty.class);
			intent.putExtra("phonenum",String.valueOf(newPhonenum.getText()));
			intent.putExtra("receiver",String.valueOf(name));
			intent.putExtra("receiverphonenum",String.valueOf(receiverphonenum.getText()));
			intent.putExtra("phonenum",String.valueOf(newPhonenum.getText()));
			intent.putExtra("addresstype",addressType);
			intent.putExtra("addressname",receiver.getText().toString());
			intent.putExtra("address",String.valueOf(dizhi));
			startActivity(intent);

			dbHelper.update_edu(dbHelper,newPhonenum.getText().toString(),Float.parseFloat(newTradePrice.getText().toString())-payment);
			finish();

//设置button可点击
		}

}
	public void dateCheck(int day,int month,int year) {


		int theDay = c.get(java.util.Calendar.DAY_OF_MONTH);

		int theMonth = c.get(java.util.Calendar.MONTH)+1;


		int theYear = c.get(java.util.Calendar.YEAR);

		if (theYear == year) {
			if (theMonth == month) {
				if (theDay > day || theDay == day) {
					Toast.makeText(this, "您选择的还款日不能选择更早的时间", Toast.LENGTH_SHORT).show();
				}
			} else if (theMonth < month) {
				if (theMonth+1<month){



				if ((getDayNum(theMonth, year) - theDay) + day > 30) {
					Toast.makeText(this, "您选择的还款日不能晚于" + month + "月" + (30 - (getDayNum(theMonth, year) - theDay)) + "日", Toast.LENGTH_SHORT).show();

				}
				}

			}else if (theMonth >month){
				Toast.makeText(this, "您选择的还款日不能选择更早的时间", Toast.LENGTH_SHORT).show();
			}
		} else if (year - theYear == 1) {
			if ((getDayNum(theMonth, year) - theDay) + day > 30) {
				Toast.makeText(this, "您选择的还款日不能晚于1月"  + (30 - (getDayNum(theMonth, year) - theDay)) + "日", Toast.LENGTH_SHORT).show();
			}else if (month>1){
				Toast.makeText(this, "您选择的还款日不能晚于1月" + (30 - (getDayNum(theMonth, year) - theDay)) + "日", Toast.LENGTH_SHORT).show();
			}
		}
	}



	public int getDayNum(int month,int year){
		int daynum=31;
		if((year%4==0)&&(year%100!=0)&&(month+1==2)){



			daynum=29;

		}else if(month+1==2){
			daynum=28;
		}

		if(month+1==4||month+1==6||month+1==9||month+1==11){
			daynum=30;
		}
		return daynum;
	}


	public void setCostuList(){
		LayoutInflater inflater =getLayoutInflater();
		final View dia= inflater.inflate(R.layout.custolist, (ViewGroup) findViewById(R.id.custolist));
		final ListView textView= (ListView) dia.findViewById(R.id.custolist);
		final TextView ifnote= (TextView)dia. findViewById(R.id.ifyounote);
		ArrayList<CustoItem> culist=new ArrayList<>();
		culist=dbHelper.selectallkehu(dbHelper);
		CustoAdapter adapter=new CustoAdapter(this,R.layout.custoitem,culist);
		textView.setAdapter(adapter);
        final ArrayList<CustoItem> finalCulist = culist;
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				CustoItem item= finalCulist.get(position);
                newPhonenum.setText(item.getPhonenum());

			}
		});
		final AlertDialog.Builder builder=new AlertDialog.Builder(NewTrainOrderAty.this);
		builder.setPositiveButton("确定", null);
		textView.setEmptyView(ifnote);
		builder.setTitle("选择客户");
		builder.setView(dia);
		builder.show();
	}


	public void setWareList(){
		LayoutInflater inflater =getLayoutInflater();
		final View dia= inflater.inflate(R.layout.custolist, (ViewGroup) findViewById(R.id.custolist));
		final ListView textView= (ListView) dia.findViewById(R.id.custolist);
        ArrayList<WaresItem> lists;
        NewOrderWaresAdapter mAdapter = null;
        lists =new ArrayList<WaresItem>();


        lists=dbHelper.selectWares("null",dbHelper);
        mAdapter=new NewOrderWaresAdapter(NewTrainOrderAty.this,R.layout.newordermcitem,lists);
		final TextView ifnote= (TextView)dia.findViewById(R.id.ifyounote);

		ifnote.setText("您的商品列表为空");
        textView.setAdapter(mAdapter);
        final ArrayList<WaresItem> finalLists = lists;
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WaresItem item= finalLists.get(position);
                newTradeName.setText(item.getName());
                newTradePrice.setText(item.getPrice());
                newStageNum.setSelection(Integer.parseInt(item.getType()));

			}
        });
        final AlertDialog.Builder builder=new AlertDialog.Builder(NewTrainOrderAty.this);
		builder.setPositiveButton("确定", null);
		textView.setEmptyView(ifnote);
		builder.setTitle("选择商品");
		builder.setView(dia);
		builder.show();
	}

}
