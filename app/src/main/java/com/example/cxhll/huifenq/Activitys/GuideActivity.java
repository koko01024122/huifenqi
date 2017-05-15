package com.example.cxhll.huifenq.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.cxhll.huifenq.Login;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.tools.NoticeFragment;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ViewPager viewPager= (ViewPager) findViewById(R.id.welcome_home);
        final View welcomeone= LayoutInflater.from(this).inflate(R.layout.welcomeone,null);
        final View welcometwo= LayoutInflater.from(this).inflate(R.layout.welcometwo,null);
        final View welcomethree= LayoutInflater.from(this).inflate(R.layout.welcomethree,null);
        final Button startlogin= (Button) welcomethree.findViewById(R.id.star_login);
        startlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this, Login.class);
                startActivity(intent);
            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {


            @Override
            public Fragment getItem(int position) {
                Fragment mFragment=new Fragment();
                if (position==0){
                    return NoticeFragment.newInstance(welcomeone);
                }else if(position==1){
                    return NoticeFragment.newInstance(welcometwo);
                }else{
                    return NoticeFragment.newInstance(welcomethree);
                }

            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }
}
