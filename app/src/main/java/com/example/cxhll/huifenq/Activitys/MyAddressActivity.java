package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.cxhll.huifenq.Adapters.AddressAdapter;
import com.example.cxhll.huifenq.Adapters.AddressAdapter1;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.AddressItem;

import java.util.ArrayList;

public class MyAddressActivity extends BaseActivity {
    public ArrayList<AddressItem> listl;
    public AddressAdapter1 adp;
    public ListView addrelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        setBar("我的地址", 4);
        Log.d(TAG, "onCreate: 设置结束");
        intentX = new Intent(MyAddressActivity.this, NewAddressActivity.class);
        intentX.putExtra("update", "2");

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: 进入onstart");
      setdate();
        super.onStart();
    }

    //   setLogic();
public void setdate(){
    try {
        Log.d(TAG, "setLogic: 进入了setlogdc");
        addrelist = (ListView) findViewById(R.id.address_list);
        listl = new ArrayList<AddressItem>();
        listl = dbHelper.select_address(dbHelper);

        adp = new AddressAdapter1(MyAddressActivity.this, R.layout.addressitem, listl, dbHelper);
        addrelist.setAdapter(adp);

    } catch (NullPointerException e) {
    }
}

}
