package com.example.cxhll.huifenq.Activitys;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.cxhll.huifenq.Adapters.Adapt;
import com.example.cxhll.huifenq.item.Message;
import com.example.cxhll.huifenq.tools.NoticeFragment;
import com.example.cxhll.huifenq.R;

import java.util.ArrayList;

public class NoticeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        final NotificationManager manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
       setBar("消息中心",3);
         intentX=new Intent(NoticeActivity.this,ReaderdActivity.class);
        intentY=new Intent(this,SeachActivity.class);
        intentY.putExtra("info","3");
        setDbHelper(this);

    }



    @Override
    protected void onStart() {
        try{
        ViewPager viewPager= (ViewPager) findViewById(R.id.notice_viewpager);
        //创建一个viewPager
      String TAG="MesList.this";
        ArrayList<Message> list1;
        Adapt adapt;
        final  String[] Titles={"审核消息","系统通知"};
        ListView listView;
        final View view1= LayoutInflater.from(NoticeActivity.this).inflate(R.layout.meslistaty_layout,null);
       //找到要显示的view，并且进行设置


        listView= (ListView) view1.findViewById(R.id.list_aty);

        if (dbHelper.select_power(dbHelper,"rm_mess")==0){
            listView.setVisibility(View.GONE);
        }


        Log.d(TAG, "数据插入成功");
        //list=new ArrayList<HashMap<String, Object>>();
     list1=new ArrayList<Message>();
        list1=dbHelper.select_mess(dbHelper);
        adapt=new Adapt(NoticeActivity.this,R.layout.messitem_layout,list1);
        //listView.setAdapter(adapt);
        Log.d(TAG, "数据获取成功 ");
	/*	adapter=new SimpleAdapter(MesList.this,
				list,
				R.layout.messitem_layout,
				new String[]{"reson","name","state"},
				new int[]{R.id.tv_mess_reson,R.id.tv_mess_name,R.id.tv_mess_state});*/
        listView.setAdapter(adapt);

        if(dbHelper.select_power(dbHelper,"rm_reportmess")==1){

            final ArrayList<Message> finalList = list1;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Message message= finalList.get(i);
                    String id= String.valueOf(message.getId());
                    String oid=String.valueOf(message.getO_id());
                    String content=message.getReson();

                    Intent intent=new Intent(NoticeActivity.this,MessAty.class);
                    intent.putExtra("id",id);
                    intent.putExtra("o_id",oid);
                    intent.putExtra("reson",content);
                    intent.putExtra("stage",String.valueOf(message.getState()));

                    startActivity(intent);


                }
            });
        }else {

        }




        final View view= LayoutInflater.from(NoticeActivity.this).inflate(R.layout.notice,null);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            //为viewPager设置页面
            @Override
            public CharSequence getPageTitle(int position) {
                return Titles[position];
            }

            @Override
            public Fragment getItem(int position) {
                Fragment mFragment1=new Fragment();
                if (position==0){
                    return NoticeFragment.newInstance(view1);
                }else{
                    return NoticeFragment.newInstance(view);
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        PagerSlidingTabStrip strip= (PagerSlidingTabStrip) findViewById(R.id.notice_tab);
        strip.setShouldExpand(true);

        Log.d("NoticeActivity.class", "onCreate: "+strip.getShouldExpand());
        strip.setViewPager(viewPager);
        strip.setDividerColor(Color.parseColor("#f44336"));

        strip.setUnderlineHeight(3);
        strip.setIndicatorHeight(6);
        super.onStart();
        }catch (Exception e){}
    }


}
