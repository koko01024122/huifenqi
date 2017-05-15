package com.example.cxhll.huifenq.Activitys;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.cxhll.huifenq.Adapters.ReTuringAdapter;
import com.example.cxhll.huifenq.Adapters.TuringMessageAdapter;
import com.example.cxhll.huifenq.NetWorks.NetSendMessage;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.Messages;
import com.example.cxhll.huifenq.item.SendMessage;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TuringActivity extends BaseActivity {
    private ArrayList<Messages> messagesListArray;
    private RecyclerView messageList;
    private TuringMessageAdapter adapter;
    private ReTuringAdapter adapter1;
    private Button send;
    private EditText message;

    String TAG = "TuringActivity.class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turing);
        setBar("客服中心",2);
        messagesListArray = new ArrayList<>();
        send = (Button) findViewById(R.id.send);
        message = (EditText) findViewById(R.id.message);
        messageList = (RecyclerView) findViewById(R.id.turinglist);
        adapter1=new ReTuringAdapter(TuringActivity.this,messagesListArray);
    //    adapter = new TuringMessageAdapter(TuringActivity.this, R.layout.turing_left, messagesListArray);
        messageList.setLayoutManager(new LinearLayoutManager(this));
        messageList.setItemAnimator(new DefaultItemAnimator());
        messageList.setAdapter(adapter1);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Messages item = new Messages();
                item.setType(1);
                item.setMessage(message.getText().toString());
                messagesListArray.add(item);

                adapter1.notifyDataSetChanged();
             //   messageList.setSelection(messageList.getBottom());
              //  messageList.smoothScrollToPosition(adapter1.getItemCount()-1);
                new Thread(runable).start();

            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                Messages item=new Messages();
                item.setType(2);
                String text= (String) msg.obj;
                item.setMessage(text);
                message.setText("");
                messagesListArray.add(item);

                adapter1.notifyDataSetChanged();
                messageList.smoothScrollToPosition(adapter1.getItemCount()-1);

            }
            super.handleMessage(msg);
        }
    };

    Runnable runable = new Runnable() {
        @Override
        public void run() {
            try {
                NetSendMessage net = new NetSendMessage();
                String json = net.NetSendMessage(makeJson(message.getText().toString()));
                if (json != null && json.startsWith("\ufeff")) {

                    json = json.substring(1);
                }
                JSONTokener jsonTokener = new JSONTokener(json);

                JSONObject obj = (JSONObject) jsonTokener.nextValue();
                final String text = obj.getString("text");
                Log.d(TAG, "run: " + text);

                handler.sendMessage(handler.obtainMessage(0,text));
                //  Gson gson=new Gson();
                //   ExpMessage message=gson.fromJson(json,ExpMessage.class);
                // Log.d("TuringActivity.class", "run: "+message.getText());

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    public void statr(String json) throws IOException, JSONException {


    }

    public String makeJson(String info) throws JSONException, IOException {
        SendMessage messageitem = new SendMessage();
        Gson gson = new Gson();
        messageitem.setInfo(info);
        messageitem.setKey("2de2ee89d2204d1aba62d636691d8887");
        messageitem.setUserid("123122");
        String json = gson.toJson(messageitem);

        Log.d(TAG, "makeJson: "+json);
        return json;
    }

    public String exjson(String report) throws JSONException {
        JSONTokener jsonTokener = new JSONTokener(report);
        String text = null;
        JSONObject jsonobject = (JSONObject) jsonTokener.nextValue();

        return null;
    }
}
