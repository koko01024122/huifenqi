package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cxhll.huifenq.Login;
import com.example.cxhll.huifenq.R;

public class ChangePsdActivity extends BaseActivity {
private Button makesure;
    private TextInputLayout psd1;
    private TextInputLayout psd2;
    private Button changepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_psd);
        setBar("修改密码",2);


    }

    @Override
    protected void onStart() {
        super.onStart();
        setLogic();
    }
    private void setLogic(){



        psd1= (TextInputLayout) findViewById(R.id.new_password);
        psd2= (TextInputLayout) findViewById(R.id.new_password3);

        final EditText psd0=psd1.getEditText();
        final EditText psd3=psd2.getEditText();
        makesure= (Button) findViewById(R.id.makesure);
        makesure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (psd0.getText().toString().equals(psd3.getText().toString())&&psd3.getText().toString().equals(psd0.getText().toString())){

                    String id=	dbHelper.changepsd(dbHelper,psd0.getText().toString());

                    Toast.makeText(ChangePsdActivity.this,"修改成功",Toast.LENGTH_SHORT).show();


                    dbHelper.changeStage(dbHelper);
                    Intent intent=new Intent(ChangePsdActivity.this, Login.class);
                    startActivity(intent);

                    dbHelper.logingout(dbHelper);
                    finish();

                }else{
                    psd3.setError("两次输入密码不一致");
                }
            }
        });
    }
}
