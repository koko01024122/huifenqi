package com.example.cxhll.huifenq;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Activitys.BaseActivity;
import com.example.cxhll.huifenq.Activitys.ForgetpsdActivity;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;

public class Login extends BaseActivity {
    private TextInputLayout username, psd;
    private EditText usernamei, psdi;
    private Button login_butt;
    private TextView fightPSD;
    private String TAG = "Login.class";
    private TextView contents_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        login_butt = (Button) findViewById(R.id.login_butt);
        username = (TextInputLayout) findViewById(R.id.username_hint);
        psd = (TextInputLayout) findViewById(R.id.password_hint);
        username.setHint("用户名");
        psd.setHint("密码");
        usernamei = username.getEditText();
        psdi = psd.getEditText();
        username.setErrorEnabled(true);
        setDbHelper(this);
        fightPSD = (TextView) findViewById(R.id.foght_psd);
        contents_us = (TextView) findViewById(R.id.contents_us);
        contents_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        fightPSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgetpsdActivity.class);
                startActivity(intent);
            }
        });
        usernamei.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                username.setError("");

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        psdi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (usernamei.getText().toString().equals("")) {
                    username.setError("请输入您的账号");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        login_butt.setOnClickListener(new View.OnClickListener() {
            boolean state = false;

            @Override
            public void onClick(View view) {
                if (!(usernamei.getText().equals("") && psdi.getText().equals(""))) {
                    state = dbHelper.login(dbHelper, usernamei.getText().toString(), psdi.getText().toString());
                    if (state) {
                        Intent intent = new Intent(Login.this, Hometest.class);
                        String name = dbHelper.starUser(dbHelper);
                        intent.putExtra("name", name);
                        startActivity(intent);
                        dbHelper.loginStage(dbHelper);
                        dbHelper.homeStage(dbHelper);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d(TAG, "onClick: 出錯啦");
                }


            }
        });
    }

    public void openDialog() {
        LayoutInflater inflater = getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = inflater.inflate(R.layout.activity_contact_us, (ViewGroup) findViewById(R.id.contact_us));
        ImageView phonenum = (ImageView) view.findViewById(R.id.content_phone);
        phonenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri url = Uri.parse("tel:" + "027123443");
                intent.setData(url);
                startActivity(intent);
            }
        });
       ImageView email = (ImageView) view.findViewById(R.id.content_emil);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                Uri url = Uri.parse("mailto:" + "pll@yqjr.com.cn");
                intent.setData(url);
                startActivity(intent);
            }
        });


        builder.setView(view);
        builder.show();
    }
}
