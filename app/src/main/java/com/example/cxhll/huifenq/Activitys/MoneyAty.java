package com.example.cxhll.huifenq.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxhll.huifenq.Adapters.MoneyAdp;
import com.example.cxhll.huifenq.Adapters.WareReAdapter;
import com.example.cxhll.huifenq.Adapters.WaresAdapter;
import com.example.cxhll.huifenq.Adapters.WithAdapter;
import com.example.cxhll.huifenq.R;
import com.example.cxhll.huifenq.dao.HuifqDbHelper;
import com.example.cxhll.huifenq.item.Moneys;
import com.example.cxhll.huifenq.item.With;
import com.example.cxhll.huifenq.tools.NumAnim;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.Legend;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CXHLL on 2016/12/10.
 */

public class MoneyAty extends BaseActivity {
    /**
     *

    private float tmoneys = 0;
    private RelativeLayout rl1;
    private RelativeLayout rl2;
    private RelativeLayout rl3;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView takemoneybutton;
    private TextView money;

    private ListView takeList;
    private ArrayList<Moneys> list;
    private RadioButton day_sales;
    private RadioButton month_sales;
    private MoneyAdp adp;
    private BarChart oid;
    private ListView withlists;
    private BarChart mbarChart;
    private String TAG="MoneyAty.class";
    private ArrayList<HashMap<Object,Object>> sales;
    private BarData mBarData;
    private Spinner charttype;
    private ArrayAdapter<String> typeadp;
    private LineChart  chart;
    private PieChart typechart;
    ArrayList<String> dates;
    private WithAdapter saleadp;
    private ArrayList<With> withlist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneys);
        setDbHelper(this);
        setBar("财务中心",0);
        sales=new ArrayList<HashMap<Object, Object>>();
        mbarChart= (BarChart) findViewById(R.id.barChart);
        day_sales= (RadioButton) findViewById(R.id.day_sale);
        day_sales.setChecked(true);
        month_sales= (RadioButton) findViewById(R.id.month_sale);
        month_sales.setChecked(false);
        chart= (LineChart) findViewById(R.id.sales_chart);
        typechart= (PieChart) findViewById(R.id.type_piechart);
        mBarData=getBarData(4,100);
        showBarChart(mbarChart,mBarData);
        charttype= (Spinner) findViewById(R.id.data_show_sp);
        dates=new ArrayList<String>();
        dates.add("请选择图表类型");
        dates.add("订单类型构成图");
        dates.add("子账号销量排行");
        dates.add("销量波动图");
        charttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (charttype.getSelectedItem().equals("订单类型构成图")){
                    setPie();
                    chart.setVisibility(View.GONE);
                    typechart.setVisibility(View.VISIBLE);
                    mbarChart.setVisibility(View.GONE);
                    month_sales.setVisibility(View.GONE);
                    day_sales.setVisibility(View.GONE);

                }
                if (charttype.getSelectedItem().equals("销量波动图")){
                    setcharts();
                    chart.setVisibility(View.VISIBLE);
                    typechart.setVisibility(View.GONE);
                    mbarChart.setVisibility(View.GONE);
                    month_sales.setVisibility(View.VISIBLE);

                    day_sales.setVisibility(View.VISIBLE);

                }
                if (charttype.getSelectedItem().equals("子账号销量排行")){

                    chart.setVisibility(View.GONE);
                    typechart.setVisibility(View.GONE);
                    mbarChart.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        month_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day_sales.setChecked(false);
                setmonthcharts();

            }
        });
        day_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                month_sales.setChecked(false);
                setcharts();
            }
        });
        typeadp=new ArrayAdapter<String>(MoneyAty.this,android.R.layout.simple_spinner_item,dates);
        typeadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        charttype.setAdapter(typeadp);
        list=new ArrayList<Moneys>();
        oid= (BarChart) findViewById(R.id.barChart);
     //   oid.setOnChartValueSelectedListener(this);

        oid.setDrawBarShadow(false);
        oid.setDrawValueAboveBar(true);
        oid.setMaxVisibleValueCount(60);
        oid.setPinchZoom(false);
        oid.setBackgroundColor(Color.WHITE );
        oid.setDrawGridBackground(false);
        oid.setTouchEnabled(true);



        ArrayList<String> xVaksl=new ArrayList<String>();
        ArrayList<BarEntry> yValsl=new ArrayList<BarEntry>();
       sales=dbHelper.sales_list(dbHelper);
        Log.d(TAG, "onCreate: ----------获取到sales"+sales.size());
        list=dbHelper.select_moneylist(dbHelper);
        Log.d(TAG, "onCreate: ----------进入for循环");
try{
        for ( HashMap<Object,Object> moneyitem1:sales){
            Log.d(TAG, "onCreate: ----------进入for循环");
            xVaksl.add(String.valueOf(moneyitem1.get("name")));
            Log.d(TAG, "onCreate: "+moneyitem1.get("name"));
            yValsl.add(new BarEntry(sales.indexOf(moneyitem1),Integer.parseInt(moneyitem1.get("sales").toString())));

        }
    xVaksl.add("小张");
    xVaksl.add("小王");
    xVaksl.add("小赵");
    yValsl.add(new BarEntry(1,10));
    yValsl.add(new BarEntry(2,8));
    yValsl.add(new BarEntry(3,5));
        BarDataSet barDataSet=new BarDataSet(yValsl,"销量排行");
        barDataSet.setColor(Color.rgb(114,188,223));
        ArrayList<BarDataSet> barDataSets =new ArrayList<BarDataSet>();
        barDataSets.add(barDataSet);

        BarData barData=new BarData(xVaksl,barDataSets);
     //   oid.setData(barData);

}catch (NullPointerException e){}

        Legend legend=oid.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(6f);
        legend.setTextColor(Color.BLACK);



        adp=new MoneyAdp(MoneyAty.this,R.layout.takemoneyitem,list);

        takeList= (ListView) findViewById(R.id.takemoney_list);
        takeList.setAdapter(adp);


        money= (TextView) findViewById(R.id.money_show);
        NumAnim.startAnim(money, Float.parseFloat(dbHelper.select_with(dbHelper).toString()),700);
        //money.setText(dbHelper.select_with(dbHelper));
        takemoneybutton= (TextView) findViewById(R.id.takemoney_button);
        takemoneybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              howmuch();
            }
        });
        if (dbHelper.select_with(dbHelper).equals("0")){
            takemoneybutton.setText("您账户余额为0，快去下单吧");
        }
        rl1= (RelativeLayout) findViewById(R.id.takemoney_rl);
        rl2= (RelativeLayout) findViewById(R.id.data_rl);
        rl3= (RelativeLayout) findViewById(R.id.reports_rl);
        tv1= (TextView) findViewById(R.id.money_takemoney);
        tv1.setTextColor(Color.parseColor("#ea4130"));
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl1.setVisibility(View.VISIBLE);
                tv1.setTextColor(Color.parseColor("#ea4130"));
                tv2.setTextColor(Color.parseColor("#8f8f8f"));
                tv3.setTextColor(Color.parseColor("#8f8f8f"));
                rl3.setVisibility(View.GONE);
                rl2.setVisibility(View.GONE);
            }
        });
        tv2= (TextView) findViewById(R.id.money_moneychart);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl2.setVisibility(View.VISIBLE);
                tv2.setTextColor(Color.parseColor("#ea4130"));
                tv1.setTextColor(Color.parseColor("#8f8f8f"));
                tv3.setTextColor(Color.parseColor("#8f8f8f"));
                rl3.setVisibility(View.GONE);
                rl1.setVisibility(View.GONE);
            }
        });
        tv3= (TextView) findViewById(R.id.sales);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    setSaleList();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                rl3.setVisibility(View.VISIBLE);
                tv3.setTextColor(Color.parseColor("#ea4130"));
                tv1.setTextColor(Color.parseColor("#8f8f8f"));
                tv2.setTextColor(Color.parseColor("#8f8f8f"));
                rl1.setVisibility(View.GONE);
                rl2.setVisibility(View.GONE);
            }
        });
    }

    public float howmuch(){

        LayoutInflater inflater=getLayoutInflater();
        View dialog=inflater.inflate(R.layout.takemoney_dialog, (ViewGroup) findViewById(R.id.dialog));
        final EditText howmuch= (EditText) dialog.findViewById(R.id.how_much);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提现");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
        try{

                dbHelper.takemoeny(dbHelper,Float.parseFloat(howmuch.getText().toString()));
            list=dbHelper.select_moneylist(dbHelper);
            adp=new MoneyAdp(MoneyAty.this,R.layout.takemoneyitem,list);
            takeList.setAdapter(adp);

            if (!howmuch.getText().toString().equals("")){
                dbHelper.create_td(dbHelper,Float.parseFloat(howmuch.getText().toString()),1);
                onStart();
            }

            }catch (Exception e){

}
            }
        });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
        howmuch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
            if (Float.parseFloat(dbHelper.select_with(dbHelper))<Float.parseFloat(howmuch.getText().toString())){
                   howmuch.setText(dbHelper.select_with(dbHelper));
                    tmoneys =Float.parseFloat(dbHelper.select_with(dbHelper));

            }
                }catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try{

                }catch (NullPointerException e){}

            }
        });
        builder.setView(dialog);
        builder.setIcon(R.drawable.takemoney);
        builder.show();
        return tmoneys;
    }

    @Override
    protected void onStart() {
        money.setText(dbHelper.select_with(dbHelper));
        list=dbHelper.select_moneylist(dbHelper);
        adp=new MoneyAdp(MoneyAty.this,R.layout.takemoneyitem,list);
        takeList.setAdapter(adp);
        if (dbHelper.select_with(dbHelper).equals("0")){
            takemoneybutton.setText("您账户余额为0，快去下单吧");
        }
        if (dbHelper.select_power(dbHelper,"rm_takemoney")==0){
            takemoneybutton.setText("我的销售额");
            takemoneybutton.setClickable(false);
        }
        if (dbHelper.select_power(dbHelper,"rm_checkjorn")==0){
            tv3.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
        }
        super.onStart();
    }



    private BarData getBarData(int count, float range) {
        ArrayList<String> xValues = new ArrayList<String>();
        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        sales=dbHelper.sales_list(dbHelper);
        Log.d(TAG, "onCreate: ----------获取到sales"+sales.size());
        list=dbHelper.select_moneylist(dbHelper);
        Log.d(TAG, "onCreate: ----------进入for循环");
            for ( HashMap<Object,Object> moneyitem1:sales){
                Log.d(TAG, "onCreate: ----------进入for循环");
               xValues.add(String.valueOf(moneyitem1.get("name")));
                Log.d(TAG, "onCreate: "+moneyitem1.get("name"));
                yValues.add(new BarEntry(Integer.parseInt(moneyitem1.get("sales").toString()),sales.indexOf(moneyitem1)));

            }
      //  xValues.add(String.valueOf("校长"));
      //  xValues.add(String.valueOf("laoshi"));
      //  xValues.add(String.valueOf("jiazhang"));

      //  yValues.add(new BarEntry(20f,1));
      //  yValues.add(new BarEntry(10f,0));
      //  yValues.add(new BarEntry(30f,2));






        // y轴的数据集合
        BarDataSet barDataSet = new BarDataSet(yValues, "测试饼状图");

        barDataSet.setColor(Color.rgb(114, 188, 223));

        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
        barDataSets.add(barDataSet); // add the datasets

        BarData barData = new BarData(xValues, barDataSets);

        return barData;
    }
    private void showBarChart(BarChart barChart, BarData barData) {


        barChart.setDescription("123123");// 数据描述

        // 如果没有数据的时候，会显示这个，类似ListView的EmptyView
        barChart.setNoDataTextDescription("You need to provide data for the chart.");

        barChart.setDrawGridBackground(false); // 是否显示表格颜色


        barChart.setTouchEnabled(true); // 设置是否可以触摸

        barChart.setDragEnabled(true);// 是否可以拖拽
        barChart.setScaleEnabled(true);// 是否可以缩放

        barChart.setPinchZoom(false);//

//      barChart.setBackgroundColor();// 设置背景

        barChart.setDrawBarShadow(true);

        barChart.setData(barData); // 设置数据

        Legend mLegend = barChart.getLegend(); // 设置比例图标示

        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.BLACK);// 颜色

//      X轴设定
//      XAxis xAxis = barChart.getXAxis();
//      xAxis.setPosition(XAxisPosition.BOTTOM);

        barChart.animateX(2500); // 立即执行的动画,x轴
    }
    public void setPie(){
        typechart.setHoleRadius(60f);//半径
        typechart.setTransparentCircleRadius(64f);
        typechart.setDrawCenterText(true);
        typechart.setDrawHoleEnabled(true);
        typechart.setRotationAngle(90);
        typechart.setRotationEnabled(true);
        typechart.setUsePercentValues(true);
        typechart.setCenterText("所有订单产品");
        ArrayList<String> date=new ArrayList<String>();
        date.add("手机");
        date.add("电脑");
        date.add("其他");

        ArrayList<Entry> yDate=new ArrayList<Entry>();
        yDate.add(new Entry(dbHelper.select_pie(dbHelper,"手机"),0));
        Log.d(TAG, "setPie:手机销量 "+dbHelper.select_pie(dbHelper,"手机"));
        yDate.add(new Entry(dbHelper.select_pie(dbHelper,"电脑"),1));
        yDate.add(new Entry(dbHelper.select_pie(dbHelper,"其他"),2));
        PieDataSet pieDataSet=new PieDataSet(yDate,"产品销售比例");
        pieDataSet.setSliceSpace(0f);
        ArrayList<Integer> colors=new ArrayList<Integer>();
        colors.add(Color.parseColor("#ea4130"));
        colors.add(Color.rgb(114,188,223));
        colors.add(Color.rgb(255,123,124));
        pieDataSet.setColors(colors);

        DisplayMetrics metrics=getResources().getDisplayMetrics();
        float px=5*(metrics.densityDpi/160f);
        pieDataSet.setSelectionShift(px);
        PieData pieData=new PieData(date,pieDataSet);
        typechart.setData(pieData);
    }
    public  void  setcharts(){

        //	test.setText(cname);

        chart.animateY(1000);
        chart.fitScreen();
        chart.setDescription("");
        chart.setNoDataTextDescription("您还没有订单，快去下单吧");
        ArrayList<String> xValue=new ArrayList<String>();
        Calendar c=Calendar.getInstance();
        int Month=c.get(Calendar.MONTH)+1;
        int day=c.get(Calendar.DAY_OF_MONTH);
        int Year=c.get(Calendar.YEAR);
        if (day-6<0){
            day=31+day;
        }
        day=day-6;
        for (int i=0;i<=7;i++){

            xValue.add(""+day+"日");
            day++;
            if (Month==1||Month==3||Month==5||Month==7||Month==8||Month==10||Month==12){
                if (day==32){
                    day=1;
                }
            }else if (Month==4||Month==6||Month==9||Month==11){
                if (day==31){
                    day=1;
                }

            }else if (Year%400==0||Year%4==0&&Year%100!=0){
                if (Month==2){
                    if (day==30){
                        day=1;
                    }else if (day==29){
                        day=1;
                    }
                }
            }
        }

        ArrayList<String> a=dbHelper.selecthome(dbHelper);
        int b,d,e,f,g,h,i;
        b= Integer.parseInt(a.get(0));
        d= Integer.parseInt(a.get(1));
        e= Integer.parseInt(a.get(2));
        f= Integer.parseInt(a.get(3));
        g= Integer.parseInt(a.get(4));
        h= Integer.parseInt(a.get(5));
        i= Integer.parseInt(a.get(6));


        ArrayList<Entry> yValue=new ArrayList<Entry>();
        yValue.add(new Entry(i,0));
        yValue.add(new Entry(h, 1));
        yValue.add(new Entry(g, 2));
        yValue.add(new Entry(f, 3));
        yValue.add(new Entry(e, 4));
        yValue.add(new Entry(d, 5));


        yValue.add(new Entry(b, 6));

        LineDataSet lineDataSet=new LineDataSet(yValue,Month+"月销量");

        lineDataSet.setLineWidth(1.75f);
        lineDataSet.setCircleSize(3f);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setHighLightColor(Color.WHITE);
        ArrayList<LineDataSet> lineDataSets=new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet);

        LineData lineData=new LineData(xValue,lineDataSets);
        chart.setDescriptionTextSize(16f);
        chart.setData(lineData);

    }
    public  void  setmonthcharts(){

        //	test.setText(cname);

        chart.animateY(1000);
        chart.fitScreen();
        chart.setDescription("");
        chart.setNoDataTextDescription("您还没有订单，快去下单吧");
        ArrayList<String> xValue=new ArrayList<String>();
        Calendar c=Calendar.getInstance();
        int Month=c.get(Calendar.MONTH)+1;
        int day=c.get(Calendar.DAY_OF_MONTH);
        int year= c.get(Calendar.YEAR);
        boolean s=false;
        if (Month-6<0){
            Month=12+Month;
               s=true;
        }
        Month=Month-6;
        for (int i=0;i<=6;i++){
        if (s){
            year--;
        }
            s=false;
            xValue.add(year+"年"+Month+"月");
            Month++;
            if (Month>12){
                Month=1;
                year++;
            }
            if (Month<1){
                Month=12;

            }
        }

        ArrayList<String> a=dbHelper.selectmonth(dbHelper);
        int b,d,e,f,g,h,i;
        b= Integer.parseInt(a.get(0));
        d= Integer.parseInt(a.get(1));
        e= Integer.parseInt(a.get(2));
        f= Integer.parseInt(a.get(3));
        g= Integer.parseInt(a.get(4));
        h= Integer.parseInt(a.get(5));
        i= Integer.parseInt(a.get(6));


        ArrayList<Entry> yValue=new ArrayList<Entry>();
        yValue.add(new Entry(i,0));
        yValue.add(new Entry(h, 1));
        yValue.add(new Entry(g, 2));
        yValue.add(new Entry(f, 3));
        yValue.add(new Entry(e, 4));
        yValue.add(new Entry(d, 5));


        yValue.add(new Entry(b, 6));

        LineDataSet lineDataSet=new LineDataSet(yValue,Month+"月销量");

        lineDataSet.setLineWidth(1.75f);
        lineDataSet.setCircleSize(3f);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setHighLightColor(Color.WHITE);
        ArrayList<LineDataSet> lineDataSets=new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet);

        LineData lineData=new LineData(xValue,lineDataSets);
        chart.setDescriptionTextSize(16f);
        chart.setData(lineData);

    }
public void setSaleList() throws ParseException {
    withlists= (ListView) findViewById(R.id.with_list);
    withlist=new ArrayList<With>();
    withlist=dbHelper.select_withlist(dbHelper);
    saleadp=new WithAdapter(MoneyAty.this,R.layout.with_item,withlist);
    withlists.setAdapter(saleadp);
    withlists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            With item=withlist.get(i);
            String time=item.getTime();
            Intent intent=new Intent(MoneyAty.this,WithListAty.class);
            intent.putExtra("time",time);
            startActivity(intent);
        }
    });
}


*/
        }