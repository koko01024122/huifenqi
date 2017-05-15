package com.example.cxhll.huifenq.Activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cxhll.huifenq.ChangeAddressPopwindow;
import com.example.cxhll.huifenq.R;

public class NewAddressActivity extends BaseActivity {
    private LinearLayout defaults;
    private EditText detil;
    private TextView address;
    private EditText name;
    private EditText tel;
    private CheckBox checkBox;
    private String address_province;
    private String address_city;
    private String isdefault="0";
    private String address_area;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);

        Intent intent = getIntent();

        try {
            if (intent.getStringExtra("update").equals("1")) {
                setBar("编辑地址", 5);
            } else {
                setBar("新增地址", 5);
            }
        } catch (NullPointerException e) {

        }

    }

    @Override
    protected void onStart() {
        //  setLogic();
        finish = (Button) findViewById(R.id.new_address_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicks();
            }
        });
        defaults = (LinearLayout) findViewById(R.id.new_address_set_default);

        checkBox = (CheckBox) findViewById(R.id.new_checkbox);
        defaults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    isdefault = "0";
                    checkBox.setChecked(false);
                } else {
                    isdefault = "1";
                    checkBox.setChecked(true);
                }
            }
        });
        name = (EditText) findViewById(R.id.new_address_name);
        tel = (EditText) findViewById(R.id.new_address_tel);
        address = (TextView) findViewById(R.id.address_pca);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(NewAddressActivity.this);
                mChangeAddressPopwindow.setAddress("湖北", "武汉", "武昌区");
                mChangeAddressPopwindow.showAtLocation(address, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow
                        .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

                            @Override
                            public void onClick(String province, String city, String area) {
                                // TODO Auto-generated method stub
                                Log.d(TAG, "onClick: 开始选择");
                                address_province = province;
                                address_city = city;
                                address_area = area;
                                if (province.equals("天津") || province.equals("北京") || province.equals("上海") || province.equals("重庆")) {
                                    address_city = city;
                                    address_province = province + "市";
                                    address_city = province + "市";
                                } else {
                                    address_province = province + "省";
                                    address_city = city + "市";
                                    address_area = area;
                                }


                                address.setText(address_province + address_city + address_area);

                            }
                        });
            }
        });
        detil = (EditText) findViewById(R.id.new_address_address);


        super.onStart();
    }

    public void setLogic() {
        Log.d(TAG, "setLogic: 设置逻辑");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                if (!name.getText().toString().equals("") || !detil.getText().toString().equals("") || !tel.getText().toString().equals("") || !address.getText().toString().equals("")) {
                    AlertDialog.Builder build = new AlertDialog.Builder(this);
                    View view = LayoutInflater.from(this).inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dialog));
                    TextView content = (TextView) view.findViewById(R.id.dialog_text);
                    content.setText("您的信息还未保存，确认现在返回吗？");
                    build.setView(view);
                    build.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    build.setNegativeButton("取消", null);
                    build.show();
                }else {
                    finish();
                }
            } catch (NullPointerException e) {
            }
        }

        return false;
    }

    public void isNull() {
        if (name.getText().toString().equals("")) {
            name.setError("收/发货人姓名不能为空");
        } else if (detil.getText().toString().equals("")) {
            detil.setError("详细地址不能为空");
        } else if (tel.getText().toString().equals("")) {
            tel.setError("电话不能为空");
        } else if (address.getText().toString().equals("")) {
            Snackbar.make(checkBox, "未选择所在地区", Snackbar.LENGTH_SHORT).show();
        }
    }

    public void onClicks() {
        isNull();
        if (!name.getText().toString().equals("") && !detil.getText().toString().equals("") && !tel.getText().toString().equals("") && !address.getText().toString().equals("")) {
            dbHelper.insert_address(dbHelper, name.getText().toString(), tel.getText().toString(), address_province, address_city, address_area, isdefault,detil.getText().toString());
            finish();
        }
    }

}
