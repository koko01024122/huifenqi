package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cxhll.huifenq.Adapters.ExpressAdapter;
import com.example.cxhll.huifenq.Adapters.ExpressCommAdapter;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.Express_CommItem;

import java.util.ArrayList;
import java.util.List;

public class ExpressListActivity extends BaseActivity {
ArrayList<Express_CommItem> list;
    ListView express_list;
    ExpressCommAdapter adp;
    private Intent intent1;
    TextView more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_express);
        setBar("寄快递",2);
        intent1=getIntent();
        Log.d(TAG, "onCreate: 进入");
more= (TextView) findViewById(R.id.more_express);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExpressListActivity.this,HomeOrderShowActivity.class);
                intent.putExtra("express","1");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        setLogic();
    }
    public void setLogic(){
        express_list= (ListView) findViewById(R.id.express_comm_lists);
        list=new ArrayList<Express_CommItem>();
        list=dbHelper.select_expresscomm(dbHelper);
        adp=new ExpressCommAdapter(ExpressListActivity.this,R.layout.express_commitem,list);
        express_list.setAdapter(adp);
String fromorder="0";
        Log.d(TAG, "setLogic: tyr前");
        try{
            fromorder=intent1.getStringExtra("fromorder");

        Log.d(TAG, "setLogic: try后");


            if (fromorder.equals("1")){
                Log.d(TAG, "setLogic:我在if中 ");
                express_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(ExpressListActivity.this,SendExpressActivity.class);
                        Express_CommItem item=list.get(position);
                        String shippername=item.getShipperName();
                        String shippercode=item.getShipperCode();
                        intent.putExtra("fromorder","1");
                        intent.putExtra("shippername",shippername);
                        intent.putExtra("shippercode",shippercode);
                        intent.putExtra("province",intent1.getStringExtra("province"));
                        intent.putExtra("city",intent1.getStringExtra("city"));
                        intent.putExtra("area",intent1.getStringExtra("area"));
                        intent.putExtra("name",intent1.getStringExtra("name"));
                        intent.putExtra("tel",intent1.getStringExtra("tel"));
                        intent.putExtra("trade_name",intent1.getStringExtra("trade_name"));
                        intent.putExtra("addressdetil",intent1.getStringExtra("addressdetil"));
                        startActivity(intent);
                    }
                });
            }

            else{
                express_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Log.d(TAG, "setLogic:进入了else ");
                        Intent intent=new Intent(ExpressListActivity.this,SendExpressActivity.class);
                        Express_CommItem item=list.get(position);
                        String shippername=item.getShipperName();
                        String shippercode=item.getShipperCode();
                        intent.putExtra("shippername",shippername);
                        intent.putExtra("shippercode",shippercode);
                        startActivity(intent1);
                    }
                });
            }
        }catch (NullPointerException e){
            fromorder="0";
            Log.d(TAG, "setLogic: cathc？");
            express_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.d(TAG, "setLogic:进入了else ");
                    Intent intent=new Intent(ExpressListActivity.this,SendExpressActivity.class);
                    Express_CommItem item=list.get(position);
                    String shippername=item.getShipperName();
                    String shippercode=item.getShipperCode();
                    intent.putExtra("shippername",shippername);
                    intent.putExtra("shippercode",shippercode);
                    startActivity(intent);
                }
            });
        }

    }
}
