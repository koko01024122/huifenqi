package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.cxhll.huifenq.item.CollectionItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017/3/3 0003.
 */

public class CollectListAdapter extends ArrayAdapter<CollectionItem> {
    private static int resourceid;
    public CollectListAdapter(Context context, int resource, ArrayList<CollectionItem> objects) {
        super(context, resource, objects);
    resourceid=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(resourceid,null);
        }
        return convertView;
    }
}
