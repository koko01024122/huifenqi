package com.example.cxhll.huifenq.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.cxhll.huifenq.R;

import java.util.ArrayList;

public class NewCollectionActivity extends BaseActivity {
private Spinner willtopay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_collection);
        setBar("XXX的还款情况",2);
        ArrayList<String> willlist=new ArrayList<>();
        willlist.add("请选择还款意愿");
        willlist.add("短期内还款");
        willlist.add("不愿意还款");
        willtopay= (Spinner) findViewById(R.id.will_topay);
        ArrayAdapter<String> willadapt=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,willlist);
        willadapt.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        willtopay.setAdapter(willadapt);
    }
}
