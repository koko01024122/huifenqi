package com.example.cxhll.huifenq.Activitys;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cxhll.huifenq.R;

public class ForgetpsdActivity extends BaseActivity {
    private TextInputLayout mPhonenum;
    private EditText mPhone;
    private TextInputLayout mNpsd_one;
    private EditText mPsdone;
    private TextInputLayout mPewpsd_two;
    private EditText mPsdtwo;
    private Button mIdencode;
    private Handler handler;
    private Button mFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpsd);
        setDbHelper(this);
        setBar("找回密码", 2);
        setViewDate();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void setViewDate() {
        mPhonenum = (TextInputLayout) findViewById(R.id.fg_phonenum);
        mPhone = mPhonenum.getEditText();
        mNpsd_one = (TextInputLayout) findViewById(R.id.fg_psd_one);
        mPsdone = mNpsd_one.getEditText();
        mPewpsd_two = (TextInputLayout) findViewById(R.id.fg_psd_two);
        mPsdtwo = mNpsd_one.getEditText();
        mIdencode = (Button) findViewById(R.id.get_Idencode);
        mFinish = (Button) findViewById(R.id.fg_psd_over);
        mIdencode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPhone.getText().toString().length() != 11) {
                    mPhone.setError("请输入手机号");
                } else {
                    mIdencode.setClickable(false);
                    mIdencode.setText("60秒后重新获取");
                    mIdencode.setBackgroundColor(Color.parseColor("#d8d8d8"));
                    pao();
                    handler = new Handler() {
                        public void handleMessage(Message msg) {

                            if (msg.what == 1) {

                                int i = (int) msg.obj;
                                mIdencode.setText("" + i + "秒后重新获取");
                                if (i == 0) {
                                    mIdencode.setText("点击获取验证码");
                                }
                                super.handleMessage(msg);
                            }


                        }

                    };
                }
            }
        });


    }

    public void pao() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int d = 3;
                    for (int i = 59; i >= 0; i--) {
                        Thread.sleep(1000);
                        Message message = new Message();
                        message.what = 1;
                        message.obj = i;


                        if (i == 0) {
                            handler.sendMessage(message);
                            mIdencode.setClickable(true);
                            mIdencode.setText("点击获取验证码");
                            mIdencode.setBackgroundColor(Color.parseColor("#e74c3c"));
                        } else {
                            handler.sendMessage(message);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }).start();

    }
}
