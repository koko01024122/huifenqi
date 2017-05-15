package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;

import java.util.ArrayList;


/**
 * Created by CXHLL on 2016/11/26.
 */

public class RmAty extends BaseActivity {



	private android.widget.CheckBox   rm_orderm;//订单管理;
	private android.widget.CheckBox   rm_neworder1;//商品分期下单;
	private android.widget.CheckBox   rm_neworder2;//教育分期下单;
	private android.widget.CheckBox   rm_neworder3;//医美分期下单;
	private android.widget.CheckBox   rm_checkorder;//查看订单;
	private android.widget.CheckBox   rm_checksonorder;//查看子账号订单;
	private android.widget.CheckBox   rm_allorder;//查看全部订单;

	private android.widget.CheckBox   rm_comm;//商品管理;
	private android.widget.CheckBox   rm_add_comm;//编辑商品;
	private android.widget.CheckBox   rm_edit_comm;//添加商品;

	private android.widget.CheckBox   rm_money;//财务中心;
	private android.widget.CheckBox   rm_takemoney;//提现;
	private android.widget.CheckBox   rm_checkself;//查看销售额（自己）;
	private android.widget.CheckBox   rm_checksonsale;//查看子账号销售额;
	private android.widget.CheckBox   rm_checkallsale;//查看全部销售额;
	private android.widget.CheckBox   rm_checkjorn;//查看财务报表;

	private android.widget.CheckBox   rm_mess;//站内信;
	private android.widget.CheckBox   rm_checkmess;//站内信;
	private android.widget.CheckBox   rm_reportmess;//补交资料;

	private android.widget.CheckBox   rm_system;//系统权限;
	private android.widget.CheckBox   rm_rbac;//权限管理;
	private android.widget.CheckBox   rm_changepass;//密码修改;
	private android.widget.CheckBox   rm_makeson;//子账号建立;
//	String  rm_nametilau="0";

	//String  rm_nameau="0";//角色名称au="0";


	String  rm_ordermau="0";//订单管理au="0";

	String  rm_neworder1au="0";//商品分期下单au="0";

	String  rm_neworder2au="0";//教育分期下单au="0";

	String  rm_neworder3au="0";//医美分期下单au="0";

	String  rm_checkorderau="0";//查看订单au="0";

	String  rm_checksonorderau="0";//查看子账号订单au="0";

	String  rm_allorderau="0";//查看全部订单au="0";



	String  rm_commau="0";//商品管理au="0";

	String  rm_add_commau="0";//编辑商品au="0";

	String  rm_edit_commau="0";//添加商品au="0";



	String  rm_moneyau="0";//财务中心au="0";

	String  rm_takemoneyau="0";//提现au="0";

	String  rm_checkselfau="0";//查看销售额（自己）au="0";

	String  rm_checksonsaleau="0";//查看子账号销售额au="0";

	String  rm_checkallsaleau="0";//查看全部销售额au="0";

	String  rm_checkjornau="0";//查看财务报表au="0";



	String  rm_messau="0";//站内信au="0";

	String  rm_checkmessau="0";//站内信au="0";

	String  rm_reportmessau="0";//补交资料au="0";



	String  rm_systemau="0";//系统权限au="0";

	String  rm_rbacau="0";//权限管理au="0";

	String  rm_changepassau="0";//密码修改au="0";

	String  rm_makesonau="0";//子账号建立au="0";
	private Button rm_finish;
	private String update="0";
	ArrayList<String> aus;
	private TextInputLayout getname;
	String name;
	private EditText names;
	private String TAG="Rmaty.class";

	private String id;
	private LinearLayout la;
	private LinearLayout lb;
	private LinearLayout lc;
	private LinearLayout ld;
	private LinearLayout le;
	private RelativeLayout ra;
	private RelativeLayout rb;
	private LinearLayout rc;
	private LinearLayout da;
	private LinearLayout ea;
	private  int a=0;
	private  int b=0;
	private  int c=0;
	private  int d=0;
	private  int e=0;
	/***
	 * 接下来要补充数据库操作
	 * @param savedInstanceState
	 */

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rm_item);
		setDbHelper(RmAty.this);
		getname= (TextInputLayout) findViewById(R.id.rm_nametil);
		getname.setHint("角色名称");
		names=getname.getEditText();
		aus=new ArrayList<String>();

		rm_finish= (Button) findViewById(R.id.rm_finish);
		Intent intent=getIntent();

		try{
				 update = intent.getStringExtra("update");
			Log.d(TAG, "onCreate: "+intent.getStringExtra("update"));

			if (update.equals("1")){
				 id=intent.getStringExtra("id");
				setBar("编辑角色",2);
				Log.d(TAG, "onCreate: id为"+id);
			aus=dbHelper.select_role(dbHelper,id);
				names.setText(aus.get(23));
			}else {
				update="0";
				setBar("创建角色",2);
			}
		}catch (NullPointerException e){

		}
	name="";
		rm_finish.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "onClick: update的值为"+update);
				if (update.equals("0")){
	name=names.getText().toString();
			dbHelper.create_role(
					dbHelper,
					name,
					rm_ordermau,//订单管理au,
					rm_neworder1au,//商品分期下单au,
					rm_neworder2au,//教育分期下单au,

					rm_neworder3au,//医美分期下单au,

					rm_checkorderau,//查看订单au,

					rm_checksonorderau,//查看子账号订单au,

					rm_allorderau,//查看全部订单au,



					rm_commau,//商品管理au,

					rm_add_commau,//编辑商品au,

					rm_edit_commau,//添加商品au,



					rm_moneyau,//财务中心au,

					rm_takemoneyau,//提现au,

					rm_checkselfau,//查看销售额（自己）au,

					rm_checksonsaleau,//查看子账号销售额au,

					rm_checkallsaleau,//查看全部销售额au,

					rm_checkjornau,//查看财务报表au,



					rm_messau,//站内信au,

					rm_checkmessau,//站内信au,

					rm_reportmessau,//补交资料au,



					rm_systemau,//系统权限au,

					rm_rbacau,//权限管理au,

					rm_changepassau,//密码修改au,

					rm_makesonau//子账号建立au,
					 );
					finish();
			}
			else {
					finish();
				}
			}
		});
		try{



		rm_ordermau=aus.get(1);//订单管理au=aus.get(0);

		rm_neworder1au=aus.get(2);//商品分期下单au=aus.get(0);

		rm_neworder2au=aus.get(3);//教育分期下单au=aus.get(0);

		rm_neworder3au=aus.get(4);//医美分期下单au=aus.get(0);

		rm_checkorderau=aus.get(5);//查看订单au=aus.get(0);

		rm_checksonorderau=aus.get(6);//查看子账号订单au=aus.get(0);

		rm_allorderau=aus.get(7);//查看全部订单au=aus.get(0);


		rm_commau=aus.get(8);//商品管理au=aus.get(0);

		rm_add_commau=aus.get(9);//编辑商品au=aus.get(0);

		rm_edit_commau=aus.get(10);//添加商品au=aus.get(0);


		rm_moneyau=aus.get(11);//财务中心au=aus.get(0);

		rm_takemoneyau=aus.get(12);//提现au=aus.get(0);

		rm_checkselfau=aus.get(13);//查看销售额（自己）au=aus.get(0);

		rm_checksonsaleau=aus.get(14);//查看子账号销售额au=aus.get(0);

		rm_checkallsaleau=aus.get(15);//查看全部销售额au=aus.get(0);

		rm_checkjornau=aus.get(16);//查看财务报表au=aus.get(0);


		rm_messau=aus.get(17);//站内信au=aus.get(0);

		rm_checkmessau=aus.get(18);//站内信au=aus.get(0);

		rm_reportmessau=aus.get(19);//补交资料au=aus.get(0);



		rm_systemau=aus.get(20);//系统权限au=aus.get(0);

		rm_rbacau=aus.get(21);//权限管理au=aus.get(0);

		rm_changepassau=aus.get(22);//密码修改au=aus.get(0);

		rm_makesonau=aus.get(23);//子账号建立au=aus.get(0);
			name = aus.get(24);

		}catch (IndexOutOfBoundsException e){

		}


		//rm_name=(CheckBox) findViewById(R.id.rm_name);//角色名称=(EditText) findViewById(R.id.);
		//rm_orderm_madel=(CheckBox) findViewById(R.id.rm_orderm_madel);
		rm_orderm=(CheckBox) findViewById(R.id.rm_orderm);//订单管理=(CheckBox) findViewById(R.id.);

		if (rm_ordermau.equals("0")){
			rm_ordermau="0";

			rm_orderm.setChecked(false);
		}else {
			rm_orderm.setChecked(true);
		}
		rm_neworder1=(CheckBox) findViewById(R.id.rm_neworder1);//商品分期下单=(CheckBox) findViewById(R.id.);
		rm_neworder1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_neworder1au.equals("0")){
					rm_neworder1.setChecked(true);
					rm_neworder1au="1";
					update("rm_neworder1","1");
				}else {
					rm_neworder1.setChecked(false);
					rm_neworder1au="0";
					update("rm_neworder1","0");
					rm_ordermau="0";
					rm_orderm.setChecked(false);
				}
			}
		});
		if (rm_neworder1au.equals("0")){
			rm_neworder1.setChecked(false);
		}else {
			rm_neworder1.setChecked(true);
			rm_ordermau="0";
			rm_orderm.setChecked(false);
		}
		rm_neworder2=(CheckBox) findViewById(R.id.rm_neworder2);//教育分期下单=(CheckBox) findViewById(R.id.);

		rm_neworder2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_neworder2au.equals("0")){
					rm_neworder2.setChecked(true);
					rm_neworder2au="1";
					update("rm_neworder2","1");
				}else {
					rm_neworder2.setChecked(false);
					rm_neworder2au="0";
					rm_ordermau="0";
					rm_orderm.setChecked(false);
					update("rm_neworder2","0");
				}
			}
		});

		if (rm_neworder2au.equals("0")){
			rm_neworder2.setChecked(false);
		}else {
			rm_neworder2.setChecked(true);
			rm_ordermau="0";
			rm_orderm.setChecked(false);
		}
		rm_neworder3=(CheckBox) findViewById(R.id.rm_neworder3);//医美分期下单=(CheckBox) findViewById(R.id.);

		rm_neworder3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_neworder3au.equals("0")){
					rm_neworder3.setChecked(true);
					rm_neworder3au="1";
					update("rm_neworder3","1");
				}else {
					rm_neworder3.setChecked(false);
					rm_neworder3au="0";
					update("rm_neworder3","0");
					rm_ordermau="0";
					rm_orderm.setChecked(false);
				}
			}
		});
		if (rm_neworder3au.equals("0")){
			rm_neworder3.setChecked(false);
		}else {
			rm_neworder3.setChecked(true);
			rm_ordermau="0";
			rm_orderm.setChecked(false);
		}
		rm_checkorder=(CheckBox) findViewById(R.id.rm_checkorder);//查看订单=(CheckBox) findViewById(R.id.);
		rm_checkorder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "onClick:这丫状态是 "+rm_checkorderau);
				if (rm_checkorderau.equals("0")){
					rm_checkorder.setChecked(true);
					rm_checkorderau="1";
					update("rm_checkorder","1");
				}else {
					rm_checkorder.setChecked(false);
					rm_checkorderau="0";
					update("rm_checkorder","0");
					rm_ordermau="0";
					rm_orderm.setChecked(false);
				}
				Log.d(TAG, "onClick:这丫状态是 "+rm_checkorderau);
			}
		});

		rm_checksonorder=(CheckBox) findViewById(R.id.rm_checksonorder);//查看子账号订单=(CheckBox) findViewById(R.id.);
		rm_checksonorder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_checksonorderau.equals("0")){
					rm_checksonorder.setChecked(true);
					rm_checksonorderau="1";
					update("rm_checksonorder","1");
				}else {
					rm_checksonorder.setChecked(false);
					rm_checksonorderau="0";
					update("rm_checksonorder","0");
					rm_ordermau="0";
					rm_orderm.setChecked(false);
				}
			}
		});
		if (rm_checksonorderau.equals("0")){
			rm_checksonorder.setChecked(false);
		}else {
			rm_checksonorder.setChecked(true);
		}
		rm_allorder=(CheckBox) findViewById(R.id.rm_allorder);//查看全部订单=(CheckBox) findViewById(R.id.);
		rm_allorder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_allorderau.equals("0")){


					rm_allorder.setChecked(true);
					rm_allorderau="1";
					update("rm_allorder","1");
				}else {
					rm_allorder.setChecked(false);
					rm_allorderau="0";
					update("rm_allorder","0");
					rm_ordermau="0";
					rm_orderm.setChecked(false);
				}
			}
		});
		if (rm_allorderau.equals("0")){
			rm_allorder.setChecked(false);
		}else {
			rm_allorder.setChecked(true);
		}
		//rm_comm_madel=(CheckBox) findViewById(R.id.rm_comm_madel);
		rm_comm=(CheckBox) findViewById(R.id.rm_comm);//商品管理=(CheckBox) findViewById(R.id.);
		rm_comm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_commau.equals("0")){
					rm_comm.setChecked(true);
					rm_commau="1";

					update("rm_comm","1");
					rm_add_comm.setChecked(true);
					rm_add_commau="1";
					update("rm_add_comm","1");

					rm_edit_comm.setChecked(true);
					rm_edit_commau="1";
					update("rm_edit_comm","1");

				}else {
					rm_comm.setChecked(false);
					rm_commau="0";
					update("rm_comm","0");

					rm_add_comm.setChecked(false);
					rm_add_commau="0";
					update("rm_add_comm","0");

					rm_edit_comm.setChecked(false);
					rm_edit_commau="0";
					update("rm_edit_comm","0");
				}
			}
		});

		rm_add_comm=(CheckBox) findViewById(R.id.rm_add_comm);//编辑商品=(CheckBox) findViewById(R.id.);
		rm_add_comm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_add_commau.equals("0")){
					rm_add_comm.setChecked(true);
					rm_add_commau="1";
					update("rm_add_comm","1");

				}else {
					rm_add_comm.setChecked(false);
					rm_add_commau="0";
					update("rm_add_comm","0");

					rm_comm.setChecked(false);
					rm_commau="0";

				}
			}
		});
		if (rm_add_commau.equals("0")){
			rm_add_comm.setChecked(false);
		}else {
			rm_add_comm.setChecked(true);
		}
		rm_edit_comm=(CheckBox) findViewById(R.id.rm_edit_comm);//添加商品=(CheckBox) findViewById(R.id.);
		rm_edit_comm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_edit_commau.equals("0")){
					rm_edit_comm.setChecked(true);
					rm_edit_commau="1";
					update("rm_edit_comm","1");
				}else {
					rm_edit_comm.setChecked(false);
					rm_edit_commau="0";
					update("rm_edit_comm","0");

					rm_comm.setChecked(false);
					rm_commau="0";

				}
			}
		});
		if (rm_edit_commau.equals("0")){
			rm_edit_comm.setChecked(false);
		}else {
			rm_edit_comm.setChecked(true);
		}
		//rm_money_madel=(CheckBox) findViewById();
		rm_money=(CheckBox) findViewById(R.id.rm_money);//财务中心=(CheckBox) findViewById(R.id.);
		rm_money.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_moneyau.equals("0")){
					rm_money.setChecked(true);
					rm_moneyau="1";
					update("rm_money","1");

					rm_takemoney.setChecked(true);
					rm_takemoneyau="1";
					update("rm_takemoney","1");

					rm_checkself.setChecked(true);
					rm_checkselfau="1";
					update("rm_checkself","1");


					rm_checksonsale.setChecked(true);
					rm_checksonsaleau="1";
					update("rm_checksonsale","1");

					rm_checkjorn.setChecked(true);
					rm_checkjornau="1";
					update("rm_checkjorn","1");

					rm_checkallsale.setChecked(true);
					rm_checkallsaleau="1";
					update("rm_checkallsale","1");

				}else {
					rm_money.setChecked(false);
					rm_moneyau="0";
					update("rm_money","0");

					rm_takemoney.setChecked(false);
					rm_takemoneyau="0";
					update("rm_takemoney","0");

					rm_checkself.setChecked(false);
					rm_checkselfau="0";
					update("rm_checkself","0");

					rm_checksonsale.setChecked(false);
					rm_checksonsaleau="0";
					update("rm_checksonsale","0");

					rm_checkjorn.setChecked(false);
					rm_checkjornau="0";
					update("rm_checkjorn","0");

					rm_checkallsale.setChecked(false);
					rm_checkallsaleau="0";
					update("rm_checkallsale","0");
				}
			}
		});

		rm_takemoney=(CheckBox) findViewById(R.id.rm_takemoney);//提现=(CheckBox) findViewById(R.id.);
		rm_takemoney.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_takemoneyau.equals("0")){
					rm_takemoney.setChecked(true);
					rm_takemoneyau="1";
					update("rm_takemoney","1");
				}else {
					rm_takemoney.setChecked(false);
					rm_money.setChecked(false);
					rm_moneyau="0";
					update("rm_money","0");
					rm_takemoneyau="0";
					update("rm_takemoney","0");
				}
			}
		});
		if (rm_takemoneyau.equals("0")){
			rm_takemoney.setChecked(false);
		}else {
			rm_takemoney.setChecked(true);
		}
		rm_checkself=(CheckBox) findViewById(R.id.rm_checkself);//查看销售额（自己）=(CheckBox) findViewById(R.id.);
		rm_checkself.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_checkselfau.equals("0")){
					rm_checkself.setChecked(true);
					rm_checkselfau="1";
					update("rm_checkself","1");
				}else {
					rm_checkself.setChecked(false);
					rm_checkselfau="0";
					rm_money.setChecked(false);
					rm_moneyau="0";
					update("rm_money","0");
					update("rm_checkself","0");
				}
			}
		});
		if (rm_checkselfau.equals("0")){
			rm_checkself.setChecked(false);
		}else {
			rm_checkself.setChecked(true);
		}
		rm_checksonsale=(CheckBox) findViewById(R.id.rm_checksonsale);//查看子账号销售额=(CheckBox) findViewById(R.id.);

		rm_checksonsale.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_checksonsaleau.equals("0")){
					rm_checksonsale.setChecked(true);
					rm_checksonsaleau="1";
					update("rm_checksonsale","1");
				}else {
					rm_checksonsale.setChecked(false);
					rm_checksonsaleau="0";

					rm_money.setChecked(false);
					rm_moneyau="0";
					update("rm_money","0");
					update("rm_checksonsale","0");
				}
			}
		});
		if (rm_checksonsaleau.equals("0")){
			rm_checksonsale.setChecked(false);
		}else {
			rm_checksonsale.setChecked(true);
		}
		rm_checkallsale=(CheckBox) findViewById(R.id.rm_checkallsale);//查看全部销售额=(CheckBox) findViewById(R.id.);

		rm_checkallsale.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_checkallsaleau.equals("0")){
					rm_checkallsale.setChecked(true);
					rm_checkallsaleau="1";
					update("rm_checkallsale","1");
				}else {
					rm_checkallsale.setChecked(false);
					rm_checkallsaleau="0";
					update("rm_checkallsale","0");
					rm_money.setChecked(false);
					rm_moneyau="0";
					update("rm_money","0");
				}
			}
		});
		if (rm_checkallsaleau.equals("0")){
			rm_checkallsale.setChecked(false);
		}else {
			rm_checkallsale.setChecked(true);
		}
		rm_checkjorn=(CheckBox) findViewById(R.id.rm_checkjorn);//查看财务报表=(CheckBox) findViewById(R.id.);
		rm_checkjorn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_checkjornau.equals("0")){
					rm_checkjorn.setChecked(true);
					rm_checkjornau="1";
					update("rm_checkjorn","1");
				}else {
					rm_checkjorn.setChecked(false);
					rm_checkjornau="0";
					rm_money.setChecked(false);
					rm_moneyau="0";
					update("rm_money","0");
					update("rm_checkjorn","0");
				}
			}
		});
		if (rm_checkjornau.equals("0")){
			rm_checkjorn.setChecked(false);
		}else {
			rm_checkjorn.setChecked(true);
		}
		//rm_mess_madel=(CheckBox) findViewById(R.id.rm_mess_madel);
		rm_mess=(CheckBox) findViewById(R.id.rm_mess);//站内信=(CheckBox) findViewById(R.id.);
		rm_mess.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_messau.equals("0")){
					rm_mess.setChecked(true);
					rm_messau="1";
					update("rm_mess","1");

					rm_checkmess.setChecked(true);
					rm_checkmessau="1";
					update("rm_checkmess","1");

					rm_reportmess.setChecked(true);
					rm_reportmessau="1";
					update("rm_reportmess","1");

				}else {
					rm_mess.setChecked(false);
					rm_messau="0";
					update("rm_mess","0");

					rm_checkmess.setChecked(false);
					rm_checkmessau="0";
					update("rm_checkmess","0");

					rm_reportmess.setChecked(false);
					rm_reportmessau="0";
					update("rm_reportmess","0");
				}
			}
		});
		if (rm_messau.equals("0")){
			rm_mess.setChecked(false);
		}else {
			rm_mess.setChecked(true);
		}
		rm_checkmess=(CheckBox) findViewById(R.id.rm_checkmess);//站内信=(CheckBox) findViewById(R.id.);
		rm_mess.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_checkmessau.equals("0")){
					rm_checkmess.setChecked(true);
					rm_checkmessau="1";
					update("rm_checkmess","1");
				}else {
					rm_checkmess.setChecked(false);
					rm_checkmessau="0";
					update("rm_checkmess","0");

					rm_mess.setChecked(false);
					rm_messau="0";
					update("rm_mess","0");
				}
			}
		});
		if (rm_checkmessau.equals("0")){
			rm_checkmess.setChecked(false);
		}else {
			rm_checkmess.setChecked(true);
		}
		rm_reportmess=(CheckBox) findViewById(R.id.rm_reportmess);//补交资料=(CheckBox) findViewById(R.id.);
		rm_reportmess.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_reportmessau.equals("0")){
					rm_reportmess.setChecked(true);
					rm_reportmessau="1";
					update("rm_reportmess","1");
				}else {
					rm_reportmess.setChecked(false);
					rm_reportmessau="0";
					update("rm_reportmess","0");

					rm_mess.setChecked(false);
					rm_messau="0";
					update("rm_mess","0");
				}
			}
		});
		if (rm_reportmessau.equals("0")){
			rm_reportmess.setChecked(false);
		}else {
			rm_reportmess.setChecked(true);
		}
		//rm_system_madel=(CheckBox) findViewById(R.id.rm_system_madel);
		rm_system=(CheckBox) findViewById(R.id.rm_system);//系统权限=(CheckBox) findViewById(R.id.);
		rm_system.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_systemau.equals("0")){
					rm_system.setChecked(true);
					rm_systemau="1";
					update("rm_system","1");

					rm_rbac.setChecked(true);
					rm_rbacau="1";
					update("rm_rabc","1");

					rm_changepass.setChecked(true);
					rm_changepassau="1";


					rm_makeson.setChecked(true);
					rm_makesonau="1";
					update("rm_makeson","1");


				}else {
					rm_system.setChecked(false);
					rm_systemau="0";
					update("rm_system","0");

					rm_rbac.setChecked(false);
					rm_rbacau="0";
					update("rm_rabc","0");

					rm_changepass.setChecked(false);
					rm_changepassau="0";


					rm_makeson.setChecked(false);
					rm_makesonau="0";
					update("rm_makeson","0");
				}
			}
		});
		if (rm_systemau.equals("0")){
			rm_system.setChecked(false);
		}else {
			rm_system.setChecked(true);
		}
		rm_rbac=(CheckBox) findViewById(R.id.rm_rbac);//权限管理=(CheckBox) findViewById(R.id.);
		rm_rbac.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_rbacau.equals("0")){
					rm_rbac.setChecked(true);
					rm_rbacau="1";
					update("rm_rbac","1");
				}else {
					rm_rbac.setChecked(false);
					rm_rbacau="0";
					update("rm_rbac","0");
				}
			}
		});
		rm_changepass=(CheckBox) findViewById(R.id.rm_changepass);//密码修改=(CheckBox) findViewById(R.id.);
		rm_changepass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_changepassau.equals("0")){
					rm_changepass.setChecked(true);
					rm_changepassau="1";
					update("rm_changepass","1");
				}else {
					rm_changepass.setChecked(false);
					rm_changepassau="0";
					rm_rbac.setChecked(false);
					rm_rbacau="0";


				}
			}
		});

		rm_makeson=(CheckBox) findViewById(R.id.rm_makeson);//子账号建立=(CheckBox) findViewById(R.id.);
		rm_makeson.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rm_makesonau.equals("0")){
					rm_makeson.setChecked(true);
					rm_makesonau="1";
					update("rm_makeson","1");
				}else {
					rm_makeson.setChecked(false);
					rm_makesonau="0";
					rm_rbac.setChecked(false);
					rm_rbacau="0";
					update("rm_makeson","0");
				}
			}
		});
		if (rm_systemau.equals("0")){
			rm_system.setChecked(false);
		}else {
			rm_system.setChecked(true);
		}
		if (rm_rbacau.equals("0")){
			rm_rbac.setChecked(false);
		}else {
			rm_rbac.setChecked(true);
		}
		if (rm_changepassau.equals("0")){
			rm_changepass.setChecked(false);
		}else {
			rm_changepass.setChecked(true);
		}
		if (rm_makesonau.equals("0")){
			rm_makeson.setChecked(false);
		}else {
			rm_makeson.setChecked(true);
		}

	}



	public void  update(String where,String stage){
		dbHelper.update(dbHelper,where,stage,id,update);
		Log.d(TAG, "update: 运行了");

	}

}
