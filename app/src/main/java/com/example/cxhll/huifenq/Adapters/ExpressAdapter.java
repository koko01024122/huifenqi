package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cxhll.huifenq.Activitys.TraceActivity;
import com.example.cxhll.huifenq.EMS.KdApiOrderDistinguish;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.ExpressItem;
import com.example.cxhll.huifenq.item.Traces;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cxandpll on 17-2-14.
 */

public class ExpressAdapter extends ArrayAdapter<ExpressItem> {

    private static int resourceid;
    String TAG = "Hometest.class";
    TextView express_state;
    ExpressItem item;

    public ExpressAdapter(Context context, int resource, List<ExpressItem> objects) {
        super(context, resource, objects);
        resourceid = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceid, null);


        }
        Log.d(TAG, "getView: -------1" + item.getCo_name());
        Log.d(TAG, "getView: " + item.getCu_name());
        Log.d(TAG, "getView: " + item.getEx_name());
        Log.d(TAG, "getView: " + item.getState());
        Log.d(TAG, "getView: " + item.getEx_num());

        TextView express_type = (TextView) convertView.findViewById(R.id.express_type);
        TextView express_num = (TextView) convertView.findViewById(R.id.express_num);
        express_num.setText(item.getEx_num());
        TextView comm_state = (TextView) convertView.findViewById(R.id.comm_state);
        comm_state.setText(item.getCo_name());
        express_state = (TextView) convertView.findViewById(R.id.express_state);
        TextView comm_name = (TextView) convertView.findViewById(R.id.comm_name);
        TextView time = (TextView) convertView.findViewById(R.id.exchange_time);
        comm_name.setText(item.getCu_name());
        if (item.getState().equals("3")) {
            express_state.setText("已签收");

        } else if (item.getState().equals("1")) {
            try {
                netWork();
            } catch (Exception e) {
                e.printStackTrace();
            }
            express_state.setText("运输中");
        } else if (item.getState().equals("2")) {
            try {
                netWork();
            } catch (Exception e) {
                e.printStackTrace();
            }
            express_state.setText("配送中");
        } else if (item.getState().equals("5")) {
            express_state.setText("待发货");

        }
        Log.d(TAG, "getView:item的srare是 " + item.getState());


        return convertView;
    }


    public void netWork() throws Exception{

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                int State = 0;

                if (msg.what == State) {

                    String state = (String) msg.obj;
                    express_state.setText(state);
                    if (state.equals("运输中")) {
                        item.setState("1");
                        Log.d(TAG, "handleMessage: 我来过1");
                    } else if (state.equals("配送中")) {
                        item.setState("2");
                        Log.d(TAG, "handleMessage: 我来过2");
                    }
                    if (state.equals("已签收")) {
                        item.setState("3");
                        express_state.setText(state);
                        Log.d(TAG, "handleMessage: 我来过3");

                    }
                    Log.d(TAG, "handleMessage: 当前它的状态是" + state);

                    Log.d(TAG, "handleMessage: " + item.getState());

                }
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
try{
                KdApiOrderDistinguish kdApiOrderDistinguish = new KdApiOrderDistinguish();
                kdApiOrderDistinguish.star(item.getEx_num());

                handler.sendMessage(handler.obtainMessage(0, kdApiOrderDistinguish.state));
}catch (Exception e){}
            }

        };
        new Thread(runnable).start();


    }
}
