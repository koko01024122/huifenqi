package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.cxhll.huifenq.Adapters.AnnoAdapter;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.AnnoItem;

import java.util.ArrayList;

public class AnnouActivity extends BaseActivity {
private AnnoAdapter adapter;
    private ArrayList<AnnoItem> list;
    private ListView annolist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annou);
        setBar("团队公告",4);
        intentX=new Intent(AnnouActivity.this,CreateAnnoActivity.class);

    }

    @Override
    protected void onStart() {
        try{
            RelativeLayout nothing1= (RelativeLayout)findViewById(R.id.anno_nothing);
            annolist= (ListView) findViewById(R.id.anno_list);
            list=new ArrayList<AnnoItem>();
            annolist.setEmptyView(nothing1);
            list=dbHelper.select_Anno(dbHelper);
            adapter=new AnnoAdapter(this,R.layout.annoitem,list);
            annolist.setAdapter(adapter);
            annolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(AnnouActivity.this,AnnoShowActivity.class);
                    AnnoItem item=list.get(position);
                    intent.putExtra("title",item.getTitle());
                    intent.putExtra("sender",item.getSendu());
                    intent.putExtra("sendtime",item.getTime());
                    intent.putExtra("content",item.getContent());
                    startActivity(intent);
                }
            });
        }catch (Exception e){}
        super.onStart();
    }
}
