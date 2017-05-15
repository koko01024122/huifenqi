package com.example.cxhll.huifenq.tools;

/**
 * Created by CXHLL on 2016/12/19.
 */


public class Pmt {

    /**
     *
     * @param price 价格
     * @param stage_num 分期期数
     * @param payment 首付
     * @param ioa
     * @param ooa 订单利率
     * @param pir 商户加息
     * @param type 订单类型
     * @param kxj 宽现金
     * @param kxq  宽限期
     * @return
     */
    public String pmt(String price,String stage_num,String payment,float ioa,float ooa,float pir,int type,float kxj,float kxq){
        double benjin = 0;
        System.out.println(benjin);

            benjin=(Float.parseFloat(price)-Float.parseFloat(payment))*(1.0f+ioa+ooa+pir);
        System.out.println(benjin);
        double mpmt=0f;
        float moli=1+0.0075f;
        float stage=Float.parseFloat(stage_num);
        mpmt=benjin*0.0075f*(Math.pow(moli, stage)/(Math.pow(moli, stage)-1));
        float b=(float)Math.round(mpmt*100)/100;
        String pmt=null;
        pmt=String.valueOf(b);
        return pmt;
    }
}
