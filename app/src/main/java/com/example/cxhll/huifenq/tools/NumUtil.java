package com.example.cxhll.huifenq.tools;

/**
 * Created by CXHLL on 2017/1/2.
 */

public class NumUtil {
    public static String NumberFormat(float f,int m){
        return String.format("%."+m+"f",f);
    }

    public static float NumberFormatFloat(float f,int m){
        String strfloat = NumberFormat(f,m);
        return Float.parseFloat(strfloat);
    }
}
