package com.example.cxhll.huifenq.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cxhll.huifenq.R;

public class CreateAnnoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_anno);
        setBar("新公告",2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setLogic();
    }
    private void setLogic(){
        final Button finish= (Button) findViewById(R.id.anno_finish);
        final EditText title= (EditText) findViewById(R.id.anno_etitle);
        final EditText content= (EditText) findViewById(R.id.anno_econtent);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.create_anno(dbHelper,title.getText().toString(),content.getText().toString());
                finish();
            }
        });
    }
}
