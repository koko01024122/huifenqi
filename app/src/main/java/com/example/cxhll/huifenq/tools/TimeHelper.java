package com.example.cxhll.huifenq.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cxandpll on 17-2-12.
 */

public class TimeHelper {

    public String getTime(){
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss  ");
        String date=time.format(new Date());
        return date;
    }

    /**
     * 转变时间方法，可根据输入的时间对应生成现在应该显示的时间
     * 如：同一年的会省略时间中的XXXX年，当月会省略XXXX年XX月
     * @param orginalTme
     * @return
     */
    public  String turntime(String orginalTme){

        String time=null;
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date times=date.parse(orginalTme);

            Calendar calendar=Calendar.getInstance();

            int M=calendar.get(Calendar.MONTH)+1;
            int Year=calendar.get(Calendar.YEAR);
            int day=calendar.get(Calendar.DAY_OF_MONTH);
            calendar.setTime(times);
            int theonth=calendar.get(Calendar.MONTH)+1;
            if (Year==calendar.get(Calendar.YEAR)){
                if (M==theonth){
                    if (day==calendar.get(Calendar.DAY_OF_MONTH)){
                        if (calendar.get(Calendar.MINUTE)<10){
                          time=("今天"+calendar.get(Calendar.HOUR_OF_DAY)+":0"+calendar.get(Calendar.MINUTE));
                        }else {
                            time=("今天"+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
                        }

                    }else if(day>calendar.get(Calendar.DAY_OF_MONTH)){
                        time=(theonth+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日"+Calendar.HOUR_OF_DAY+"时");
                    }
                }else if (M>theonth){
                    time=(theonth+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日");
                }
            }else{
                time=(calendar.get(Calendar.YEAR)+"年"+theonth+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }

    public String cutTime(String orginalTme) throws ParseException {

        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date times=date.parse(orginalTme);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(times);
        String min=null;
        String h=null;
        if (calendar.get(Calendar.MINUTE)<10){
            min="0"+calendar.get(Calendar.MINUTE);
        }else {
            min=String.valueOf(calendar.get(Calendar.MINUTE));
        }
        if (calendar.get(Calendar.HOUR_OF_DAY)<10){
           h="0"+calendar.get(Calendar.HOUR_OF_DAY);
        }else {
            h=String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        }
        String time= h+":"+min;
        return time;
    }
    public String cutDays(String orginalTme) throws ParseException {

        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date times=date.parse(orginalTme);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(times);
        String Y=null;
        String M=null;
        String D=null;

            Y=String.valueOf(calendar.get(Calendar.YEAR));

        if (calendar.get(Calendar.MONTH)+1<10){
            M="0"+(calendar.get(Calendar.MONTH)+1);
        }else {
            M=String.valueOf((calendar.get(Calendar.MONTH)+1));
        }
        if (calendar.get(Calendar.DAY_OF_MONTH)<9){
            D="0"+(calendar.get(Calendar.DAY_OF_MONTH)+1);
        }else {
            D=String.valueOf((calendar.get(Calendar.DAY_OF_MONTH)+1));
        }
        String time= Y+"-"+M+"-"+D;
        return time;
    }
}
