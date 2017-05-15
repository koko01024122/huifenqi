package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.UserItem;

import java.util.ArrayList;

/**
 * Created by CXHLL on 2016/11/12.
 */

public class UserAdapter extends ArrayAdapter<UserItem> {
    private int resourceId;
    private int index=1;
    RelativeLayout overdue;
    String TAG="Hometest.class";
    public UserAdapter(Context context, int resource, ArrayList<UserItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        UserItem userItem = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView name = (TextView) view.findViewById(R.id.ui_name);

        TextView role = (TextView) view.findViewById(R.id.ui_role);
        TextView phone = (TextView) view.findViewById(R.id.ui_phonenum);
        name.setText(userItem.getmUiname());

        role.setText(userItem.getmUirole());
        return view;
    }
}
