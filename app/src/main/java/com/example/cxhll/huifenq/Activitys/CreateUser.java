package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CreateUser extends BaseActivity {
    private TextInputLayout name;
    private TextInputLayout username;
    private TextInputLayout password;
    private Spinner role;
    private ArrayAdapter<String> adap;
    private ArrayList<String> list;
    private ArrayList<String> idlist;
    private HashMap<Object, Object> infolist;
    private Button create;
    String roles;
    String roleid;
    String rolename;
    String phoenum;

    ArrayList<String> infos;
    EditText names;
    EditText phonenum;
    EditText psd;
    Intent intent;
    HashMap<Object, Object> infohash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        setBar("创建新用户", 2);
        try {
            intent = getIntent();
            Log.d(TAG, "onCreate: "+intent.getStringExtra("update"));
            if (intent.getStringExtra("update").equals("1")) {
                setBar("编辑用户", 2);
            }
        } catch (NullPointerException e) {

        }


        create = (Button) findViewById(R.id.cu_finish);

        list = new ArrayList<String>();
        idlist = new ArrayList<String>();
        infolist = new HashMap<Object, Object>();
        infolist = dbHelper.select_rolename(dbHelper, CreateUser.this);
        list = (ArrayList<String>) infolist.get("role");
        idlist = (ArrayList<String>) infolist.get("id");
        adap = new ArrayAdapter<String>(CreateUser.this, android.R.layout.simple_spinner_item, list);
        adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        role = (Spinner) findViewById(R.id.cu_role);

        role.setAdapter(adap);
        name = (TextInputLayout) findViewById(R.id.cu_name);
        name.setHint("用户姓名");
        names = name.getEditText();
        username = (TextInputLayout) findViewById(R.id.cu_username);
        username.setHint("登录账号（手机号）");

        password = (TextInputLayout) findViewById(R.id.cu_password);

        password.setHint("登录密码");
        phonenum = username.getEditText();
        psd = password.getEditText();
        phonenum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (phonenum.getText().toString().length() != 11) {
                    phonenum.setError("请输入正确的手机号");

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (phonenum.getText().toString().length() != 11) {
                    phonenum.setError("请输入正确的手机号");

                }
            }
        });


        try {

            if (intent.getStringExtra("update").equals("1")) {
                update();
            }
        } catch (NullPointerException e) {
        }


        try {
            if (intent.getStringExtra("update").equals("0")) {
                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbHelper.create_user(dbHelper, phonenum.getText().toString(), psd.getText().toString(), names.getText().toString(), roles, roleid);
                        finish();
                    }
                });
            }
        } catch (NullPointerException e) {
        }
        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                roles = role.getSelectedItem().toString();
                roleid = String.valueOf(role.getSelectedItemId() + 1);
                rolename = String.valueOf(role.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Log.d("CreateUser.class", "onCreate: 角色名称为" + rolename);

    }


    public void update() {
        phoenum = intent.getStringExtra("phonenum");
        infohash = new HashMap<Object, Object>();
        infohash = dbHelper.select_userList(dbHelper, phoenum);
        ArrayList<String> infos = (ArrayList<String>) infohash.get("userinfos");
        try {


            names.setText(infos.get(2));
            phonenum.setText(infos.get(0));
            psd.setText(infos.get(1));
            role.setSelection(Integer.parseInt(infos.get(5)) - 1);
            create.setText("更新");
            create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.update_user(dbHelper, names.getText().toString(), phonenum.getText().toString(), psd.getText().toString(), intent.getStringExtra("id"), roles, roleid);
                    finish();

                }
            });
        } catch (NullPointerException e) {
        }


    }


}
