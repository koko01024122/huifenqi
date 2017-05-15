package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.cxhll.huifenq.EMS.KdApiOrderDistinguish;
import com.example.cxhll.huifenq.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class SelectExpressActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_express);
        setBar("查快递",2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setLogic();
    }
    public void setLogic(){
        final EditText select= (EditText) findViewById(R.id.input_express_num);
        final ImageView scanne= (ImageView) findViewById(R.id.scann_express);
        scanne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectExpressActivity.this, CaptureActivity.class);
            startActivityForResult(intent,RESULT_CANCELED);
            }
        });
        final ImageView select_start= (ImageView) findViewById(R.id.select_express_start);
        select.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                scanne.setVisibility(View.VISIBLE);
                select_start.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                scanne.setVisibility(View.GONE);
                select_start.setVisibility(View.VISIBLE);
                if (select.getText().length()<1){
                    scanne.setVisibility(View.VISIBLE);
                    select_start.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        select_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(SelectExpressActivity.this,TraceActivity.class);
                intent.putExtra("num",select.getText().toString());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CANCELED) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Intent intent=new Intent(SelectExpressActivity.this,TraceActivity.class);
                    intent.putExtra("num",result);

                    startActivity(intent);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(SelectExpressActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
