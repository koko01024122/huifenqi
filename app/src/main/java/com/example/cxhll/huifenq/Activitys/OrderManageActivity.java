package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.cxhll.huifenq.Adapters.Adapt;
import com.example.cxhll.huifenq.Adapters.orderAdapt;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.Message;
import com.example.cxhll.huifenq.item.OrderListItem;
import com.example.cxhll.huifenq.tools.NoticeFragment;

import java.util.ArrayList;

public class OrderManageActivity extends BaseActivity {
private View auditing;
    private View notpass;
    private View pass;
    private View guo;
    private orderAdapt oAdp;
    private orderAdapt oAdp1;
    private orderAdapt oAdp2;
    private orderAdapt oAdp3;
    ArrayList<OrderListItem> List1=new ArrayList<>();
    ArrayList<OrderListItem> List2=new ArrayList<>();
    ArrayList<OrderListItem> List3=new ArrayList<>();
    ArrayList<OrderListItem> List4=new ArrayList<>();
    String TAG="Hometest.class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manage);
        	setBar("订单管理",0);
        Log.d(TAG, "onCreate:进入订单管理页面1");
        intentX=new Intent(this,NewOrderAty.class);
        Log.d(TAG, "onCreate: 创建intent1");
       intentY=new Intent(this,SeachActivity.class);
        intentY.putExtra("info","1");
        Log.d(TAG, "onCreate: 创建intent2");

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onCreate: 进入onstar");
        super.onStart();
        ViewPager viewPager= (ViewPager) findViewById(R.id.order_viewpager);
        Log.d(TAG, "onCreate: 创建viewpager");
        //创建一个viewPager
        Log.d(TAG, "onCreate:进入订单管理页面2");
        final  String[] Titles={"审核中","未通过","待发货","已发货"};
        //审核中
        auditing= LayoutInflater.from(this).inflate(R.layout.auditing,null);

        ListView auditingList= (ListView) auditing.findViewById(R.id.auditing_list);



        Log.d(TAG, "onCreate:进入订单管理页面3");
        try{
        List1=dbHelper.select_ordersstage(dbHelper,"1",1);

        RelativeLayout nothing1= (RelativeLayout) auditing.findViewById(R.id.nothing_part1);

        auditingList.setEmptyView(nothing1);

        Log.d(TAG, "onStart: 查询订单");
        oAdp = new orderAdapt(this, R.layout.orderlist, List1);
        auditingList.setAdapter(oAdp);
        Log.d(TAG, "onCreate:进入订单管理页面4");
        }catch (NullPointerException e){}
        auditingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderListItem item=List1.get(position);
                Log.d(TAG, "onItemClick:id的值为！！！！ "+item.getId());

                Intent intent=new Intent(OrderManageActivity.this,OrderinfoAty.class);

                /**
                 * 	String name=intent.getStringExtra("name");
                 String stage=intent.getStringExtra("stage");
                 String address=intent.getStringExtra("address");
                 String addressphonenum=intent.getStringExtra("address_phonenum");
                 String trade_names=intent.getStringExtra("trade_name");
                 realup= (CardView) findViewById(R.id.real_up);
                 String mopay=intent.getStringExtra("mopay");
                 String price=intent.getStringExtra("price");
                 String content=intent.getStringExtra("content");

                 */
                String ids= String.valueOf(item.getId());
                intent.putExtra("id",ids);
                intent.putExtra("name",item.getName());
                intent.putExtra("stage",item.getStage());
                intent.putExtra("address",item.getAddress_province()+item.getAddress_city()+item.getAddress_area()+item.getAddress_detil());
                intent.putExtra("address_phonenum",item.getAddress_phoennum());
                intent.putExtra("trade_names",item.getTrade_name());
                intent.putExtra("paystage",item.getPaystage());
                intent.putExtra("mopay",item.getMopay());
                intent.putExtra("price",item.getPrice());
                intent.putExtra("content",item.getContent());
                intent.putExtra("ordertime",item.getTime());
                intent.putExtra("address_name",item.getAddress_name());
                intent.putExtra("waretype",item.getWare_type());
                startActivity(intent);
            }
        });

        Log.d(TAG, "onCreate:进入订单管理页面5");
        //未通过
        notpass= LayoutInflater.from(this).inflate(R.layout.notpass,null);

        ListView notPassList= (ListView) notpass.findViewById(R.id.notpass_list);

        RelativeLayout nothing2= (RelativeLayout) notpass.findViewById(R.id.nothing_part2);

        notPassList.setEmptyView(nothing2);

        Log.d(TAG, "onCreate:进入订单管理页面6");
        List2=dbHelper.select_ordersstage(dbHelper,"0",1);
        oAdp1 = new orderAdapt(this, R.layout.orderlist, List2);
        notPassList.setAdapter(oAdp1);
       notPassList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderListItem item=List2.get(position);
                Intent intent=new Intent(OrderManageActivity.this,OrderinfoAty.class);
                String ids= String.valueOf(item.getId());
                intent.putExtra("id",ids);
                intent.putExtra("name",item.getName());
                intent.putExtra("stage",item.getStage());
                intent.putExtra("address",item.getAddress_province()+item.getAddress_city()+item.getAddress_area()+item.getAddress_detil());
                intent.putExtra("address_phonenum",item.getAddress_phoennum());
                intent.putExtra("trade_names",item.getTrade_name());
                intent.putExtra("paystage",item.getPaystage());
                intent.putExtra("mopay",item.getMopay());
                intent.putExtra("price",item.getPrice());
                intent.putExtra("content",item.getContent());
                intent.putExtra("ordertime",item.getTime());
                intent.putExtra("address_name",item.getAddress_name());
                intent.putExtra("waretype",item.getWare_type());
                startActivity(intent);
            }
        });
        Log.d(TAG, "onCreate:进入订单管理页面7");

        //已通
        pass= LayoutInflater.from(this).inflate(R.layout.passorder,null);
        ListView passList= (ListView) pass.findViewById(R.id.pass_list);
        RelativeLayout nothing3= (RelativeLayout) pass.findViewById(R.id.nothing_part3);

        passList.setEmptyView(nothing3);
        List3=dbHelper.select_ordersstage(dbHelper,"6",1);
        oAdp2 = new orderAdapt(this, R.layout.orderlist, List3);
        passList.setAdapter(oAdp2);
        passList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderListItem item=List3.get(position);
                Intent intent=new Intent(OrderManageActivity.this,OrderinfoAty.class);
                String ids= String.valueOf(item.getId());
                intent.putExtra("id",ids);
                Log.d(TAG, "onItemClick: "+id);
                intent.putExtra("id",ids);
                intent.putExtra("name",item.getName());
                intent.putExtra("stage",item.getStage());
                intent.putExtra("address",item.getAddress_province()+item.getAddress_city()+item.getAddress_area()+item.getAddress_detil());
                intent.putExtra("address_phonenum",item.getAddress_phoennum());
                intent.putExtra("trade_names",item.getTrade_name());
                intent.putExtra("paystage",item.getPaystage());
                intent.putExtra("mopay",item.getMopay());
                intent.putExtra("price",item.getPrice());
                intent.putExtra("content",item.getContent());
                intent.putExtra("ordertime",item.getTime());
                intent.putExtra("address_name",item.getAddress_name());
                intent.putExtra("waretype",item.getWare_type());
                startActivity(intent);
            }
        });

        Log.d(TAG, "onCreate:进入订单管理页面8");

        //待发货
        guo= LayoutInflater.from(this).inflate(R.layout.guo,null);

        ListView guoList= (ListView) guo.findViewById(R.id.guo_list);



        RelativeLayout nothing4= (RelativeLayout) guo.findViewById(R.id.nothing_part4);

        guoList.setEmptyView(nothing4);
        List4=dbHelper.select_ordersstage(dbHelper,"5",1);

        oAdp3 = new orderAdapt(this, R.layout.orderlist, List4);

        guoList.setAdapter(oAdp3);
       guoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderListItem item=List4.get(position);
                Intent intent=new Intent(OrderManageActivity.this,OrderinfoAty.class);
                String ids= String.valueOf(item.getId());
                intent.putExtra("id",ids);
                intent.putExtra("name",item.getName());
                intent.putExtra("stage",item.getStage());
                intent.putExtra("address",item.getAddress_province()+item.getAddress_city()+item.getAddress_area()+item.getAddress_detil());
                intent.putExtra("address_phonenum",item.getAddress_phoennum());
                intent.putExtra("trade_names",item.getTrade_name());
                intent.putExtra("paystage",item.getPaystage());
                intent.putExtra("mopay",item.getMopay());
                intent.putExtra("price",item.getPrice());
                intent.putExtra("content",item.getContent());
                intent.putExtra("ordertime",item.getTime());
                intent.putExtra("address_name",item.getAddress_name());
                intent.putExtra("waretype",item.getWare_type());
                startActivity(intent);
            }
        });
        Log.d(TAG, "onCreate:进入订单管理页面9");
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return Titles[position];
            }

            @Override
            public Fragment getItem(int position) {
                Fragment mFragment1=new Fragment();
                if (position==0){
                    return NoticeFragment.newInstance(auditing);
                }else if(position==1){
                    return NoticeFragment.newInstance(notpass);
                }else if(position==2){
                    return NoticeFragment.newInstance(guo);
                }else {
                    return NoticeFragment.newInstance(pass);
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        PagerSlidingTabStrip strip= (PagerSlidingTabStrip) findViewById(R.id.order_tab);
        strip.setShouldExpand(true);


        strip.setViewPager(viewPager);
        strip.setDividerColor(Color.parseColor("#f44336"));

        strip.setUnderlineHeight(3);
        strip.setIndicatorHeight(6);


    }
}

