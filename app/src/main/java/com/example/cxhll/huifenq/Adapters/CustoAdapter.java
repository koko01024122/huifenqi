package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cxhll.huifenq.Activitys.NewOrderAty;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.CustoItem;

import java.util.List;

import static com.example.cxhll.huifenq.R.id.custo_tel;

/**
 * Created by cxandpll on 17-2-27.
 */

public class CustoAdapter extends ArrayAdapter<CustoItem> {
    private static int resourceid;
    public CustoAdapter(Context context, int resource, List<CustoItem> objects) {
        super(context, resource, objects);
    resourceid=resource;
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustoItem item=getItem(position);
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(resourceid,null);

        }
        TextView custoname= (TextView) convertView.findViewById(R.id.custo_name);
        custoname.setText(item.getName());
        TextView custotel= (TextView) convertView.findViewById(custo_tel);
        custotel.setText(item.getPhonenum());
        return convertView;
    }
}
