package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Activitys.MyAddressActivity;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.item.AddressItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxandpll on 17-2-23.
 */

public class AddressAdapter1 extends ArrayAdapter<AddressItem> {
    static int resourceId;
    HuifqDbHelper dbHelper1;
    ArrayList<AddressItem> mlist;
    String TAG = "Hometest.class";
    TextView name;
    LinearLayout addressll;
    TextView tel;
    TextView address;
    LinearLayout edit;
    LinearLayout del;
    CheckBox defaultl;

    public AddressAdapter1(Context context, int resource, ArrayList<AddressItem> objects, HuifqDbHelper dbHelper) {

        super(context, resource, objects);
        dbHelper1 = dbHelper;

        Log.d(TAG, "AddressAdapter:bug给我吐出来 ");
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        try {
            final AddressItem item = getItem(position);





                convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
                name = (TextView) convertView.findViewById(R.id.address_name);
                tel = (TextView) convertView.findViewById(R.id.address_phonenum);
                address = (TextView) convertView.findViewById(R.id.address_address);
                defaultl = (CheckBox) convertView.findViewById(R.id.address_default);
                edit = (LinearLayout) convertView.findViewById(R.id.address_edit);
                del = (LinearLayout) convertView.findViewById(R.id.address_del);
                addressll = (LinearLayout) convertView.findViewById(R.id.addressitem);


             name.setText(item.getName());
             tel.setText(item.getTel());
             address.setText(item.getProvince() + item.getCity() + item.getArea() + item.getDetil());
            if (item.getDefaults().equals("1")) {
                 defaultl.setChecked(true);
            }
             defaultl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if ( item.getDefaults().equals("1")) {
                        dbHelper1.update_address(dbHelper1, item.getId(), "0");
                    } else if (item.getDefaults().equals("0")){
                        dbHelper1.update_address(dbHelper1, item.getId(), "1");

                    }

                }
            });
             edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
             del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mlist = new ArrayList<AddressItem>();
                    mlist = dbHelper1.select_address(dbHelper1);


                    AlertDialog.Builder build = new AlertDialog.Builder(getContext());
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog, null);
                    TextView content = (TextView) view.findViewById(R.id.dialog_text);
                    content.setText("确认要删除该地址吗？");
                    build.setView(view);
                    build.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // addressll.setVisibility(View.GONE);


                            dbHelper1.del_address(dbHelper1, item.getId());
                            Log.d(TAG, "onClick: 被干掉的item是" + item.getName());
                            remove(item);
                            Log.d(TAG, "onClick: 听说它被干掉了" + item.getName());
                            // setNotifyOnChange(true);
                            Log.d(TAG, "onClick: " + position);
                            // notifyDataSetInvalidated();
                            notifyDataSetInvalidated();
                            // notify();
                            notifyDataSetChanged();
                            setNotifyOnChange(true);
                            Log.d(TAG, "onClick: ");
                            //Snackbar.make(del, "删除成功", Snackbar.LENGTH_SHORT).show();
                            Toast.makeText(getContext(),"删除成功",Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onClick: ");


                        }
                    });
                    build.setNegativeButton("取消", null);
                    build.show();

                }

             });
        } catch (Exception e) {
            Log.d(TAG, "getView: "+e);
        }



       return convertView;

    }

}


