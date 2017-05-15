package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.cxhll.huifenq.Adapters.ExpressAdapter;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.ExpressItem;

import java.util.ArrayList;
import java.util.List;

public class ExpressActivity extends BaseActivity {
ListView express_list;
ArrayList<ExpressItem> lists;
String TAG="Hometest.class";
    ExpressAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        setBar("快递助手",6);
    intentY=new Intent(ExpressActivity.this,MyAddressActivity.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setLogic();
    }
    public void setLogic(){
        RelativeLayout nothing1= (RelativeLayout)findViewById(R.id.express_nothing);
        RelativeLayout send_express= (RelativeLayout) findViewById(R.id.send_express);
        send_express.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExpressActivity.this,ExpressListActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout select_express= (RelativeLayout) findViewById(R.id.select_express);
        select_express.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExpressActivity.this,SelectExpressActivity.class);
                startActivity(intent);
            }
        });
        lists=new ArrayList<ExpressItem>();
        ArrayList<ExpressItem> list2=new ArrayList<ExpressItem>();
        //list2=dbHelper.select_passorder(dbHelper,"5",1);
        lists=dbHelper.select_expressList(dbHelper,"1");
     // lists.addAll(list2);
        adp=new ExpressAdapter(this,R.layout.expressitem,lists);
        express_list= (ListView) findViewById(R.id.express_list);
        express_list.setEmptyView(nothing1);
        express_list.setAdapter(adp);
        express_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ExpressActivity.this,TraceActivity.class);
                ExpressItem item=lists.get(position);
                String num=item.getEx_num();
                String state=item.getState();
                Log.d(TAG, "onItemClick: "+state);
                Log.d(TAG, "handleMessage: 这个家伙的状态是"+state);
                if (state=="3"){
                    dbHelper.upDate_express(dbHelper,"3",num);
                }else if(state.equals("2")){
                    dbHelper.upDate_express(dbHelper,"2",num);
                }
                intent.putExtra("num",num);
                //intent.putExtra("code",)
                startActivity(intent);


            }
        });
    }
}
