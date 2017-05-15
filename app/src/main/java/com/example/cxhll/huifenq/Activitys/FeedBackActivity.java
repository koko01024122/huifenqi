package com.example.cxhll.huifenq.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cxhll.huifenq.R;

public class FeedBackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        setBar("意见反馈",2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setLogoi();
    }
    private void setLogoi(){
        EditText feeds= (EditText) findViewById(R.id.feedback_edit);
        Button feed_button= (Button) findViewById(R.id.feedback_button);
        feed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
