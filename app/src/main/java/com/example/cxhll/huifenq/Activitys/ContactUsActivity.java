package com.example.cxhll.huifenq.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cxhll.huifenq.R;

public class ContactUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        setDbHelper(this);
        setBar("联系我们",2);

    }
}
