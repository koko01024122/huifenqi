package com.example.cxhll.huifenq.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.tencent.bugly.beta.Beta;

public class VersionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        setBar("关于惠分期", 2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setLogic();
    }

    private void setLogic() {
        TextView checkup = (TextView) findViewById(R.id.check_update);
        checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Beta.checkUpgrade();
                Beta.autoDownloadOnWifi = true;
            }
        });
        TextView feedback = (TextView) findViewById(R.id.feed_back);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VersionActivity.this, FeedBackActivity.class);
                startActivity(intent);
            }
        });
    }
}
