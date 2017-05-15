package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cxhll.huifenq.Adapters.CollectListAdapter;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.CollectionItem;

import java.util.ArrayList;

public class CollectionFollowActivity extends BaseActivity {
private CollectListAdapter adp;
    private ArrayList<CollectionItem> list;
    private ListView collectionlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_follow);
        Log.d(TAG, "onCreate: 1");
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        setBar(name+"的还款意愿",4);
        collectionlist= (ListView) findViewById(R.id.collection_list);
        intentX =new Intent(CollectionFollowActivity.this,NewCollectionActivity.class);
        list=new ArrayList<>();
        Log.d(TAG, "onCreate: 2");
        for (int i=0;i<10;i++){
            CollectionItem item=new CollectionItem();
            item.setId(""+i);
            list.add(item);
        }
        adp=new CollectListAdapter(CollectionFollowActivity.this,R.layout.collection_item,list);
        collectionlist.setAdapter(adp);
        Log.d(TAG, "onCreate: 3");
    }
}
