package com.example.cxhll.huifenq.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.item.Messages;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class ReTuringAdapter extends RecyclerView.Adapter<ReTuringAdapter.MyViewHolder> {
    private static final String TAG = "Myadapter";
    private ArrayList<Messages> datas;
    private LayoutInflater inflater;

    public ReTuringAdapter(Context context, ArrayList<Messages> datas) {
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.turing_left, null, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Messages item = datas.get(position);
        holder.setIsRecyclable(false);
        if (item.getType() == 1) {
            holder.left.setText(item.getMessage());
            holder.right.setVisibility(View.GONE);
        } else {
            holder.right.setText(item.getMessage());
            holder.left.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView left;
        TextView right;

        public MyViewHolder(View itemView) {
            super(itemView);
            left = (TextView) itemView.findViewById(R.id.turing_left);
            right = (TextView) itemView.findViewById(R.id.turing_right);

        }
    }
}
