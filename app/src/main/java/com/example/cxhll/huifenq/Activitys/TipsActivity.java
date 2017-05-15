package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cxhll.huifenq.Adapters.TipsAdapter;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.TipsItem;

import java.util.ArrayList;

public class TipsActivity extends BaseActivity {
TipsAdapter adp;
    ArrayList<TipsItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        setBar("商户须知",2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setLogic();
    }
    private  void setLogic(){
        ListView tipslist= (ListView) findViewById(R.id.tips_list);
        list=new ArrayList<TipsItem>();
        list=dbHelper.select_tips(dbHelper);
        adp=new TipsAdapter(TipsActivity.this,R.layout.rm_items,list);
        tipslist.setAdapter(adp);
        tipslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipsItem item=list.get(position);
                Intent intent=new Intent(TipsActivity.this,WebViewActivity.class);
                intent.putExtra("title",item.getTitle());
                intent.putExtra("url",item.getUrl());
                startActivity(intent);
            }
        });
    }
}
