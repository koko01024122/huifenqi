package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.cxhll.huifenq.Adapters.ChoseOverdueAdapter;
import com.example.cxhll.huifenq.Adapters.ShowOverdueAdapter;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.ChoseOverdueIem;
import com.example.cxhll.huifenq.item.ShowOverdueIem;

import java.util.ArrayList;

public class OverdueCustomesListActivity extends BaseActivity {

    private ArrayList<ChoseOverdueIem> choseOverdueIemArrayList = new ArrayList<>();
    private ArrayList<ShowOverdueIem> showOverdueIemArrayList = new ArrayList<>();
    private ChoseOverdueAdapter choseOverdueAdapter;
    private ShowOverdueAdapter showOverdueAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdue_customes_list);
        Log.d(TAG, "onCreate: 启动activity");

        listView = (ListView) findViewById(R.id.customes_list);
        setBar("逾期列表", 7);
        intentX=new Intent(OverdueCustomesListActivity.this,CollectionFollowActivity.class);
        Log.d(TAG, "onCreate: 启动activity设置bar");


        showOverdueIemArrayList = dbHelper.selectShowOverList(dbHelper);
        chose_listView = listView;
        showOverdueAdapter = new ShowOverdueAdapter(this, R.layout.check_ovverdue_custimes_item, showOverdueIemArrayList);
        showOverdueAdapter_base = showOverdueAdapter;
        listView.setAdapter(showOverdueAdapter);
        choseOverdueIemArrayList = dbHelper.selectChoseOverList(dbHelper);
        choseOverdueAdapter = new ChoseOverdueAdapter(this, R.layout.chose_ovverdue_custimes_item, choseOverdueIemArrayList);
        choseOverdueAdapter_base = choseOverdueAdapter;
        choseOverdueIemArrayList_base=choseOverdueIemArrayList;


    }
}
