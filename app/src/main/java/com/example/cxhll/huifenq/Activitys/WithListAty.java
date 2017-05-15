package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cxhll.huifenq.Adapters.MoneysAdp;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.item.Moneys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WithListAty extends BaseActivity {
private ListView withlist;

    private  ArrayList<Moneys> moneylist;
    private MoneysAdp listadp;
    private String TAG="WithListAty.class";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDbHelper(this);
        setContentView(R.layout.activity_with_list_aty);

        moneylist=new ArrayList<Moneys>();
        withlist= (ListView) findViewById(R.id.with_lists);

        dbHelper=new HuifqDbHelper(WithListAty.this,"huifenqi.db",null,5);
        Intent intent=getIntent();
        String time =intent.getStringExtra("time");
        SimpleDateFormat date=new SimpleDateFormat("yyyyMM");
        Date times = null;

        try {
            times = date.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(times);
        int month=calendar.get(Calendar.MONTH)+1;
        if (month<10){
            time=(calendar.get(Calendar.YEAR)+"-0"+month+"");
        }else
        {
            time=(calendar.get(Calendar.YEAR)+"-"+month+"");
        }

        moneylist=dbHelper.select_withlist(dbHelper,time);
        Log.d(TAG, "onCreate: "+time);
        listadp=new MoneysAdp(WithListAty.this,R.layout.takemoneyitem,moneylist);
        withlist.setAdapter(listadp);
       setBar(calendar.get(Calendar.YEAR)+"年"+month+"月款项明细",2);

    }

}
