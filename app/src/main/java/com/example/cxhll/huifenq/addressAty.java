package com.example.cxhll.huifenq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Activitys.BaseActivity;

public class addressAty extends BaseActivity {
    private TextView select_address_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        select_address_tv= (TextView) findViewById(R.id.select_address_tv);
        select_address_tv   .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(addressAty.this);
                mChangeAddressPopwindow.setAddress("广东", "深圳", "福田区");
                mChangeAddressPopwindow.showAtLocation(select_address_tv, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow
                        .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

                            @Override
                            public void onClick(String province, String city, String area) {
                                // TODO Auto-generated method stub
                                Toast.makeText(addressAty.this,
                                        province + "-" + city + "-" + area,
                                        Toast.LENGTH_LONG).show();
                                select_address_tv.setText(province + city + area);
                            }
                        });
            }
        });
    }
}
