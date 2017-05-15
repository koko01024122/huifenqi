package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.AnnoItem;
import com.example.cxhll.huifenq.tools.TimeHelper;

import java.util.ArrayList;

/**
 * Created by cxandpll on 17-2-12.
 */

public class AnnoAdapter extends ArrayAdapter<AnnoItem> {

    private static int resourceId;

    public AnnoAdapter(Context context, int resource, ArrayList<AnnoItem> list) {
        super(context, resource, list);
        resourceId = resource;
    }

    TimeHelper timeHelper = new TimeHelper();

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AnnoItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);

        }
        TextView title = (TextView) convertView.findViewById(R.id.anno_title);
        title.setText(item.getTitle());
        TextView content = (TextView) convertView.findViewById(R.id.anno_content);
        content.setText(item.getContent());
        TextView name = (TextView) convertView.findViewById(R.id.anno_uname);
        name.setText(item.getSendu());
        TextView time = (TextView) convertView.findViewById(R.id.anno_time);
        time.setText(timeHelper.turntime(item.getTime()));

        return convertView;
    }
}
