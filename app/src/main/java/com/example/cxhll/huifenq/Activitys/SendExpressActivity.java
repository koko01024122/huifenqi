package com.example.cxhll.huifenq.Activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.ChangeAddressPopwindow;
import com.example.cxhll.huifenq.EMS.KdniaoSubscribeAPI;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.AddressItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SendExpressActivity extends BaseActivity {
    private String sProvinceName;
    private String sCityName;
    private String sExpAreaName;
    private String pProvinceName;
    private String pCityName;
    private String pExpAreaName;
    private String shippercode;
    private EditText express_comm_name;
    private EditText remarks;
    private EditText receiver_name;
    private EditText receiver_tel;
    private EditText receiver_address_detil;
    private TextView receiver_address;
    private EditText sender_name;
    private EditText sender_tel;
    private EditText sender_address_detil;
    private TextView sender_address;
    private RadioButton mepay;
    private Intent intent1;
    private RadioButton menotpay;
    private LinearLayout default_part;
    private RelativeLayout sender_infos;
    private int paytype = 0;
    TextView name;
    LinearLayout addressll;
    TextView tel;
    TextView address;
    int set=0;
    LinearLayout edit;
    LinearLayout del;
    AddressItem item;
    CheckBox defaultl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_express);
        intent1 = getIntent();
        String shippername = intent1.getStringExtra("shippername");
        setBar(shippername, 2);
        shippercode = intent1.getStringExtra("shippercode");

    }

    @Override
    protected void onStart() {
        super.onStart();
        setLogic();
    }

    public void setLogic() {
        sender_name = (EditText) findViewById(R.id.sender_name_put);
        sender_tel = (EditText) findViewById(R.id.sender_tel_put);
        sender_address_detil = (EditText) findViewById(R.id.sender_address_detil);
        Button express_send = (Button) findViewById(R.id.express_send);
        express_comm_name = (EditText) findViewById(R.id.express_comm_name);
        remarks = (EditText) findViewById(R.id.express_remarks);
        receiver_name = (EditText) findViewById(R.id.receiver_name_put);
        receiver_address_detil = (EditText) findViewById(R.id.receiver_address_detil);
        receiver_tel = (EditText) findViewById(R.id.receiver_tel_put);
        sender_address = (TextView) findViewById(R.id.sender_address);
        Log.d(TAG, "setLogic: 设置逻辑");
        sender_infos= (RelativeLayout) findViewById(R.id.express_sender_infos);
        name = (TextView) findViewById(R.id.address_name1);
        receiver_address = (TextView) findViewById(R.id.receiver_address_put);
        tel = (TextView) findViewById(R.id.address_phonenum1);

        mepay = (RadioButton) findViewById(R.id.me_pay);

        menotpay = (RadioButton) findViewById(R.id.me_notpay);


        address = (TextView) findViewById(R.id.address_address1);

        edit = (LinearLayout) findViewById(R.id.address_edit1);
        del = (LinearLayout) findViewById(R.id.address_del1);
        addressll = (LinearLayout) findViewById(R.id.addressitem);
        default_part = (LinearLayout) findViewById(R.id.default_part);
        intent1 = getIntent();
    item = new AddressItem();
        try {
        Log.d(TAG, "setLogic:查找前 ");
            item = dbHelper.select_address_default(dbHelper);
            Log.d(TAG, "setLogic: try中");
           if (item!=null){
                sender_name.setText(item.getName());

                sender_tel.setText(item.getTel());
               sender_address_detil.setText(item.getDetil());
               sender_address.setText(item.getProvince() + item.getCity() + item.getArea() + item.getDetil());
                tel.setText(item.getTel());
               name.setText(item.getName());

                address.setText(item.getProvince() + item.getCity() + item.getArea() + item.getDetil());
                sProvinceName = item.getProvince();

                sCityName = item.getCity();
                sExpAreaName = item.getArea();

                del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sender_infos.setVisibility(View.VISIBLE);
                        default_part.setVisibility(View.GONE);
                        Log.d(TAG, "setLogic: 默认地址不为空");
                        sender_name.setText("");
                        sender_tel.setText("");
                        sender_address.setText("");
                        sender_address_detil.setText("");


                        set=1;





                    }
                });
               edit.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       sender_name.setText(item.getName());
                       sender_tel.setText(item.getTel());
                       sender_address.setText(item.getProvince() + item.getCity() + item.getArea() );
                       sender_address_detil.setText(item.getDetil());
                       name.setText("");
                       tel.setText("");
                       address.setText("");
                       default_part.setVisibility(View.GONE);
                       sender_infos.setVisibility(View.VISIBLE);
                   }
               });
            }else {
               if (item == null) {

                   Log.d(TAG, "setLogic: 默认地址为空");
                   default_part.setVisibility(View.GONE);
                   sender_infos.setVisibility(View.VISIBLE);
               }
           }
        } catch (NullPointerException e) {
            Log.d(TAG, "setLogic: catch中"+e);
            default_part.setVisibility(View.GONE);
            sender_infos.setVisibility(View.VISIBLE);
        }



        express_comm_name.setText(intent1.getStringExtra("trade_name"));


        receiver_address_detil.setText(intent1.getStringExtra("addressdetil"));
        receiver_name.setText(intent1.getStringExtra("name"));
        receiver_tel.setText(intent1.getStringExtra("tel"));

        receiver_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(SendExpressActivity.this);
                mChangeAddressPopwindow.setAddress("湖北", "武汉", "武昌区");
                mChangeAddressPopwindow.showAtLocation(receiver_address, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow
                        .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

                            @Override
                            public void onClick(String province, String city, String area) {
                                // TODO Auto-generated method stub
                                pProvinceName = province;
                                pCityName = city;
                                pExpAreaName = area;
                                if (province.equals("天津") || province.equals("北京") || province.equals("上海") || province.equals("重庆")) {
                                    pExpAreaName = city;
                                    pProvinceName = province + "市";
                                    pCityName = province + "市";
                                } else {
                                    pProvinceName = province + "省";
                                    pCityName = city + "市";
                                    pExpAreaName = area;
                                }


                                receiver_address.setText(pProvinceName + pCityName + pExpAreaName);

                            }
                        });
            }
        });



        try {
            if (intent1.getStringExtra("fromorder").equals("1")) {
                receiver_address.setText(intent1.getStringExtra("province") + intent1.getStringExtra("city") + intent1.getStringExtra("area"));
                receiver_address_detil.setText(intent1.getStringExtra("addressdetil"));
                pProvinceName = intent1.getStringExtra("province");
                pCityName = intent1.getStringExtra("city");
                pExpAreaName = intent1.getStringExtra("area");
            }
        } catch (NullPointerException e) {
        }

        sender_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(SendExpressActivity.this);
                mChangeAddressPopwindow.setAddress("湖北", "武汉", "武昌区");
                mChangeAddressPopwindow.showAtLocation(sender_address, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow
                        .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

                            @Override
                            public void onClick(String province, String city, String area) {
                                // TODO Auto-generated method stub

                                sProvinceName = province;

                                sCityName = city;
                                sExpAreaName = area;
                                if (province.equals("天津") || province.equals("北京") || province.equals("上海") || province.equals("重庆")) {
                                    sExpAreaName = city;
                                    sProvinceName = province + "市";
                                    sCityName = province + "市";
                                    Log.d(TAG, "onClick: 地址！！！！！！");
                                } else {
                                    sProvinceName = province + "省";
                                    Log.d(TAG, "onClick:地址———————————————————————— ");
                                    sCityName = city + "市";
                                    sExpAreaName = area;
                                }

                                sender_address.setText(sProvinceName + sCityName + sExpAreaName);

                            }
                        });
            }
        });

        mepay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                InputMethodManager inm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inm.hideSoftInputFromWindow(mepay.getWindowToken(),0);
                if (mepay.isChecked()) {
                    menotpay.setChecked(false);
                    paytype = 1;
                }
            }
        });
        menotpay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                InputMethodManager inm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inm.hideSoftInputFromWindow(menotpay.getWindowToken(),0);
                if (menotpay.isChecked()) {
                    mepay.setChecked(false);
                    paytype = 2;
                }
            }
        });
        express_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(SendExpressActivity.this, ExpressFinishActivity.class);
                final JSONObject expressinfo = new JSONObject();
                JSONObject senderinfos = new JSONObject();
                JSONObject Receiverinfos = new JSONObject();
                JSONArray foodsinfo = new JSONArray();
                JSONObject foods = new JSONObject();
                try {
                    senderinfos.put("Name", sender_name.getText().toString());
                    senderinfos.put("Mobile", sender_tel.getText().toString());
                    senderinfos.put("ProvinceName", sProvinceName);
                    senderinfos.put("CityName", sCityName);
                    senderinfos.put("ExpAreaName", sExpAreaName);
                    senderinfos.put("Address", sender_address_detil.getText().toString());
                    Receiverinfos.put("Name", receiver_name.getText().toString());
                    Receiverinfos.put("Mobile", receiver_tel.getText().toString());
                    Receiverinfos.put("ProvinceName", pProvinceName);
                    Receiverinfos.put("CityName", pCityName);
                    Receiverinfos.put("ExpAreaName", pExpAreaName);
                    Receiverinfos.put("Address", receiver_address_detil.getText().toString());
                    foods.put("GoodsName", express_comm_name.getText().toString());

                    foodsinfo.put(0, foods);
                    expressinfo.put("OrderCode", "0");
                    expressinfo.put("ShipperCode", shippercode);
                    expressinfo.put("PayType", paytype);
                    expressinfo.put("ExpType", 1);

                    expressinfo.put("Sender", senderinfos);
                    expressinfo.put("Receiver", Receiverinfos);
                    expressinfo.put("Commodity", foodsinfo);
                    expressinfo.put("Remark", remarks.getText().toString());
                    expressinfo.put("LogisticCode", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "onClick: " + expressinfo.toString());



                if (check_full()) {
                    Log.d(TAG, "onClick: " + check_full());
                    Log.d(TAG, "onClick: 进入了检查完整性");
                    intent.putExtra("json", expressinfo.toString());
try {


                   // Log.d(TAG, "onClick: "+item.getName().equals(""));
                    if (item.getName()==null){
                        Log.d(TAG, "onClick: 满足条件1");
                        AlertDialog.Builder build = new AlertDialog.Builder(SendExpressActivity.this);
                        View view = LayoutInflater.from(SendExpressActivity.this).inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dialog));
                        TextView content = (TextView) view.findViewById(R.id.dialog_text);
                        content.setText("您是否需要将此地址设置为默认地址？");
                        build.setView(view);

                        Log.d(TAG, "onClick: 满足条件1");
                        build.setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.insert_address(dbHelper, sender_name.getText().toString(), sender_tel.getText().toString(), sProvinceName, sCityName, sExpAreaName, "1",sender_address_detil.getText().toString());
                                finish();
                                startActivity(intent);
                            }
                        });
                        Log.d(TAG, "onClick: 满足条件1");
                        build.setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(intent);
                            }
                        });
                        build.show();
                    }else if (set==1 && item!=null){
                        AlertDialog.Builder build = new AlertDialog.Builder(SendExpressActivity.this);
                        View view = LayoutInflater.from(SendExpressActivity.this).inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dialog));
                        TextView content = (TextView) view.findViewById(R.id.dialog_text);
                        content.setText("您是否需要将此地址存入地址列表？");
                        build.setView(view);
                        build.setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.insert_address(dbHelper, name.getText().toString(), tel.getText().toString(), sProvinceName, sCityName, sExpAreaName, "0",sender_address_detil.getText().toString());
                                finish();

                            }
                        });
                        build.setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(intent);
                            }
                        });
                        build.show();
                    }
}catch (NullPointerException e){

}

                }
            }
        });
    }

    public boolean check_full() {
        boolean ecn = false;
        boolean rn = false;
        boolean rt = false;
        boolean ra = false;
        boolean rad = false;
        boolean sn = false;
        boolean st = false;
        boolean sa = false;
        boolean sad = false;
        boolean pay = false;
        Log.d(TAG, "onClick: 进入了检查完整性");
        if (express_comm_name.getText().toString().equals("")) {
            express_comm_name.setError("商品名称不能为空");

        } else {
            ecn = true;
        }
        if (receiver_name.getText().toString().equals("")) {
            receiver_name.setError("收件人不能为空");
        } else {
            rn = true;
        }

        if (receiver_tel.getText().toString().equals("")) {
            receiver_tel.setError("收件人电话不能为空");
        } else {
            rt = true;
        }

        if (receiver_address.getText().toString().equals("轻触选择地区")) {
            Toast.makeText(SendExpressActivity.this, "请选择收件人地址", Toast.LENGTH_SHORT).show();
        } else {
            ra = true;
        }
        if (receiver_address_detil.getText().toString().equals("")) {
            receiver_address_detil.setError("请填写收件人详细地址");

        } else {
            rad = true;
        }
        if (sender_name.getText().toString().equals("")) {
            sender_name.setError("发件人不能为空");
        } else {
            sn = true;

        }
        if (sender_tel.getText().toString().equals("")) {
            sender_tel.setError("");
        } else {
            st = true;
        }
        if (sender_address.getText().toString().equals("轻触选择地区")) {
            Toast.makeText(SendExpressActivity.this, "请选择收件人地址", Toast.LENGTH_SHORT).show();
        } else {
            sa = true;
        }
        if (sender_address_detil.getText().toString().equals("")) {
            sender_address_detil.setError("请填写发件人详细地址");
        } else {
            sad = true;
        }

        if (mepay.isChecked() || menotpay.isChecked()) {
            pay = true;
        } else {
            Toast.makeText(SendExpressActivity.this, "请选择付款方式", Toast.LENGTH_SHORT).show();
        }


        if (ecn && rn && rt && ra && rad && sn && st && sa && sad && pay) {
            return true;
        }
        return false;
    }
}
