package com.example.cxhll.huifenq.EMS;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.cxhll.huifenq.item.Traces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cxandpll on 17-2-13.
 */
public class Gjson {
    String TAG="TraceActivity.class";
    private static final int HEAD =0 ;
    private static final int BODY =1 ;
    String json;
    String num;
    boolean one=false;//运输中
    boolean two=false;//配送中
    boolean three=false;//已签收
    public HashMap<String,Object> getCode() throws JSONException {
        HashMap<String,Object> hashMap=new HashMap<String, Object>();
        JSONObject JSON1=new JSONObject(json);
        //{  "EBusinessID": "1275600",  "Success": true,  "LogisticCode": "70737907079124",
        // "Shippers": [    {      "ShipperCode": "HTKY",      "ShipperName": "百世汇通"    }  ]}
        //{  "EBusinessID": "1275600",  "ShipperCode": "HTKY",  "Success": true,
        // "LogisticCode": "70737907079124",  "State": "3"
        JSONArray shipper=JSON1.getJSONArray("Shippers");
        //String code=shipper.getString(1);
      try{
        JSONObject codes=shipper.getJSONObject(0);
        String code1=codes.getString("ShipperCode");
        hashMap.put("Stage",JSON1.getBoolean("Success"));
        hashMap.put("Num",JSON1.getString("LogisticCode"));

        hashMap.put("code",codes.getString("ShipperCode"));
        hashMap.put("Name",codes.getString("ShipperName"));
;}catch (Exception e){}
        return hashMap;
    }
    public ArrayList<Traces> analysis() throws JSONException {
        ArrayList<Traces> list=new ArrayList<>();
        JSONObject JSON1=new JSONObject(json);
        JSONArray shipper=JSON1.getJSONArray("Traces");
        //String code=shipper.getString(1);
        for (int i=shipper.length()-1;i>=0;i--){

            JSONObject codes=shipper.getJSONObject(i);
            Traces item=new Traces();
            item.setAcceptTime(codes.getString("AcceptTime"));
            Log.d(TAG, "analysis时间: "+codes.getString("AcceptTime"));
            item.setAcceptStation(codes.getString("AcceptStation"));
           list.add(item);
        }

        return list;
    }
    public String state(){
        if (json.indexOf("已签收")!=-1||json.indexOf("已收")!=-1||json.indexOf("签收")!=-1){
            Log.d(TAG, "state: "+"马上返回");
            return "已签收";
        }
       else if(json.indexOf("配送")!=-1||json.indexOf("派送")!=-1||json.indexOf("派件")!=-1){
            Log.d(TAG, "state: "+"妈蛋怎么没返回");
            return "配送中";
        }else
            Log.d(TAG, "state: "+json);
        {
            return "运输中";
        }

    }
}
