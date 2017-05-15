package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Activitys.ExpressListActivity;
import com.example.cxhll.huifenq.Activitys.NewOrderAty;
import com.example.cxhll.huifenq.Activitys.SendExpressActivity;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.OrderListItem;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by CXHLL on 2016/11/4.
 */

public class orderAdapt extends ArrayAdapter<OrderListItem> {
    private String TAG = "Hometest.class";
    View view1;
    private int mresourID;

    public orderAdapt(Context context, int resource, ArrayList<OrderListItem> objects) {
        super(context, resource, objects);
        mresourID = resource;

    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view1 = view;
        final ViewHolder viewHolder;
        final OrderListItem orderListItem = getItem(position);

        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(mresourID, null);

            viewHolder.name = (TextView) view.findViewById(R.id.tv_od_name);
            viewHolder.state = (TextView) view.findViewById(R.id.tv_od_state);
            viewHolder.time = (TextView) view.findViewById(R.id.order_time);
            viewHolder.stagepass = (TextView) view.findViewById(R.id.tv_od_state_pass);
            viewHolder.trade_anme = (TextView) view.findViewById(R.id.od_trade_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.name = (TextView) view.findViewById(R.id.tv_od_name);
        viewHolder.state = (TextView) view.findViewById(R.id.tv_od_state);
        viewHolder.time = (TextView) view.findViewById(R.id.order_time);
        viewHolder.stagepass = (TextView) view.findViewById(R.id.tv_od_state_pass);
        viewHolder.trade_anme = (TextView) view.findViewById(R.id.od_trade_name);
        viewHolder.functionImg = (ImageView) view.findViewById(R.id.function_img);
        viewHolder.functionText = (TextView) view.findViewById(R.id.function_text);
        viewHolder.function_part = (RelativeLayout) view.findViewById(R.id.function_part);
        viewHolder.function_num = (TextView) view.findViewById(R.id.function_num);
        String stage = "0";
        String phonenum = orderListItem.getPhonenum();
        String shijian = orderListItem.getTime();
        Log.d(TAG, "getView: " + shijian);
        viewHolder.name.setText(orderListItem.getName());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date times = date.parse(shijian);
            Log.d(TAG, "getView: +" + "转换");
            Calendar calendar = Calendar.getInstance();
            Log.d(TAG, "getView: 创建calendar");
            int M = calendar.get(Calendar.MONTH) + 1;

            int Year = calendar.get(Calendar.YEAR);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.setTime(times);
            if (Year == calendar.get(Calendar.YEAR)) {
                if (M == calendar.get(Calendar.MONTH) + 1) {
                    if (day == calendar.get(Calendar.DAY_OF_MONTH)) {
                        if (calendar.get(Calendar.MINUTE) < 10) {
                            viewHolder.time.setText("今天" + calendar.get(Calendar.HOUR_OF_DAY) + ":0" + calendar.get(Calendar.MINUTE));
                        } else {
                            viewHolder.time.setText("今天" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
                        }


                    } else if (day > calendar.get(Calendar.DAY_OF_MONTH)) {
                        int themonth = calendar.get(Calendar.MONTH) + 1;
                        viewHolder.time.setText(themonth + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日" + Calendar.HOUR_OF_DAY + "时");
                    }
                } else if (M > calendar.get(Calendar.MONTH) + 1) {
                    int themonth = calendar.get(Calendar.MONTH) + 1;
                    viewHolder.time.setText(themonth + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
                }
            } else {
                int m = calendar.get(Calendar.MONTH) + 1;
                viewHolder.time.setText(calendar.get(Calendar.YEAR) + "年" + m + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
            }
            Log.d(TAG, "getView: 创建time");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.trade_anme.setText(orderListItem.getTrade_name());
        if (orderListItem.getStage().equals("0")) {
            viewHolder.state.setVisibility(View.VISIBLE);
            viewHolder.stagepass.setVisibility(View.GONE);
            viewHolder.functionImg.setImageResource(R.drawable.retry);
            viewHolder.function_part.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), NewOrderAty.class);
                    intent.putExtra("retry", "1");
                    intent.putExtra("id", orderListItem.getId());
                    intent.putExtra("phonenum", orderListItem.getPhonenum());
                    intent.putExtra("tradename", orderListItem.getTrade_name());
                    intent.putExtra("price", orderListItem.getPrice());
                    intent.putExtra("paystage", orderListItem.getPaystage());
                    intent.putExtra("fromorder", "1");

                    intent.putExtra("province", orderListItem.getAddress_province());
                    intent.putExtra("city", orderListItem.getAddress_city());
                    intent.putExtra("area", orderListItem.getAddress_area());
                    intent.putExtra("name", orderListItem.getName());
                    intent.putExtra("tel", orderListItem.getPhonenum());

                    intent.putExtra("addressdetil", orderListItem.getAddress_detil());

                    Log.d(TAG, "onClick: " + orderListItem.getId());
                    getContext().startActivity(intent);
                }
            });

            viewHolder.functionText.setText("重新下单");
        } else if (orderListItem.getStage().equals("1")) {
            viewHolder.state.setVisibility(View.GONE);
            viewHolder.functionImg.setImageResource(R.drawable.urge);
            viewHolder.functionText.setText("催单");
            viewHolder.function_part.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(viewHolder.function_part, "已催单", Snackbar.LENGTH_SHORT).show();
                }
            });

            viewHolder.stagepass.setVisibility(View.VISIBLE);
            viewHolder.stagepass.setText("初审中");
        } else if (orderListItem.getStage().equals("2")) {
            viewHolder.state.setVisibility(View.GONE);
            viewHolder.functionImg.setImageResource(R.drawable.urge);
            viewHolder.functionText.setText("催单");
            viewHolder.stagepass.setVisibility(View.VISIBLE);
            viewHolder.function_part.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(viewHolder.function_part, "已催单", Snackbar.LENGTH_SHORT).show();
                }
            });

            viewHolder.stagepass.setText("电审中");
        } else if (orderListItem.getStage().equals("3")) {
            viewHolder.state.setVisibility(View.GONE);
            viewHolder.functionImg.setImageResource(R.drawable.urge);
            viewHolder.function_part.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Snackbar.make(viewHolder.function_part, "已催单", Snackbar.LENGTH_SHORT).show();
                }
            });

            viewHolder.functionText.setText("催单");
            viewHolder.stagepass.setVisibility(View.VISIBLE);
            viewHolder.stagepass.setText("复审中");
        } else if (orderListItem.getStage().equals("4")) {
            viewHolder.state.setVisibility(View.GONE);
            viewHolder.functionImg.setImageResource(R.drawable.urge);
            viewHolder.functionText.setText("催单");
            viewHolder.function_part.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(viewHolder.function_part, "已催单", Snackbar.LENGTH_SHORT).show();
                }
            });

            viewHolder.stagepass.setVisibility(View.VISIBLE);
            viewHolder.stagepass.setText("终审中");
        } else if (orderListItem.getStage().equals("5")) {
            viewHolder.state.setVisibility(View.GONE);
            viewHolder.stagepass.setVisibility(View.VISIBLE);
            viewHolder.functionImg.setImageResource(R.drawable.delivery);

            viewHolder.stagepass.setText("审核通过");
            Log.d(TAG, "getView: 收货方式为--------------" + orderListItem.getAddresstype());
            //if (orderListItem.getAddresstype().equals("1")){
            viewHolder.functionText.setText("约快递");
            viewHolder.function_part.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ExpressListActivity.class);
                    intent.putExtra("fromorder", "1");
                    intent.putExtra("province", orderListItem.getAddress_province());
                    intent.putExtra("city", orderListItem.getAddress_city());
                    intent.putExtra("area", orderListItem.getAddress_area());
                    intent.putExtra("name", orderListItem.getName());
                    intent.putExtra("tel", orderListItem.getPhonenum());
                    intent.putExtra("trade_name", orderListItem.getTrade_name());
                    intent.putExtra("addressdetil", orderListItem.getAddress_detil());
                    Log.d(TAG, "onClick: 详细地址为" + orderListItem.getAddress_detil());
                    getContext().startActivity(intent);
                }
            });
            //}
        } else if (orderListItem.getStage().equals("6")) {
            viewHolder.state.setVisibility(View.GONE);
            viewHolder.stagepass.setVisibility(View.VISIBLE);
            viewHolder.functionImg.setImageResource(R.drawable.order_finish);


            viewHolder.function_part.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            viewHolder.functionText.setText("订单完成");

            viewHolder.stagepass.setText("已收货");
        } else if (orderListItem.getStage().equals("9")) {
            viewHolder.state.setVisibility(View.GONE);
            viewHolder.functionImg.setImageResource(R.drawable.touch);
            viewHolder.function_part.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTouch(orderListItem.getPhonenum(), orderListItem.getName(), "http://www.hfenq.com/");
                }
            });
            viewHolder.functionText.setText("联系客户");
            viewHolder.stagepass.setVisibility(View.VISIBLE);
            viewHolder.stagepass.setText("待签约");
        }
        return view;
    }

    public void setTouch(final String phonenum, final String name, final String url) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.touchcusto, null);
        final RelativeLayout phone = (RelativeLayout) view.findViewById(R.id.touch_phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phonenum));
                getContext().startActivity(intent);
            }
        });
        RelativeLayout text = (RelativeLayout) view.findViewById(R.id.touch_text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phonenum));
                intent.putExtra("sms_body", name + "你好，我这里已经给您下单了，请您登陆您的后台" + url + "在“我的订单”中签订合同吧");

                getContext().startActivity(intent);
            }
        });
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view).show();
    }

    static class ViewHolder {
        public TextView name;
        public TextView state;
        public TextView time;
        public TextView stagepass;
        public TextView trade_anme;
        public ImageView functionImg;
        public TextView functionText;
        public RelativeLayout function_part;
        public TextView function_num;
    }
}
