package com.example.cxhll.huifenq.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cxhll.huifenq.dao.MessageListHelper;
import com.example.cxhll.huifenq.R;

import org.w3c.dom.Text;

/**
 * Created by CXHLL on 2016/11/1.
 */

public class OrderSuccAty extends BaseActivity {
    private TextView address;
    private TextView order_num;
    private TextView address_Type;
    private TextView addressdetil;
    private TextView address_phonenum;
    private RelativeLayout success_address;
    //	private ArrayList<String> infos=new ArrayList<String>();;
    private TextView address_name;
    private String TAG = "OrderSuccAty.class";
    private TextView tishi;

    /**
     * 此部分需要补充的内容有
     * 1.从上一级Intent中获取更多的数据
     * 2.添加一个三秒后自动关闭的线程
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_order);
        address_phonenum = (TextView) findViewById(R.id.et_succ_receiverphonenum);//收货人电话
        address = (TextView) findViewById(R.id.show_succ_address);
        TextView address_text= (TextView) findViewById(R.id.tv_succ_address);
        TextView name_text= (TextView) findViewById(R.id.tv__succ_receiver);
        TextView tel_text= (TextView) findViewById(R.id.tv__succ_receiverphonenum);
        address_Type = (TextView) findViewById(R.id.tv_succ_type);//收货方式
        address_name = (TextView) findViewById(R.id.tv_succ_receivername);//收货人姓名
        addressdetil = (TextView) findViewById(R.id.show_succ_address);//详细地址地址
        success_address = (RelativeLayout) findViewById(R.id.rl_address_success);
        tishi = (TextView) findViewById(R.id.tishi);
        //	infos=new ArrayList<String>();

        MessageListHelper dbHelper = new MessageListHelper(OrderSuccAty.this, "mess", null, 2);
        Intent intent = getIntent();
        if (intent.getStringExtra("addresstype").equals("0")) {
            address_Type.setText("自提");
            address.setVisibility(View.GONE);
            address_name.setVisibility(View.GONE);
            address_phonenum.setVisibility(View.GONE);
            addressdetil.setVisibility(View.GONE);
            address_text.setVisibility(View.GONE);
            name_text.setVisibility(View.GONE);
            tel_text.setVisibility(View.GONE);
        } else {

            address_Type.setText("快递");
            address.setText(intent.getStringExtra("address"));
            address_name.setText(intent.getStringExtra("addressname"));
            address_phonenum.setText(intent.getStringExtra("receiverphonenum"));
        }


        Log.d(TAG, "onCreate: Intent运行");
        String phonenum = intent.getStringExtra("phonenum");
        Log.d(TAG, "onCreate: 准备获取数据");
        pao();

        //infos=dbHelper.selectSucc(phonenum,dbHelper,infos);
        //infos=dbHelper.select(dbHelper);
//		Log.d(TAG, "onCreate: "+infos.get(1));
        //	dbHelper.seletctext(dbHelper);
        Log.d(TAG, "onCreate: 已经获取到");


        //	order_num.setText(String.valueOf(infos.get(1)));
        //int type= Integer.parseInt(infos.get(8));
        //	if (type==0){
        //address_Type.setText("自提");
        //success_address.setVisibility(View.GONE);
        //}else if (type==1){
        //	address_Type.setText("快递");
        //	addressdetil.setText(infos.get(10));
        //address_phonenum.setText(infos.get(9));
        //	address_name.setText(infos.get(7));
        //}
        //	}catch (NullPointerException e){

    }

    private int a = 3;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tishi.setText("此页面将在" + 3 + "秒后自动关闭");
                    break;
                case 2:
                    tishi.setText("此页面将在" + 2 + "秒后自动关闭");
                    break;
                case 3:
                    tishi.setText("此页面将在" + 1 + "秒后自动关闭");
                    break;
            }
            super.handleMessage(msg);
        }

    };

    public void pao() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int d = 3;
                    for (int i = 1; i < 4; i++) {
                        Thread.sleep(1000);
                        Message message = new Message();
                        message.what = i;
                        handler.sendMessage(message);
                        Log.d(TAG, "run: " + i);

                        if (i == 3) {
                            finish();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }).start();

    }


}






