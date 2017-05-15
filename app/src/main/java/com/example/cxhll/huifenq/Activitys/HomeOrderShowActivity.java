package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.cxhll.huifenq.Adapters.orderAdapt;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.OrderListItem;

import java.util.ArrayList;

public class HomeOrderShowActivity extends BaseActivity {
private  Intent intent;
    private ArrayList<OrderListItem> list=new ArrayList<OrderListItem>();
   private ListView home_list;
    private orderAdapt adapt;
private RelativeLayout mEmepty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_order_show);
        intent=getIntent();
        home_list= (ListView) findViewById(R.id.ahos_list);
        mEmepty= (RelativeLayout) findViewById(R.id.ahos_nothing);
        home_list.setEmptyView(mEmepty);
    }




    @Override
    protected void onStart() {
        super.onStart();
        setLLogic();
    }

    public void setLLogic(){

        String state=intent.getStringExtra("state");
        Log.d(TAG, "setLLogic: state的值为"+state);
        if (state.equals("9")){
            list=dbHelper.select_ordersstage(dbHelper,"9",1);
            setList();
            setBar("待签约",2);
            home_list.setAdapter(adapt);
        }else if(state.equals("0")){
            list=dbHelper.select_ordersstage(dbHelper,"0",1);
            setList();
            setBar("被拒单",2);
            home_list.setAdapter(adapt);
        }else if(state.equals("5")){
            list=dbHelper.select_ordersstage(dbHelper,"5",1);
            setList();
            home_list.setAdapter(adapt);
            setBar("待发货",2);

        }else if(state.equals("1")){

            list=dbHelper.select_ordersstage(dbHelper,"1",1);
           setList();
            home_list.setAdapter(adapt);
            setBar("审核中",2);
          //  home_list.setAdapter(adapt);
        }
    }

    public void setList(){
adapt=new orderAdapt(this,R.layout.orderlist,list);

}
}
