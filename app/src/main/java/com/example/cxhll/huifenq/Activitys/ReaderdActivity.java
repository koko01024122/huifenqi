package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cxhll.huifenq.Adapters.Adapt;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.item.Message;

import java.util.ArrayList;

public class ReaderdActivity extends BaseActivity {
private ListView readerlist;
    private ArrayList<Message> list1;
    private Adapt adapt;
    private HuifqDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readerd);
       setBar("历史信息",2);
        readerlist= (ListView) findViewById(R.id.readered_list);
        intentY=new Intent(ReaderdActivity.this,SeachActivity.class);
        intentY.putExtra("mess","1");

        list1=new ArrayList<Message>();
        try{
        list1=dbHelper.select_mess1(dbHelper);
        }catch (NullPointerException e){}
        adapt=new Adapt(ReaderdActivity.this,R.layout.messitem_layout,list1);
        readerlist.setAdapter(adapt);

        readerlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message message=list1.get(position);
                String ids= String.valueOf(message.getId());
                String oid=String.valueOf(message.getO_id());
                String content=message.getReson();
                Log.d(TAG, "onItemClick: "+message.getState());
                Intent intent=new Intent(ReaderdActivity.this,MessAty.class);
                intent.putExtra("id",ids);
                intent.putExtra("o_id",oid);
                intent.putExtra("reson",content);
                intent.putExtra("stage",String.valueOf(message.getState()));
                Log.d(TAG, "onItemClick: reson"+content);
                startActivity(intent);
            }
        });
    }

}
