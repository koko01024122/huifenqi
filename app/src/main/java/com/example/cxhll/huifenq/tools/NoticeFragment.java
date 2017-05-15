package com.example.cxhll.huifenq.tools;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cxhll.huifenq.Activitys.NoticeActivity;
import com.example.cxhll.huifenq.R;

/**
 * Created by CXHLL on 2017/1/14.
 */

public class NoticeFragment extends Fragment {


    private View view;
public  NoticeFragment(){

}
    @SuppressLint("ValidFragment")
    public NoticeFragment(View view) {
        this.view=view;
    }

    public static NoticeFragment newInstance(View view) {

        return new NoticeFragment(view);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= null;//LayoutInflater.from(getContext()).inflate(R.layout.notices,null);
        view=this.view;
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));



        return view;
    }


}
