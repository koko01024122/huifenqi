package com.example.cxhll.huifenq.dao;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.cxhll.huifenq.Activitys.CreateUser;
import com.example.cxhll.huifenq.Activitys.NewOrderAty;
import com.example.cxhll.huifenq.Login;
import com.example.cxhll.huifenq.item.AddressItem;
import com.example.cxhll.huifenq.item.AnnoItem;
import com.example.cxhll.huifenq.item.ChoseOverdueIem;
import com.example.cxhll.huifenq.item.CustoItem;
import com.example.cxhll.huifenq.item.ExpressItem;
import com.example.cxhll.huifenq.item.Express_CommItem;
import com.example.cxhll.huifenq.item.Message;
import com.example.cxhll.huifenq.item.Moneys;
import com.example.cxhll.huifenq.item.OrderListItem;
import com.example.cxhll.huifenq.item.RoleItem;
import com.example.cxhll.huifenq.item.ShowOverdueIem;
import com.example.cxhll.huifenq.item.SuppItem;
import com.example.cxhll.huifenq.item.TipsItem;
import com.example.cxhll.huifenq.item.UserItem;
import com.example.cxhll.huifenq.item.WaresItem;
import com.example.cxhll.huifenq.item.With;
import com.example.cxhll.huifenq.tools.Icb;
import com.example.cxhll.huifenq.tools.TimeHelper;

import java.lang.reflect.Array;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * Created by CXHLL on 2016/11/15.
 */

public class HuifqDbHelper extends SQLiteOpenHelper {
    int a;
    int b;
    private String TAG = "Lodingactivity.class";
    private static final String comm_ten = "create table comm_ten(id integer primary key autoincrement, " +
            "numeral text, " +
            "trian text, " +
            "bc text, " +
            "user text, " +
            "psd text, " +
            "commname text )";
    String SQL = "insert into comm_ten (numeral,trian,bc,user,psd,commname) VALUES ('1'，'1','1','18266668888','123456','北京榆钱投资管理有限公司') ;";
    //String

    private static final String CREATE_KEHU = "create table kehu(id integer primary key autoincrement, " +
            "edu text, " +
            "name text, " +
            "phonenum text);";
    private static final String CREATE_STARTIME = "create table startime(id integer primary key autoincrement, " +
            "yiyi text, " +
            "user text, " +
            "u_id text);";
    private static final String CREATE_WITH = "create table money(id integer primary key autoincrement, " +
            "u_id text, " +//用户id
            "moneys text, " +
            "cunzai text)"; //余额

    private static final String CREATE_TD = "create table td(id integer primary key autoincrement, " +
            "w_id text, " +
            "takemoneynum text, " +
            "taketime datetime, " +
            "takestage text, " +
            "t_type text)";//0 已发起 1 处理中 2.已到账
    private static final String CREATE_STAGE = "create table stages(id integer primary key autoincrement, " +
            "stage text)";
    private static final String CREATE_LOGIN = "create table login(id integer primary key autoincrement, " +
            "u_id text, " +//其实应该叫f_id,为创建该角色的id
            "usename text, " +
            "psd text, " +
            "state text, " +//0为离线 1为登录
            "usertype text, " +
            "names text, " +
            "role text);";//0为管理员 1为销售经理
    private static final String CREATE_ROLE = "create table role(id integer primary key autoincrement, " +
            "rm_orderm integer, " +
            "rm_neworder1 integer, " +
            "rm_neworder2 integer, " +
            "rm_neworder3 integer, " +
            "rm_checkorder integer, " +
            "rm_checksonorder integer, " +
            "rm_allorder integer," +
            "rm_comm integer, " +
            "rm_add_comm integer, " +
            "rm_edit_comm integer," +
            "rm_money integer, " +
            "rm_takemoney integer, " +
            "rm_checkself integer, " +
            "rm_checksonsale integer, " +
            "rm_checkallsale integer, " +
            "rm_checkjorn integer, " +//报表
            "rm_mess integer, " +
            "rm_checkmess integer, " +
            "rm_reportmess integer, " +
            "rm_system integer, " +
            "rm_rabc integer, " +
            "rm_makeson integer, " +
            "rm_typename text," +
            "rm_mid integer, " +
            "rm_typeid integer);";
    private static final String CREATE_USER = "create table user(u_id integer primary key autoincrement, " +
            "u_username text, " +
            "u_phonenum text, " +
            "u_password text, " +
            "u_type text, " +
            "u_name text, " +
            "u_role text, " +
            "u_fid text);";
    private static final String CREATE_CWARES = "create table cwares(w_id integer primary key autoincrement, " +
            "w_type text, " +
            "w_name text, " +
            "w_price real, " +
            "w_brand text, " +
            "w_model text, " +
            "w_ram text, " +
            "w_disk text, " +
            "w_memory text, " +
            "w_state text, " +
            "w_img blob, " +
            "w_imgurl text);";
    private static final String CREATE_TRAIN = "create table train(t_id integer primary key autoincrement," +
            "t_type integer, " +
            "t_name text, " +
            "t_price real, " +
            "t_state integer, " +
            "t_img blob);";
    private static final String CREATE_ORDERS = "create table orders(o_id integer primary key autoincrement, " +
            " o_ordernum text, " +
            " o_ordername text, " +
            "o_price text, " +
            " o_cname text, " +
            " o_cid text, " +
            "o_content text, " +
            " o_phonenum text, " +
            " o_mid text, " +
            "o_uid text, " +
            " o_stage text, " +
            " o_rstate text, " +
            "o_payday text, " +
            "o_createdata text, " +
            " o_passdata text, " +
            " o_paystage text, " +
            "o_grace text, " +
            " o_gracemoney text, " +
            " o_mopay text, " +
            "o_rate text, " +
            "o_mrate text," +
            " o_paymoney text, " +
            "o_paymet text, " +
            "o_addresstype text, " +
            "o_address text, " +
            "o_address_city text, " +
            "o_address_province text, " +
            "o_address_area text, " +
            "o_address_detil text, " +
            "o_address_name text, " +
            "o_address_phonenum text, " +
            "o_remark text, " +
            "o_time datetime, " +
            "o_waretype text, " +
            "o_pingzheng1 text," +
            "o_pingzheng2 text," +
            "o_pingzheng3 text," +
            "o_pingzheng4 text," +
            "o_pingzheng5 text);";
    private static final String CREATE_MESS = "create table message(m_id integer primary key autoincrement, " +
            "m_onum text, " +
            "m_name text" +//订单号
            "m_type text, " +//订单类型
            "m_ostate text, " +//订单状态
            "m_content text, " +
            "m_mess text, " +
            "m_time datetime, " +
            "m_mess_state text);";//消息状态 0为未读 1为已读
    private static final String CREATE_SUPPLY = "create table supply(s_id integer primary key autoincrement, " +
            "s_mid integer, " +
            "s_cname1 text, " +
            "s_cname2 text, " +
            "s_canme3 text, " +
            "s_cphone1 text, " +
            "s_cphone2 text, " +
            "s_cphone3 text, " +
            "s_crelation1 text, " +
            "s_creltaion2 text, " +
            "s_creltaion3 text, " +
            "s_img1 text, " +
            "s_img2 text, " +
            "s_img3 text, " +
            "s_img4 text, " +
            "s_img5 text, " +
            "s_img6 text, " +
            "s_remarks1 text, " +
            "s_remarks2 text, " +
            "s_remarks3 text);";
    private static final String anno = "create table anno(id integer primary key autoincrement," +
            "a_uid text, " +
            "a_title text, " +
            "a_content text, " +
            "a_time text, " +
            "a_sendu text)";

    private static final String Customer = "create table customer(id integer primary key autoincrement," +
            "c_uid text, " +
            "c_name text, " +
            "c_username text)";
    private static final String express = "create table express(id integer primary key autoincrement, " +
            "e_uid text, " +
            "e_exnum text, " +
            "e_cuname text, " +
            "e_coname text, " +
            "e_state text, " +
            "e_time text) ";
    private static final String express_comm = "create table expresscomm(id integer primary key autoincrement, " +
            "e_name text, " +
            "e_code text, " +
            "e_word text)";
    private static final String express_orders = "create table express_orders(id integer primary key autoincrement, " +
            "e_uid text, " +
            "e_paytype text, " +
            "e_exptype text, " +
            "e_receiver_name text, " +
            "e_receiver_tel text, " +
            "e_provice_name text, " +
            "e_city_name text, " +
            "e_exparea_name text, " +
            "e_address text, " +
            "e_sender_name text, " +
            "e_sender_tel text, " +
            "e_sender_provice text, " +
            "e_sender_city text, " +
            "e_sender_exparea text, " +
            "e_sender_address text, " +
            "e_goods_name text, " +
            "e_time text)";
    private static final String tipslist = "create table tipslist(id integer primary key autoincrement, " +
            "t_content text, " +
            "t_url text)";

    private static final String user_address = "create table user_address(id integer primary key autoincrement , " +
            "u_uid text, " +
            "u_name text, " +
            "u_tel text, " +
            "u_province text, " +
            "u_city text, " +
            "u_area text, " +
            "u_detil text," +
            "u_default text)";//1为默认，0为非默认
    private static final String overdue = "create table overdue (id integer primary key autoincrement, " +
            "o_uid text, " +
            "o_cid text, " +
            "o_payday text, " +
            "o_charge text, " +
            "o_state text, " +
            "o_stage text, " +
            "o_name text)";
    String insert_overdue1 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('1','noone','0','342.12','赵铁柱')";
    String insert_overdue2 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('3','noone','0','31.12','王小花')";
    String insert_overdue3 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('3','noone','0','142.12','李根生')";
    String insert_overdue4 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('4','noone','0','642.12','刘铁栓')";
    String insert_overdue5 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('5','noone','0','342.12','甄富贵')";
    String insert_overdue6 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('7','noone','0','442.12','贾富贵')";
    String insert_overdue7 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('9','noone','0','42.12','牛成才')";
    String insert_overdue8 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('12','noone','0','348.12','王正义')";
    String insert_overdue9 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('1','noone','0','321.12','赵富强')";
    String insert_overdue10 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('12','noone','0','500.12','钱文明')";
    String insert_overdue11 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('3','noone','0','400.12','孙民主')";
    String insert_overdue12 = "insert into overdue (o_payday,o_charge,o_state,o_stage,o_name) VALUES('21','noone','0','201.12','李和谐')";

    String insert_tips = "insert into tipslist (t_content,t_url) VALUES('惠分期微信公众号还款流程','http://mp.weixin.qq.com/s/lRGCOUHlLmtcS9NkjtQ6Zw') ";
    String insert_tip2 = "insert into tipslist (t_content,t_url) VALUES('过单秘籍','http://www.jd.com') ";
    String insert3 =
            " insert into tipslist (t_content,t_url) VALUES('客户开发指南','http://www.taobao.com')";
    String insert_order_test = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','1','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";

    String insert_order_test1 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','5','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";
    String insert_order_test2 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','2','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";
    String insert_order_test3 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','3','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";
    String insert_order_test4 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','4','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";
    String insert_order_test5 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','6','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";
    String insert_order_test6 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','0','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";
    String insert_order_test7 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','3','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";
    String insert_order_test8 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','1','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";
    String insert_order_test9 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','4','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";
    String insert_order_test10 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','6','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";
    String insert_order_test11 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','9','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";
    String insert_order_test12 = "insert into orders (o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype) values ('18202770371','手机','600','9','','还 款 日 期：','0','湖北省','武汉市','武昌区','中南路','','0','203.01','裴磊磊','2017-02-20 15:39:52  ','2','裴磊磊','18202770371','3')";

    String insert_mess_test1 = "insert into message (m_content,m_onum,m_ostate,m_name,m_mess_state,m_time) VALUES('站内信','1','2','裴磊磊','1','2017-02-20 15:55:34')";

    String insert_mess_test2 = "insert into message (m_content,m_onum,m_ostate,m_name,m_mess_state,m_time) VALUES('站内信','1','3','裴磊磊','0','2017-02-20 15:55:34')";
    String insert_mess_test3 = "insert into message (m_content,m_onum,m_ostate,m_name,m_mess_state,m_time) VALUES('站内信','1','1','裴磊磊','0','2017-02-20 15:55:34')";
    String insert_mess_test4 = "insert into message (m_content,m_onum,m_ostate,m_name,m_mess_state,m_time) VALUES('站内信','1','0','裴磊磊','1','2017-02-20 15:55:34')";


    public HuifqDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    String SF = "insert into expresscomm (e_name,e_code, e_word) VALUES('顺丰速运','SF','在线预约寄件，一小时上门取件')";
    String YT = "insert into expresscomm (e_name,e_code, e_word) VALUES('圆通快递','YT','一键下单，快速取件')";
    String HT = "insert into expresscomm (e_name,e_code, e_word) VALUES('百事快递','HT','快速取件，精准送达，扫码支付')";
    String ZT = "insert into expresscomm (e_name,e_code, e_word) VALUES('中通快递','ZT','用我们的产品，体验极速服务')";
    String YD = "insert into expresscomm (e_name,e_code, e_word) VALUES('韵达快递','YD','快速下单，极速取件，为您的生活提供便利')";
    String EMS = "insert into expresscomm (e_name,e_code, e_word) VALUES('邮政EMS','EMS','大提速，更实惠！快速上门，扫运单条码支付')";
    String DB = "insert into expresscomm (e_name,e_code, e_word) VALUES('德邦物流','DB','专注你的托付')";
    String YS = "insert into expresscomm (e_name,e_code, e_word) VALUES('优速物流','YS','一路呵护所托 ')";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CWARES);
        sqLiteDatabase.execSQL(CREATE_MESS);
        sqLiteDatabase.execSQL(CREATE_ORDERS);
        sqLiteDatabase.execSQL(express);
        sqLiteDatabase.execSQL(express_orders);
        sqLiteDatabase.execSQL(CREATE_ROLE);
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_TRAIN);
        sqLiteDatabase.execSQL(CREATE_SUPPLY);
        sqLiteDatabase.execSQL(CREATE_LOGIN);
        sqLiteDatabase.execSQL(CREATE_STARTIME);
        sqLiteDatabase.execSQL(CREATE_KEHU);
        sqLiteDatabase.execSQL(CREATE_WITH);
        sqLiteDatabase.execSQL(CREATE_STAGE);
        sqLiteDatabase.execSQL(CREATE_TD);
        sqLiteDatabase.execSQL(user_address);

        sqLiteDatabase.execSQL(tipslist);
        sqLiteDatabase.execSQL(insert_tips);
        sqLiteDatabase.execSQL(insert_tip2);
        sqLiteDatabase.execSQL(insert3);
        sqLiteDatabase.execSQL(insert_order_test);
        sqLiteDatabase.execSQL(insert_order_test1);
        sqLiteDatabase.execSQL(insert_order_test2);
        sqLiteDatabase.execSQL(insert_order_test3);
        sqLiteDatabase.execSQL(insert_order_test4);
        sqLiteDatabase.execSQL(insert_order_test5);
        sqLiteDatabase.execSQL(insert_order_test6);
        sqLiteDatabase.execSQL(insert_order_test7);
        sqLiteDatabase.execSQL(insert_order_test8);
        sqLiteDatabase.execSQL(insert_order_test9);
        sqLiteDatabase.execSQL(insert_order_test10);
        sqLiteDatabase.execSQL(insert_order_test11);
        sqLiteDatabase.execSQL(insert_order_test12);

        sqLiteDatabase.execSQL(insert_mess_test1);
        sqLiteDatabase.execSQL(insert_mess_test2);
        sqLiteDatabase.execSQL(insert_mess_test3);
        sqLiteDatabase.execSQL(insert_mess_test4);
        Log.d(TAG, "onCreate: created login");
        sqLiteDatabase.execSQL(anno);
        sqLiteDatabase.execSQL(Customer);
        sqLiteDatabase.execSQL(express_comm);

        sqLiteDatabase.execSQL(SF);
        sqLiteDatabase.execSQL(YT);
        sqLiteDatabase.execSQL(HT);
        sqLiteDatabase.execSQL(ZT);
        sqLiteDatabase.execSQL(YD);
        sqLiteDatabase.execSQL(EMS);
        sqLiteDatabase.execSQL(DB);
        sqLiteDatabase.execSQL(YS);


        sqLiteDatabase.execSQL(overdue);
        sqLiteDatabase.execSQL(insert_overdue1);
        sqLiteDatabase.execSQL(insert_overdue2);
        sqLiteDatabase.execSQL(insert_overdue3);
        sqLiteDatabase.execSQL(insert_overdue4);
        sqLiteDatabase.execSQL(insert_overdue5);
        sqLiteDatabase.execSQL(insert_overdue6);
        sqLiteDatabase.execSQL(insert_overdue7);
        sqLiteDatabase.execSQL(insert_overdue8);
        sqLiteDatabase.execSQL(insert_overdue9);
        sqLiteDatabase.execSQL(insert_overdue10);
        sqLiteDatabase.execSQL(insert_overdue11);
        sqLiteDatabase.execSQL(insert_overdue12);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    public void update_overdue(HuifqDbHelper dbHelper, String name, String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "update overdue set o_charge='" + name + "' where id='" + id + "'";
        Log.d(TAG, "update_overdue: " + SQL);
        db.execSQL(SQL);
    }

    public ArrayList<ShowOverdueIem> selectShowOverList(HuifqDbHelper dbHelper) {
        ArrayList<ShowOverdueIem> list = new ArrayList<ShowOverdueIem>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        //"select o_name,o_payday,o_charge,id,o_state,o_stage from overdue where o_charge='"+"'



        /*
           "o_payday text, " +
            "o_charge text, " +
            "o_state text, " +
            "o_stage text)";

         */

        String SQL = "select o_name,o_payday,o_charge,id,o_state,o_stage from overdue";
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            ShowOverdueIem item = new ShowOverdueIem();
            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setName(c.getString(0));
                item.setTime(c.getString(1));
                item.setCharger(c.getString(2));
                item.setId(c.getString(3));
            }
            list.add(item);
        }
        c.close();
        c = db.rawQuery("select id,names,usertype,usename from login where u_id ='" + selectId(dbHelper) + "'", null);
        while (c.moveToNext()) {
            Cursor d = db.rawQuery("select o_name,o_payday,o_charge,id,o_state,o_stage from overdue where o_charge='" + c.getString(2) + "'", null);
            while (d.moveToNext()) {
                ShowOverdueIem item = new ShowOverdueIem();
                for (int i = 0; i < d.getColumnCount(); i++) {
                    item.setName(d.getString(0));
                    item.setTime(d.getString(1));
                    item.setCharger(d.getString(2));
                    item.setId(d.getString(3));
                }
                list.add(item);
            }
            d.close();
        }
        c.close();
        return list;
    }


    public ArrayList<ChoseOverdueIem> selectChoseOverList(HuifqDbHelper dbHelper) {
        ArrayList<ChoseOverdueIem> list = new ArrayList<ChoseOverdueIem>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        /*
           "o_payday text, " +
            "o_charge text, " +
            "o_state text, " +
            "o_stage text)";

         */

        String SQL = "select o_name,o_payday,o_charge,id,o_state,o_stage from overdue";
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            ChoseOverdueIem item = new ChoseOverdueIem();
            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setName(c.getString(0));
                item.setTime(c.getString(1));

                item.setId(c.getString(3));
            }
            list.add(item);
        }

        return list;
    }


    public void insert_address(HuifqDbHelper dbHelper, String name, String tel, String province, String city, String area, String defaults, String detil) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String update = "update user_address set u_default='0' where u_uid='" + selectId(dbHelper) + "'";
        String insert = "insert into user_address (u_name,u_tel,u_province,u_city,u_area,u_uid,u_default,u_detil) VALUES ('" + name + "','" + tel + "','" + province + "','" + city + "','" + area + "','" + selectId(dbHelper) + "','" + defaults + "','" + detil + "') ";
        if (defaults.equals("1")) {
            try {
                db.execSQL(update);
            } catch (Exception e) {
            }
        }
        db.execSQL(insert);
    }

    public void update_address(HuifqDbHelper dbHelper, String id, String defaults) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (defaults.equals("1")) {
            String update = "update user_address set u_default='0' where u_uid='" + selectId(dbHelper) + "'";
            String update1 = "update user_address set u_default='1' where u_uid='" + selectId(dbHelper) + "' and id='" + id + "'";
            db.execSQL(update);
            db.execSQL(update1);
            Log.d(TAG, "update_address: " + update);
            Log.d(TAG, "update_address: " + update1);
        } else if (defaults.equals("0")) {
            String update = "update user_address set u_default='0' where id='" + id + "' ";
            Log.d(TAG, "update_address: " + update);
            db.execSQL(update);
        }


    }

    public void del_address(HuifqDbHelper dbHelper, String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "delete from user_address where id='" + id + "'";
        db.execSQL(sql);
    }

    public ArrayList<AddressItem> select_address(HuifqDbHelper dbHelper) {
        Log.d(TAG, "select_address: 查找");
        ArrayList<AddressItem> list = new ArrayList<AddressItem>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "select * from user_address where u_uid='" + selectId(dbHelper) + "'";
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            /**
             * "u_name text, " +
             "u_tel text, " +
             "u_province text, " +
             "u_city text, " +
             "u_area text, " +
             "u_detil text," +
             "u_default text
             */
            AddressItem item = new AddressItem();
            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setId(c.getString(0));
                item.setName(c.getString(2));
                item.setTel(c.getString(3));
                item.setProvince(c.getString(4));
                item.setCity(c.getString(5));
                item.setArea(c.getString(6));
                item.setDetil(c.getString(7));
                item.setDefaults(c.getString(8));
            }
            list.add(item);
        }
        return list;
    }

    public AddressItem select_address_default(HuifqDbHelper dbHelper) {
        Log.d(TAG, "select_address: 查找");
        AddressItem item = new AddressItem();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "select * from user_address where u_uid='" + selectId(dbHelper) + "' and u_default = '1'";
        Cursor c = db.rawQuery(SQL, null);
        Log.d(TAG, "select_address_default: " + SQL);
        while (c.moveToNext()) {


            Log.d(TAG, "select_address_default: 进入while？");
            item.setId(c.getString(0));
            item.setName(c.getString(2));
            item.setTel(c.getString(3));
            item.setProvince(c.getString(4));
            item.setCity(c.getString(5));
            item.setArea(c.getString(6));
            item.setDetil(c.getString(7));
            item.setDefaults(c.getString(8));
            Log.d(TAG, "select_address_default: " + item.getName());


        }

        if (!item.getName().equals("")) {
            return item;
        } else {
            return null;
        }
    }

    public void updatePingzheng(HuifqDbHelper dbHelper, ArrayList<String> urls, String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int j = 1;
        for (int i = 0; i < urls.size(); i++) {
            String url = urls.get(i);
            j = j + i;
            if (j > 5) {
                break;
            }
            String SQL = "update orders set o_pingzheng" + j + " = '" + url + "' where o_id='" + id + "'";
            db.execSQL(SQL);
        }
    }

    public ArrayList<String> select_pingzheng(HuifqDbHelper dbHelper, String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<String> list = new ArrayList<>();
        String SQL = "select o_pingzheng1,o_pingzheng2,o_pingzheng3,o_pingzheng4,o_pingzheng5 from orders where o_id='" + id + "'";
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            list.add(c.getString(0));
            list.add(c.getString(1));
            list.add(c.getString(2));
            list.add(c.getString(3));
            list.add(c.getString(4));
        }
        c.close();

        return list;
    }

    public ArrayList<TipsItem> select_tips(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "select * from tipslist";
        ArrayList<TipsItem> list = new ArrayList<TipsItem>();
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {

            TipsItem item = new TipsItem();
            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setId(c.getString(0));
                item.setTitle(c.getString(1));
                item.setUrl(c.getString(2));
            }
            list.add(item);
        }

        return list;
    }

    /**
     * 获取快递公司信息
     */
    public ArrayList<Express_CommItem> select_expresscomm(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Express_CommItem> list = new ArrayList<Express_CommItem>();
        String SQL = "select * from expresscomm";
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            Express_CommItem item = new Express_CommItem();
            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setShipperName(c.getString(1));
                item.setShipperCode(c.getString(2));
                item.setShipperWord(c.getString(3));
            }
            list.add(item);
        }
        return list;
    }

    /**
     * "e_paytype text, " +
     * "e_exptype text, " +
     * "e_receiver_name text, " +
     * "e_receiver_tel text, " +
     * "e_provice_name text, " +
     * "e_city_name text, " +
     * "e_exparea_name text, " +
     * "e_address text, " +
     * "e_sender_name text, " +
     * "e_sender_tel text, " +
     * "e_sender_provice text, " +
     * "e_sender_city text, " +
     * "e_sender_exparea text, " +
     * "e_sender_address text, " +
     * "e_goods_name text,
     * )";
     *
     * @param dbHelper
     */
    public void create_express_orders(HuifqDbHelper dbHelper, String e_paytype, String e_exptype, String e_receiver_name, String e_receiver_tel, String e_provice_name, String e_city_name, String e_exparea_name, String e_address, String e_sender_name, String e_sender_tel, String e_sender_provice, String e_sender_city, String e_sender_exparea, String e_sender_address, String e_good_name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String KEY = "insert into express_orders (e_uid,e_paytype,e_exptype,e_receiver_name,e_receiver_tel,e_provice_name, e_city_name,e_exparea_name" +
                ",e_address,e_sender_name,e_sender_tel,e_sender_provice,e_sender_city,e_sender_exparea,e_sender_address,e_good_name,e_time)";
        String VALUE = "VALUES('" + selectId(dbHelper) + "','" + e_paytype + "','" + e_exptype + "','" + e_receiver_name + "','" + e_receiver_tel + "','" + e_provice_name + "','" + e_city_name + "','" + e_exparea_name + "','" + e_address + "','" + e_sender_name + "','" + e_sender_tel + "','" + e_sender_provice + "','" + e_sender_city + "','" + e_sender_exparea + "','" + e_sender_address + "','" + e_good_name + "','" + getTime() + "')";
        db.execSQL(KEY + VALUE);
    }


    /**
     * 创建快递单号仓库
     */
    public void create_express(HuifqDbHelper dbHelper, String e_exnum, String e_cuname, String e_coname, String e_state) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String KEY = "insert into express (e_uid,e_exnum,e_cuname,e_coname,e_state,e_time)";
        String VALUES = "VALUES('" + selectId(dbHelper) + "','" + e_exnum + "','" + e_cuname + "','" + e_coname + "','" + e_state + "','" + getTime() + "')";
        db.execSQL(KEY + VALUES);
    }

    /**
     * 查询快递
     */
    public String select_express(HuifqDbHelper dbHelper, String e_exnum) {
        String state = "0";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "select e_state from express where e_exnum='" + e_exnum + "'";
        Log.d(TAG, "select_express: " + SQL);
        try {
            Cursor c = db.rawQuery(SQL, null);

            while (c.moveToNext()) {
                state = c.getString(0);
            }
            c.close();
        } catch (NullPointerException e) {
            state = "0";
        }
        return state;
    }

    public ArrayList<ExpressItem> select_expressList(HuifqDbHelper dbHelper, String where) {
        String how = null;
        ArrayList<ExpressItem> list = new ArrayList<ExpressItem>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (where.equals("1")) {
            how = "1' or '2";
        } else if (where.equals("3")) {
            how = "3";
        }
        String SQL = "select * from express where e_uid='" + selectId(dbHelper) + "' and e_state='" + how + "'";
        Log.d(TAG, "select_express: " + SQL);
        try {
            Cursor c = db.rawQuery(SQL, null);
            Log.d(TAG, "select_expressList: " + c.getColumnCount());
            while (c.moveToNext()) {
                ExpressItem item = new ExpressItem();
                //	"e_uid text, " +
                //"e_exnum text, " +
                //		"e_cuname text, " +
                //	"e_coname text, " +
                //	"e_state text)";
                for (int i = 0; i < c.getColumnCount(); i++) {
                    item.setEx_num(c.getString(2));
                    item.setCu_name(c.getString(3));
                    item.setCo_name(c.getString(4));
                    item.setState(c.getString(5));
                    item.setTime(c.getString(6));
                }
                list.add(item);
            }
            c.close();
        } catch (NullPointerException e) {

        }
        return list;
    }

    /**
     * 更新物流信息
     */
    public void upDate_express(HuifqDbHelper dbHelper, String e_state, String e_exnum) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "update express set e_state ='" + e_state + "'where e_exnum='" + e_exnum + "'";
        db.execSQL(SQL);

    }

    /**
     * 创建公告
     */
    public void create_anno(HuifqDbHelper dbHelper, String title, String content) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        /**
         * 		"a_uid text, " +
         "a_title text, " +
         "a_content text, " +
         "a_time text, " +
         "a_sendu text)";

         */
        String KEYSQL = "insert into anno (a_uid,a_title,a_content,a_time,a_sendu)";
        String VALUESQL = " VALUES('" + selectId(dbHelper) + "','" + title + "','" + content + "','" + getTime() + "','" + starUser(dbHelper) + "')";
        db.execSQL(KEYSQL + VALUESQL);

    }

    public ArrayList<AnnoItem> select_Anno(HuifqDbHelper dbHelper) {
        ArrayList<AnnoItem> list = new ArrayList<AnnoItem>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "select * from anno where a_uid='" + selectId(dbHelper) + "'";
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            AnnoItem item = new AnnoItem();
            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setId(c.getString(0));
                item.setSendu(c.getString(5));
                item.setTitle(c.getString(2));
                item.setContent(c.getString(3));
                item.setTime(c.getString(4));


            }
            list.add(item);
        }

        return list;
    }

    public String getTime() {
        TimeHelper timeHelper = new TimeHelper();

        return timeHelper.getTime();
    }

    public void cReate_username(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //创建一个管理员角色
        values.put("rm_typename", "system");
        values.put("rm_mid", 1);
        values.put("rm_typeid", 1);
        values.put("rm_orderm", 1);
        values.put("rm_neworder1", 1);
        values.put("rm_neworder2", 0);
        values.put("rm_neworder3", 0);
        values.put("rm_checkorder", 1);
        values.put("rm_checksonorder", 1);
        values.put("rm_allorder", 0);
        values.put("rm_comm", 1);
        values.put("rm_add_comm", 1);
        values.put("rm_edit_comm", 1);
        values.put("rm_money", 1);
        values.put("rm_takemoney", 0);
        values.put("rm_checkself", 1);
        values.put("rm_checksonsale", 1);
        values.put("rm_checkallsale", 1);
        values.put("rm_checkjorn", 1);
        values.put("rm_mess", 1);
        values.put("rm_checkmess", 1);
        values.put("rm_reportmess", 1);
        values.put("rm_system", 1);
        values.put("rm_rabc", 1);
        values.put("rm_makeson", 1);
        db.insert("role", null, values);

    }

    public void create_user(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //插入管理员信息
        values.put("usename", "admin");
        values.put("psd", "huifenqi");

        values.put("names", "管理员");
        values.put("usertype", "0");
        db.insert("login", null, values);
        values.clear();
        //插入销售经理信息
        values.put("usename", "18202770371");
        values.put("psd", "7862280");
        values.put("names", "裴磊磊");

        values.put("usertype", "1");
        db.insert("login", null, values);
        values.clear();

    }

    public String star(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String c = "3";
        try {
            Cursor cursor = db.rawQuery("select yiyi from startime", null);
            while (cursor.moveToNext()) {
                c = cursor.getString(0);

                Log.d(TAG, "startime: " + c);

            }
            cursor.close();

            return c;
        } catch (NullPointerException e) {
            return "3";
        }
    }

    public void updateStar(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String c = "3";

        db.execSQL("update startime set yiyi='0' ");

    }


    public String starUser(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select user from startime", null);
        Log.d(TAG, "star: " + cursor.getColumnCount());
        String c = "";
        try {
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    c = cursor.getString(0);

                    Log.d(TAG, "star: " + c);
                }
            }
            cursor.close();

            return c;
        } catch (NullPointerException e) {
            return "NULL";
        }
    }

    public boolean login(HuifqDbHelper dbHelper, String usename, String psd) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String thepsd = null;
        String u_id = null;
        Cursor c = db.rawQuery("select psd from login where usename='" + usename + "';", null);
        try {

            while (c.moveToNext()) {
                thepsd = c.getString(0);
                Log.d(TAG, "login: 密码是" + thepsd);
            }
            c.close();
        } catch (NullPointerException e) {

        }
        try {
            if (thepsd.equals(psd)) {
                Cursor f = db.rawQuery("select names,id from login where usename='" + usename + "';", null);
                String name = "";
                while (f.moveToNext()) {
                    name = f.getString(0);
                    Log.d(TAG, "login: name是" + name);
                    u_id = f.getString(1);

                }
                f.close();
                Log.d(TAG, "login: " + thepsd);
                Log.d(TAG, "login: " + psd);

                //db.execSQL("update startime set yiyi='1',user='"+name+"',u_id='"+u_id+"'");
                db.execSQL("update startime set yiyi='1',user='" + name + "',u_id='" + u_id + "'");

                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }


    }

    public void logingout(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("yiyi", "0");
        values.put("u_id", "null");
        db.update("startime", values, null, null);
        values.clear();
    }

    public void onetime(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("insert into startime (yiyi,u_id) VALUES ('3','null')");


    }

    public void create_kehu(HuifqDbHelper dbHelper) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phonenum", "18202770371");
        values.put("name", "裴磊磊");
        values.put("edu", "8000");
        db.insert("kehu", null, values);
        values.clear();

        values.put("phonenum", "18202770372");
        values.put("name", "陈祥");
        values.put("edu", "8000");
        db.insert("kehu", null, values);
        values.clear();

        values.put("phonenum", "18202770373");
        values.put("edu", "8000");
        values.put("name", "张三");
        db.insert("kehu", null, values);
        values.clear();

        values.put("phonenum", "18202770374");
        values.put("edu", "8000");
        values.put("name", "李四");
        db.insert("kehu", null, values);
        values.clear();

        values.put("phonenum", "18202770375");
        values.put("edu", "8000");
        values.put("name", "马克思");
        db.insert("kehu", null, values);
        values.clear();

        values.put("phonenum", "18202770376");
        values.put("edu", "8000");
        values.put("name", "赵卡尔");
        db.insert("kehu", null, values);
        values.clear();

        values.put("phonenum", "18202770377");
        values.put("edu", "8000");
        values.put("name", "长者");
        db.insert("kehu", null, values);
        values.clear();

    }

    public ArrayList<CustoItem> selectallkehu(HuifqDbHelper dbHelper) {
        ArrayList<CustoItem> custolist = new ArrayList<CustoItem>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "select * from kehu ";
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            CustoItem item = new CustoItem();
            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setId(c.getString(0));
                item.setName(c.getString(2));
                item.setPhonenum(c.getString(3));

            }
            custolist.add(item);
        }
        return custolist;
    }

    public String select_edu(HuifqDbHelper dbHelper, String phonenum) {
        String edu = "0";
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor c = db.rawQuery("select edu from kehu where phonenum='" + phonenum + "'", null);
            while (c.moveToNext()) {
                edu = c.getString(0);
                Log.d(TAG, "onTextChanged: 18202770371的额度为" + edu);
            }
            c.close();

            return edu;

        } catch (NullPointerException e) {


            edu = "00000";
            return edu;

        } catch (Exception e) {

            edu = "00000";
            return edu;
        }
    }

    public String select_name(HuifqDbHelper dbHelper, String phonenum) {
        String name = "";
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor c = db.rawQuery("select name from kehu where phonenum='" + phonenum + "'", null);
            while (c.moveToNext()) {
                name = c.getString(0);
                Log.d(TAG, "select_name: ");
            }
            c.close();

            return name;

        } catch (NullPointerException e) {
            ArrayList<String> edu0 = new ArrayList<String>();

            edu0.add("");
            return name;

        } catch (Exception e) {


            return name;
        }
    }

    public void creat_order(HuifqDbHelper dbHelper, String phonenum, String tradename, String trade_price, String stagenum, String paymet, String payday, String addresstype, String address_province, String address_city, String address_area, String address_detile, String remark, String paystage, String mopay, String name, String address_anme, String address_phonenum, String o_waretype) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss  ");
        String date = time.format(new Date());
        String uid = selectId(dbHelper);
        Log.d(TAG, "creat_order: date" + date);
        String key = "(o_phonenum,o_ordername,o_price,o_stage,o_paymet,o_payday,o_addresstype,o_address_province,o_address_city,o_address_area,o_address_detil,o_remark,o_paystage,o_mopay,o_cname,o_time,o_uid,o_address_name,o_address_phonenum,o_waretype)";
        String value = " values ('" + phonenum + "','" + tradename + "','" + trade_price + "','" + stagenum + "','" + paymet + "','" + payday + "','" + addresstype + "','" + address_province + "','" + address_city + "','" + address_area + "','" + address_detile + "','" + remark + "','" + paystage + "','" + mopay + "','" + name + "','" + date + "','" + uid + "','" + address_anme + "','" + address_phonenum + "','" + o_waretype + "')";

        Log.d(TAG, "creat_order: 创建订单的语句为" + key + value);
        db.execSQL("insert into orders " + key + value);
    }

    public void pass(HuifqDbHelper dbHelper, String id, String stage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String update = "update orders set o_stage='" + stage + "' where o_id= '" + id + "';";
        Log.d(TAG, "pass: stage=" + stage);
        db.execSQL(update);
    }

    public String passstage(HuifqDbHelper dbHelper, String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String a = "";
        Cursor c = db.rawQuery("select o_stage from orders where o_id='" + id + "';", null);
        while (c.moveToNext()) {
            a = String.valueOf(c.getInt(0));

        }
        c.close();


        return a;
    }

    public ArrayList<OrderListItem> select_orders(HuifqDbHelper dbHelper, int power) {
        ArrayList<OrderListItem> orders = new ArrayList<OrderListItem>();//向ITEM中添加所有内容，在点击时传值给下一个活动
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "";
        if (power == 0) {
            SQL = "select o_ordername,o_price,o_paystage,o_mopay,o_address_province,o_phonenum,o_id ,o_stage,o_content,o_cname,o_address_name,o_address_phonenum,o_time,o_address_city,o_address_area from orders where o_uid ='" + selectId(dbHelper) + "' order by o_id desc";
        } else if (power == 1) {
            SQL = "select o_ordername,o_price,o_paystage,o_mopay,o_address_province,o_phonenum,o_id ,o_stage,o_content,o_cname,o_address_name,o_address_phonenum,o_time,o_address_city,o_address_area from orders order by o_id desc";
        }

        Cursor c = db.rawQuery(SQL, null);

        int a = c.getColumnCount();
        while (c.moveToNext()) {

            OrderListItem od = new OrderListItem();
            for (int i = 0; i < a; i++) {
                od.setName(c.getString(9));
                od.setTrade_name(c.getString(0));
                od.setPrice(c.getString(1));
                od.setPaystage(c.getString(2));
                od.setMopay(c.getString(3));
                od.setAddress_province(c.getString(4));
                od.setPhonenum(c.getString(5));
                od.setId(c.getInt(6));
                od.setStage(c.getString(7));
                od.setContent(c.getString(8));
                od.setAddress_name(c.getString(10));
                od.setAddress_phoennum(c.getString(11));
                od.setTime(c.getString(12));
                od.setAddress_city(c.getString(13));
                od.setAddress_area(c.getString(14));
            }
            orders.add(od);
        }
        c.close();


        return orders;
    }


    public ArrayList<OrderListItem> select_orders(HuifqDbHelper dbHelper, String id, String what) {
        ArrayList<OrderListItem> orders = new ArrayList<OrderListItem>();//向ITEM中添加所有内容，在点击时传值给下一个活动
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(TAG, "select_orders: 进入了数据库操作类");
        String SQL = "";
        Log.d(TAG, "select_orders: 创建了SQL");                                                                                                                                                                                                                            //o_uid='"+selectId(dbHelper)+"' and  o_ordername like '%"+what+"%' or o_phonenum like '%"+what+"%' or
        SQL = "select o_ordername,o_price,o_paystage,o_mopay,o_address_province,o_phonenum,o_id ,o_stage,o_content,o_cname,o_address_name,o_address_phonenum,o_time,o_address_city,o_address_area from orders  where o_cname like '%" + what + "%' or o_phonenum like '%" + what + "%' or o_address_name like '%" + what + "%' or o_ordername like '%" + what + "%' order by o_id desc";
        Log.d(TAG, "select_orders: " + SQL);

        Cursor c = db.rawQuery(SQL, null);

        int a = c.getColumnCount();
        while (c.moveToNext()) {

            OrderListItem od = new OrderListItem();
            for (int i = 0; i < a; i++) {
                od.setName(c.getString(9));
                od.setTrade_name(c.getString(0));
                od.setPrice(c.getString(1));
                od.setPaystage(c.getString(2));
                od.setMopay(c.getString(3));
                od.setAddress_province(c.getString(4));
                od.setPhonenum(c.getString(5));
                od.setId(c.getInt(6));
                od.setStage(c.getString(7));
                od.setContent(c.getString(8));
                od.setAddress_name(c.getString(10));
                od.setAddress_phoennum(c.getString(11));
                od.setTime(c.getString(12));
                od.setAddress_city(c.getString(13));
                od.setAddress_area(c.getString(14));
            }
            orders.add(od);
        }
        c.close();


        return orders;
    }


    public HashMap<Object, Object> select_mess(HuifqDbHelper dbHelper, String o_id) {
        ArrayList<Message> messlist = null;//通过select order 改造，获得站内信
        HashMap<Object, Object> obb = new HashMap();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("selcet m_ostaget,m_name,m_onum,m_mess from message where m_mess_state='0';", null);
        int cnum = c.getColumnCount();
        while (c.moveToNext()) {
            Message mess = new Message();
            for (int i = 0; i < cnum; i++) {
                mess.setState(Integer.parseInt(c.getString(0)));
                mess.setName(c.getString(1));
                mess.setId(Integer.parseInt(c.getString(2)));
                mess.setReson(c.getString(3));
            }
            messlist.add(mess);

        }
        c.close();
        obb.put("orderslist", messlist);
        ArrayList<String> infors = new ArrayList<String>();
        Cursor d = db.rawQuery("selcet o_cname,o_ordername,o_price,o_paystage,o_mopay,o_address,o_phonenum,o_id ,o_state,o_content from orders where id=';" + o_id + "';", null);
        while (d.moveToNext()) {
            for (int i = 0; i < d.getColumnCount(); i++) {
                infors.add(d.getString(0));
                infors.add(d.getString(1));
                infors.add(d.getString(2));
                infors.add(d.getString(3));
                infors.add(d.getString(4));
                infors.add(d.getString(5));
                infors.add(d.getString(6));
                infors.add(d.getString(7));
                infors.add(d.getString(8));
                infors.add(d.getString(9));
            }

        }
        d.close();
        obb.put("inofrs", messlist);

        return obb;
    }

    public void test(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("select o_cname,o_ordername,o_price,o_paystage,o_mopay,o_address,o_phonenum,o_id ,o_stage,o_content from orders", null);
        int a = c.getColumnCount();


        while (c.moveToNext()) {

            for (int i = 0; i < a - 1; i++) {


            }
        }
        c.close();

    }

    /***
     * -----------------商品管理操作类开始---------------------
     */
    public void createWares(String w_type,
                            String w_name,
                            String w_price,
                            String w_brand,
                            String w_model,
                            String w_ram,
                            String w_disk,
                            String w_memory,
                            String w_state,
                            byte[] w_img, String w_imgurl, HuifqDbHelper dbHelper) {//创建商品
        Log.d(TAG, "createWares: " + w_img);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String key = "insert into cwares(w_type" +
                ", w_name" +
                ", w_price" +
                ", w_brand" +
                ", w_model" +
                ", w_ram" +
                ", w_disk" +
                ", w_memory" +
                ", w_state" +
                ", w_img)";
        String value = " VALUES ('" + w_type + "','" + w_name + "','" + w_price + "','" + w_brand + "','" + w_model + "','" + w_ram + "','" + w_disk + "','" + w_memory + "','" + w_state + "'," + w_img + ");";
        //db.execSQL(key+value);
        Log.d(TAG, "createWares: value最终插入信息" + key + value);
        ContentValues values = new ContentValues();
        try {
            values.put("w_type", w_type);
            values.put("w_name", w_name);
            values.put("w_price", w_price);
            values.put("w_brand", w_brand);
            values.put("w_model", w_model);
            values.put("w_ram", w_ram);
            values.put("w_disk", w_disk);
            values.put("w_memory", w_memory);
            values.put("w_state", w_state);
            values.put("w_img", w_img);
            values.put("w_imgurl", w_imgurl);

            db.insert("cwares", null, values);
        } catch (NullPointerException e) {

        }
        Log.d(TAG, "createWares: " + values);
        Log.d(TAG, "createWares: 插入完成");
    }

    public void updateWares(String w_type,
                            String w_name,
                            String w_price,
                            String w_brand,
                            String w_model,
                            String w_ram,
                            String w_disk,
                            String w_memory,
                            String w_state,
                            byte[] w_img, String w_imgurl, HuifqDbHelper dbHelper, String id) {//编辑商品 包括商品信息更新和商品上架下架
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String key = "update cwares set w_type='" + w_type +
                "', w_name='" + w_name +
                "', w_price='" + w_price +
                "', w_brand='" + w_brand +
                "', w_model='" + w_model +
                "', w_ram='" + w_ram +
                "', w_disk='" + w_disk +
                "', w_memory='" + w_memory +
                "', w_state='" + w_state + "' , w_imgurl='" + w_imgurl + "' where w_id='" + id + "';";
        db.execSQL(key);
    }

    public ArrayList<WaresItem> selectWares(String id, HuifqDbHelper dbHelper) {
        ArrayList<WaresItem> ware = new ArrayList<WaresItem>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(TAG, "selectWares: 开始查找");
        if (id.equals("null")) {
            Cursor c = db.rawQuery("select * from cwares", null);
            int cnum = c.getColumnCount();
            while (c.moveToNext()) {
                Log.d(TAG, "selectWares: 找到了，正在读取");
                Log.d(TAG, "selectWares:---- " + cnum);
                WaresItem item = new WaresItem();
                for (int i = 0; i < cnum; i++) {
                    item.setId(c.getString(0));
                    item.setType(c.getString(1));
                    item.setName(c.getString(2));
                    item.setPrice(c.getString(3));
                    item.setBrand(c.getString(4));
                    item.setModel(c.getString(5));
                    item.setRam(c.getString(6));
                    item.setDisk(c.getString(7));
                    item.setMemory(c.getString(8));

                    item.setState(c.getString(9));
                    try {
                        item.setUrl(c.getString(11));
                    } catch (NullPointerException e) {
                    }
                    //Log.d(TAG, "selectWares: "+c.getBlob(10));
                    //item.setImg(icb.bci(c.getBlob(10)));
                }
                ware.add(item);
            }
            c.close();
            Log.d(TAG, "selectWares: 准备返回");
        } else {

        }


        return ware;
    }

    public ArrayList<String> selectWareInfo(String id, HuifqDbHelper dbHelper) {
        ArrayList<String> ware = new ArrayList<String>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from cwares where w_id='" + id + "'", null);
        int cnum = c.getColumnCount();
        while (c.moveToNext()) {
            Log.d(TAG, "selectWares: " + cnum);
            Log.d(TAG, "selectWares: " + c);
            WaresItem item = new WaresItem();
            Icb icb = new Icb();
            for (int i = 0; i < cnum; i++) {

                ware.add(c.getString(0));
                ware.add(c.getString(1));
                ware.add(c.getString(2));
                ware.add(c.getString(3));
                ware.add(c.getString(4));
                ware.add(c.getString(5));
                ware.add(c.getString(6));
                ware.add(c.getString(7));
                ware.add(c.getString(8));
                ware.add(c.getString(9));
                //ware.add(c.getString(10));
                ware.add(c.getString(11));
                //Log.d(TAG, "selectWares: "+c.getBlob(10));
                //item.setImg(icb.bci(c.getBlob(10)));


            }
        }

        return ware;
    }

    public ArrayList<WaresItem> checkWaresp(String query, HuifqDbHelper dbHelper) {
        ArrayList<WaresItem> ware = new ArrayList<WaresItem>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            Log.d(TAG, "checkWaresp: ----进入查询-- query=" + query);
            Cursor c = db.rawQuery("select * from cwares where w_name like '%" + query + "%' OR w_brand like '%" + query + "%'", null);
            if (c.getColumnCount() != 0) {
                Log.d(TAG, "checkWaresp: ----进入if-- query=" + query);
                Log.d(TAG, "checkWaresp: " + c);
                int cnum = c.getColumnCount();
                while (c.moveToNext()) {
                    WaresItem item = new WaresItem();
                    for (int i = 0; i < cnum; i++) {
                        item.setId(c.getString(0));
                        item.setType(c.getString(1));
                        item.setName(c.getString(2));
                        item.setPrice(c.getString(3));
                        item.setBrand(c.getString(4));
                        item.setModel(c.getString(5));
                        item.setRam(c.getString(6));
                        item.setDisk(c.getString(7));
                        item.setMemory(c.getString(8));
                        item.setState(c.getString(9));
                        item.setUrl(c.getString(11));
                    }
                    ware.add(item);
                }
                c.close();

                return ware;

            }

        } catch (NullPointerException e) {
        }

        return null;
    }

    public ArrayList<WaresItem> checkWaresp2(String query, HuifqDbHelper dbHelper) {
        ArrayList<WaresItem> ware = new ArrayList<WaresItem>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor d = db.rawQuery("select * from cwares where w_brand='" + query + "'", null);
        if (d.getColumnCount() != 0) {
            int cnum = d.getColumnCount();
            while (d.moveToNext()) {
                WaresItem item = new WaresItem();
                Icb icb = new Icb();
                for (int i = 0; i < cnum; i++) {
                    item.setId(d.getString(0));
                    item.setType(d.getString(1));
                    item.setName(d.getString(2));
                    item.setPrice(d.getString(3));
                    item.setBrand(d.getString(4));
                    item.setModel(d.getString(5));
                    item.setRam(d.getString(6));
                    item.setDisk(d.getString(7));
                    item.setMemory(d.getString(8));
                    item.setState(d.getString(9));

                }
                ware.add(item);
            }
            d.close();
            return ware;

        }
        return null;
    }
/***
 * -----------------商品管理操作完成---------------------
 */
    /***
     * -----------------角色管理操作开始---------------------
     */
    public void create_role(HuifqDbHelper dbHelper,
                            String rm_name,
                            String rm_orderma,//订单管理a,
                            String rm_neworder1a,//商品分期下单a,
                            String rm_neworder2a,//教育分期下单a,
                            String rm_neworder3a,//医美分期下单a,
                            String rm_checkordera,//查看订单a,
                            String rm_checksonordera,//查看子账号订单a,
                            String rm_allordera,//查看全部订单a,
                            String rm_comma,//商品管理a,
                            String rm_add_comma,//编辑商品a,
                            String rm_edit_comma,//添加商品a,
                            String rm_moneya,//财务中心a,
                            String rm_takemoneya,//提现a,
                            String rm_checkselfa,//查看销售额（自己）a,
                            String rm_checksonsalea,//查看子账号销售额a,
                            String rm_checkallsalea,//查看全部销售额a,
                            String rm_checkjorna,//查看财务报表a,
                            String rm_messa,//站内信a,
                            String rm_checkmessa,//站内信a,
                            String rm_reportmessa,//补交资料a,
                            String rm_systema,//系统权限a,
                            String rm_rbaca,//权限管理a,
                            String rm_changepassa,//密码修改a,
                            String rm_makesona) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        String key = "(rm_typename," +
                "rm_orderm," +//订单管理a,
                "rm_neworder1," +//商品分期下单a"+
                "rm_neworder2," +//教育分期下单a"+
                "rm_neworder3," +//医美分期下单a"+
                "rm_checkorder," +//查看订单a"+
                "rm_checksonorder," +//查看子账号订单a"+
                "rm_allorder," +//查看全部订单a"+
                "rm_comm," +//商品管理a"+
                "rm_add_comm," +//编辑商品a"+
                "rm_edit_comm," +//添加商品a"+
                "rm_money," +//财务中心a"+
                "rm_takemoney," +//提现a"+
                "rm_checkself," +//查看销售额（自己）a"+
                "rm_checksonsale," +//查看子账号销售额a"+
                "rm_checkallsale," +//查看全部销售额a"+
                "rm_checkjorn," +//查看财务报表a"+
                "rm_mess," +//站内信a"+
                "rm_checkmess," +//站内信a"+
                "rm_reportmess," +//补交资料a"+
                "rm_system," +//系统权限a"+
                "rm_rabc," +//权限管理a"+
                "rm_makeson)";


        String values = "VALUES ('" + rm_name + "','" + rm_orderma + "','" +//订单管理a,
                rm_neworder1a + "','" +//商品分期下单a+"','"+
                rm_neworder2a + "','" +//教育分期下单a+"','"+
                rm_neworder3a + "','" +//医美分期下单a+"','"+
                rm_checkordera + "','" +//查看订单a+"','"+
                rm_checksonordera + "','" +//查看子账号订单a+"','"+
                rm_allordera + "','" +//查看全部订单a+"','"+
                rm_comma + "','" +//商品管理a+"','"+
                rm_add_comma + "','" +//编辑商品a+"','"+
                rm_edit_comma + "','" +//添加商品a+"','"+
                rm_moneya + "','" +//财务中心a+"','"+
                rm_takemoneya + "','" +//提现a+"','"+
                rm_checkselfa + "','" +//查看销售额（自己）a+"','"+
                rm_checksonsalea + "','" +//查看子账号销售额a+"','"+
                rm_checkallsalea + "','" +//查看全部销售额a+"','"+
                rm_checkjorna + "','" +//查看财务报表a+"','"+
                rm_messa + "','" +//站内信a+"','"+
                rm_checkmessa + "','" +//站内信a+"','"+
                rm_reportmessa + "','" +//补交资料a+"','"+
                rm_systema + "','" +//系统权限a+"','"+
                rm_rbaca + "','" +//权限管理a+"','"+
                rm_makesona + "');";
        String sql = "insert into role " + key + values;
        Log.d(TAG, "create_role: " + sql);
        db.execSQL(sql);
        //子账号建立a,){


    }


    //查找角色
    public ArrayList<String> select_role(HuifqDbHelper dbHelper, String id) {
        Log.d(TAG, "select_role: 此处id为" + id);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<String> roleinfo = new ArrayList<String>();
        String sql = "select * from role where id='" + id + "'";
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            Log.d(TAG, "select_role: " + c.getColumnCount());
            for (int i = 0; i < c.getColumnCount(); i++) {
                roleinfo.add(c.getString(i));
                Log.d(TAG, "select_role: " + c.getString(i));
            }
        }
        c.close();
        Log.d(TAG, "select_role: ");
        return roleinfo;

    }

    //角色列表
    public ArrayList<RoleItem> select_rolelist(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<RoleItem> roleinfo = new ArrayList<RoleItem>();
        String sql = "select rm_typename,id from role";
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            RoleItem item = new RoleItem();
            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setId(c.getString(1));
                item.setRm_name(c.getString(0));
            }
            roleinfo.add(item);
        }
        c.close();
        return roleinfo;
    }

    public ArrayList<RoleItem> select_idrolelist(HuifqDbHelper dbHelper, String text) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<RoleItem> roleinfo = new ArrayList<RoleItem>();
        String sql = "select rm_typename,id from role where rm_typename like'%" + text + "%'";
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            RoleItem item = new RoleItem();
            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setId(c.getString(1));
                item.setRm_name(c.getString(0));
            }
            roleinfo.add(item);
        }
        c.close();
        return roleinfo;
    }

    public void update(HuifqDbHelper dbHelper, String where, String stage, String id, String type) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (type.equals("1")) {
            String update = "update role set " + where + "='" + stage + "' where id='" + id + "'";
            Log.d(TAG, "update: 运行了+1");

            db.execSQL(update);
        }


    }

    public HashMap<Object, Object> select_rolename(HuifqDbHelper dbHelper, Context contex) {

        HashMap<Object, Object> roles = new HashMap<Object, Object>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<String> roleinfo = new ArrayList<String>();
        ArrayList<String> roleid = new ArrayList<String>();
        String sql = "select rm_typename,id from role";
        Cursor c = db.rawQuery(sql, null);

        while (c.moveToNext()) {

            String name;
            name = c.getString(0);
            String id;
            id = c.getString(1);
            roleid.add(id);
            roleinfo.add(name);
        }
        c.close();
        roles.put("id", roleid);
        roles.put("role", roleinfo);
        return roles;
    }

    /**
     * 查看当前账户id
     */
    public String selectId(HuifqDbHelper dbHelper) {
        String id = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select u_id from startime", null);
        while (c.moveToNext()) {
            id = c.getString(0);
        }
        c.close();
        return id;
    }

    public String select_name(HuifqDbHelper dbHelper) {
        String name = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select names from login where u_id='" + selectId(dbHelper) + "'", null);
        while (c.moveToNext()) {
            name = c.getString(0);
        }
        c.close();
        return name;
    }

    /**
     * 用户管理操作
     */

    public void create_user(HuifqDbHelper dbHelper, String u_phonenum, String u_password, String u_name, String role, String roleid) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String u_id = dbHelper.selectId(dbHelper);
        Log.d(TAG, "create_user:角色名讲道理应该是 " + select_roleid(dbHelper, roleid));
        Log.d(TAG, "create_user: UID=" + u_id + roleid);
        String key = "INSERT INTO login (usename,psd,names,u_id,role,usertype) ";
        String values = "VALUES ('" + u_phonenum + "','" + u_password + "','" + u_name + "','" + u_id + "','" + role + "','" + roleid + "')";
        Log.d(TAG, "create_user: 插入完成");
        db.execSQL(key + values);
    }

    public String select_roleid(HuifqDbHelper dbHelper, String id) {
        String name = "123";
        Log.d(TAG, "select_roleid: id的值为" + id);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select rm_typename from role where id='" + id + "'", null);
        while (c.moveToNext()) {
            for (int i = 0; i < c.getColumnCount(); i++) {
                name = c.getString(0);
                Log.d(TAG, "select_roleid: " + c.getString(0));

            }

        }
        c.close();
        return name;
    }

    public int select_roleid2(HuifqDbHelper dbHelper, String id) {
        int name = 0;
        Log.d(TAG, "select_roleid: id的值为" + id);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select id from role where rm_typename='" + id + "'", null);
        while (c.moveToNext()) {
            for (int i = 0; i < c.getColumnCount(); i++) {
                name = c.getInt(0);
                Log.d(TAG, "select_roleid: " + c.getString(0));

            }

        }
        c.close();
        return name;
    }

    public HashMap<Object, Object> select_userList(HuifqDbHelper dbHelper, String id) {
        ArrayList<UserItem> itemList = new ArrayList<UserItem>();
        HashMap<Object, Object> userinfo = new HashMap<Object, Object>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<String> userinfoString = new ArrayList<String>();
        if (id.equals("null")) {
            Cursor c = db.rawQuery("select id,names,usertype,usename from login where u_id ='" + selectId(dbHelper) + "'", null);
            while (c.moveToNext()) {
                UserItem item = new UserItem();
                for (int i = 0; i < c.getColumnCount(); i++) {
                    Cursor d = db.rawQuery("select rm_typename from role where id='" + c.getString(2) + "'", null);
                    while (d.moveToNext()) {
                        item.setmUirole(d.getString(0));

                    }
                    d.close();
                    item.setId(c.getString(0));
                    Log.d(TAG, "select_userList:id========= " + c.getString(0));
                    item.setmUiname(c.getString(1));
                    item.setmUiphone(c.getString(3));
                    Log.d(TAG, "select_userList: " + c.getString(2));

                }
                itemList.add(item);
                Cursor e = db.rawQuery("select id,names,usertype,usename from login where u_id ='" + c.getString(0) + "'", null);
                while (e.moveToNext()) {
                    UserItem item2 = new UserItem();
                    for (int i = 0; i < c.getColumnCount(); i++) {
                        Cursor d = db.rawQuery("select rm_typename from role where id='" + e.getString(2) + "'", null);
                        while (d.moveToNext()) {
                            item2.setmUirole(d.getString(0));

                        }
                        item2.setmUiname(e.getString(1));
                        item2.setmUiphone(e.getString(3));
                    }
                    itemList.add(item2);
                }
            }
            c.close();
            userinfo.put("userlist", itemList);
            return userinfo;
        } else {
            Cursor c = db.rawQuery("select usename,psd,names,u_id,role,usertype from login where usename='" + id + "';", null);
            while (c.moveToNext()) {

                for (int i = 0; i < c.getColumnCount(); i++) {
                    userinfoString.add(c.getString(i));
                    Log.d(TAG, "select_userList: " + c.getString(i));
                }
            }
            c.close();
            userinfo.put("userinfos", userinfoString);
            return userinfo;
        }

    }

    public ArrayList<UserItem> select_useridList(HuifqDbHelper dbHelper, String text) {
        ArrayList<UserItem> itemList = new ArrayList<UserItem>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String SQL = "select id,names,usertype,usename from login where usename like '%" + text + "%' or names like '%" + text + "%' or usertype like '%" + text + "%'";
        Cursor c = db.rawQuery(SQL, null);
        Log.d(TAG, "select_useridList: " + SQL);
        while (c.moveToNext()) {
            UserItem item = new UserItem();
            for (int i = 0; i < c.getColumnCount(); i++) {
                Cursor d = db.rawQuery("select rm_typename from role where id='" + c.getString(2) + "'", null);

                while (d.moveToNext()) {
                    item.setmUirole(d.getString(0));

                }
                d.close();
                item.setId(c.getString(0));
                Log.d(TAG, "select_userList:id========= " + c.getString(0));
                item.setmUiname(c.getString(1));
                item.setmUiphone(c.getString(3));
                Log.d(TAG, "select_userList: " + c.getString(2));

            }
            itemList.add(item);
        }
        c.close();


        return itemList;

    }

    public void update_user(HuifqDbHelper dbHelper, String name, String phoenum, String psd, String id, String role, String usertype) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //usename,psd,names,u_id,role,usertype
        String SQL = "update login set usename='" + phoenum + "',psd='" + psd + "'," + "names='" + name + "',usertype='" + role + "'," + "usertype='" + usertype + "'  where id='" + id + "'";
        db.execSQL(SQL);
        Log.d(TAG, "update_user: " + SQL);
        Log.d(TAG, "update_user: ");

    }

    /**
     * @param dbHelper
     * @param id
     * @param reason
     */
    public void create_mess(HuifqDbHelper dbHelper, String id, String reason) {
        Log.d(TAG, "create_mess: 数据库创建站内信的id" + id);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String select = "select o_cname,o_stage from orders where o_id='" + id + "'";
        Cursor c = db.rawQuery(select, null);
        //	Log.d(TAG, "create_mess:站内信语句为 "+select);
        String stage = null;
        String name = null;
        while (c.moveToNext()) {
            name = c.getString(0);
            stage = c.getString(1);
        }
        c.close();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = dateFormat.format(date);
        String sql = "insert into message (m_content,m_onum,m_ostate,m_name,m_mess_state,m_time) VALUES('" + reason + "','" + id + "','" + stage + "','" + name + "','" + "0" + "','" + time + "')";
        db.execSQL(sql);
        Log.d(TAG, "create_mess: 创建站内信的语句为" + sql);
    }

    public ArrayList<Message> select_mess(HuifqDbHelper dbHelper) {
        ArrayList<Message> item = new ArrayList<Message>();
        /**
         * 根据用户来筛选
         * 1.selectu_id
         * 2.select order  获取orderid
         * 3.select mess
         */
        ArrayList<String> orderid = new ArrayList<String>();
        String selectorders = "select o_id from  orders where o_uid='" + selectId(dbHelper) + "'";


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor d = db.rawQuery(selectorders, null);

        while (d.moveToNext()) {

            String sql = "select m_id,m_name,m_ostate,m_content,m_onum,m_time from message where m_onum='" + d.getString(0) + "' and m_mess_state='0'";
            Log.d(TAG, "select_mess: " + d.getString(0));
            Cursor c = db.rawQuery(sql, null);
            while (c.moveToNext()) {
                Message mess = new Message();
                for (int i = 0; i < c.getColumnCount(); i++) {
                    mess.setId(Integer.parseInt(c.getString(0)));
                    mess.setName(c.getString(1));
                    //	mess.setState(Integer.parseInt(c.getString(2)));
                    mess.setReson(c.getString(3));
                    mess.setO_id(c.getInt(4));
                    mess.setM_time(c.getString(5));
                    Log.d(TAG, "select_mess: " + c.getString(4));
                }
                item.add(mess);
            }
            c.close();

        }
        d.close();


        return item;
    }


    public ArrayList<Message> select_messseach(HuifqDbHelper dbHelper, String what) {
        ArrayList<Message> messlist = new ArrayList<Message>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        /**
         * 根据用户来筛选
         * 1.selectu_id
         * 2.select order  获取orderid
         * 3.select mess
         */
        ArrayList<String> orderid = new ArrayList<String>();
        Cursor c = db.rawQuery("selcet m_ostaget,m_name,m_onum,m_mess from message where m_mess_state='0' and m_name like '%" + what + "%' or m_mess like '%" + what + "%' or m_onum like '%" + what + "%'", null);
        int cnum = c.getColumnCount();
        while (c.moveToNext()) {
            Message mess = new Message();
            for (int i = 0; i < cnum; i++) {
                mess.setState(Integer.parseInt(c.getString(0)));
                mess.setName(c.getString(1));
                mess.setId(Integer.parseInt(c.getString(2)));
                mess.setReson(c.getString(3));
            }
            messlist.add(mess);

        }
        c.close();

        return messlist;
    }


    public ArrayList<Message> select_mess1(HuifqDbHelper dbHelper) {
        ArrayList<Message> item = new ArrayList<Message>();
        try {
            /**
             * 根据用户来筛选
             * 1.selectu_id
             * 2.select order  获取orderid
             * 3.select mess
             */
            ArrayList<String> orderid = new ArrayList<String>();
            String selectorders = "select o_id from  orders where o_uid='" + selectId(dbHelper) + "'";


            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor d = db.rawQuery(selectorders, null);

            while (d.moveToNext()) {

                String sql = "select m_id,m_name,m_ostate,m_content,m_onum,m_time from message where m_onum='" + d.getString(0) + "' and m_mess_state='1'";
                Log.d(TAG, "select_mess: " + d.getString(0));
                Cursor c = db.rawQuery(sql, null);
                while (c.moveToNext()) {
                    Message mess = new Message();
                    for (int i = 0; i < c.getColumnCount(); i++) {
                        mess.setId(Integer.parseInt(c.getString(0)));
                        mess.setName(c.getString(1));
                        mess.setState(Integer.parseInt(c.getString(2)));
                        mess.setReson(c.getString(3));
                        mess.setO_id(c.getInt(4));
                        mess.setM_time(c.getString(5));
                        Log.d(TAG, "select_mess: " + c.getString(4));
                    }
                    item.add(mess);
                }
                c.close();

            }
            d.close();
        } catch (NullPointerException e) {

        }

        return item;
    }

    public void create_Supp(HuifqDbHelper dbHelper, HashMap<Object, Object> infos) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String m_id = (String) infos.get("m_id");
        String content1 = (String) infos.get("content1");
        String content2 = (String) infos.get("content2");
        String content3 = (String) infos.get("content3");
        String name1 = (String) infos.get("name1");
        String name2 = (String) infos.get("name2");
        String name3 = (String) infos.get("name3");
        String phonenum1 = (String) infos.get("phonenum1");
        String phonenum2 = (String) infos.get("phonenum2");
        String phonenum3 = (String) infos.get("phonenum3");
        String remark1 = (String) infos.get("remark1");
        String remark2 = (String) infos.get("remark2");
        String remark3 = (String) infos.get("remark3");
        String sql = "";

    }


    public ArrayList<SuppItem> select_Supp(HuifqDbHelper dbHelper, String m_id) {
        ArrayList<SuppItem> item = new ArrayList<SuppItem>();
        String sql = "select id from supp where m_id='" + m_id + "'";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            SuppItem supp = new SuppItem();
            for (int i = 0; i < c.getColumnCount(); i++) {
                supp.setId(c.getString(0));
            }
            item.add(supp);
        }
        c.close();
        return item;

    }

    public ArrayList<String> selecthome(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String a = null;
        ArrayList<String> list = new ArrayList<String>();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        String date = time.format(new Date());
        Calendar d = Calendar.getInstance();
        int Month = d.get(Calendar.MONTH) + 1;
        int day = d.get(Calendar.DAY_OF_MONTH);
        int Year = d.get(Calendar.YEAR);
        Log.d(TAG, "setcharts: 现在时间" + Year + Month + day);
        for (int i = 0; i <= 7; i++) {
            Log.d(TAG, "setcharts: 现在时间" + Year + Month + day);
            if (Month == 1) {
                if (day < 1) {
                    day = 31;
                    Month = 12;
                    Year--;
                }
            }
            if (Month == 2) {
                if (day < 1) {
                    day = 31;
                    Month--;
                }
            }
            if (Month == 5 || Month == 7 || Month == 10 || Month == 12) {
                if (day < 1) {
                    day = 30;
                    Month--;
                }

            } else if (Month == 4 || Month == 6 || Month == 8 || Month == 9 || Month == 11) {
                if (day < 1) {
                    day = 31;
                    Month--;
                }

            } else if (Year % 400 == 0 || Year % 4 == 0 && Year % 100 != 0) {
                if (Month == 3) {
                    if (day < 1) {
                        day = 29;
                        Month--;
                    }
                }
            } else {
                if (Month == 3) {
                    if (day < 1) {
                        day = 28;
                        Month--;
                    }
                }
            }
            if (Month < 10) {

                if (day < 10) {
                    date = Year + "-0" + Month + "-0" + day;
                } else {
                    date = Year + "-0" + Month + "-" + day;
                }


            } else {
                if (day < 10) {
                    date = Year + "-" + Month + "-0" + day;
                } else {
                    date = Year + "-" + Month + "-" + day;
                }

            }


            day--;
            String sql = "select count(o_id) from orders where o_time like '" + date + "%' and o_uid='" + selectId(dbHelper) + "'";
            Log.d(TAG, "selecthome: " + sql);
            Cursor c = db.rawQuery(sql, null);
            while (c.moveToNext()) {
                for (int j = 0; j < c.getColumnCount(); j++) {
                    Log.d(TAG, "selecthome: " + c.getString(0));
                    try {
                        if (c.getString(0).equals("0")) {

                            a = "0";
                        } else {
                            a = c.getString(0);

                        }
                    } catch (NullPointerException e) {
                        a = "0";
                    }

                }
            }
            c.close();
            list.add(a);
        }
        return list;
    }

    public String changepsd(HuifqDbHelper dbHelper, String psd) {
        String u_id = "";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQLU = "select u_id from startime";
        Cursor c = db.rawQuery(SQLU, null);
        while (c.moveToNext()) {
            u_id = c.getString(0);
        }
        c.close();
        Log.d(TAG, "changepsd: 查到u_id" + u_id);
        String SQL = "update login set psd='" + psd + "' where id='" + u_id + "'";
        db.execSQL(SQL);
        Log.d(TAG, "changepsd: 更新完了，看下结果");

        return u_id;

    }

    public void getuid(HuifqDbHelper dbHelper, String u_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(TAG, "getuid:进入密码查询");
        String SQL = "select psd from login where id='" + u_id + "'";
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            Log.d(TAG, "getuid: 密码是" + c.getString(0));
        }
        c.close();
    }

    public String homeStage(HuifqDbHelper dbHelper) {
        /**
         * 如果是0，loginout and set=1
         * 如果为空 create
         * 如果是1 set=0
         */

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL1 = "select stage from stages";
        Log.d(TAG, "homeStage: 进入了额");
        Cursor c = db.rawQuery(SQL1, null);
        Log.d(TAG, "homeStage: " + c.getColumnCount());


        while (c.moveToNext()) {
            if (c.getString(0).equals("null")) {


            } else {
                SQL1 = c.getString(0);

            }
        }
        c.close();
        return SQL1;
    }

    public void lodingcreate(HuifqDbHelper dbHelper) {
        SQLiteDatabase sq = dbHelper.getReadableDatabase();
        sq.execSQL("insert into stages (stage) values ('1')");
    }

    public void changeStage(HuifqDbHelper dbHelper) {
        /**
         * 如果是0，loginout and set=1
         * 如果为空 create
         * 如果是1 set=0
         */
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("update stages set stage='0'");


    }

    public void loginStage(HuifqDbHelper dbHelper) {
        /**
         * 如果是0，loginout and set=1
         * 如果为空 create
         * 如果是1 set=0
         */
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("update stages set stage='1'");

    }

    public ArrayList<String> select_orders(HuifqDbHelper dbHelper, String id) {
        ArrayList<String> orders = new ArrayList<String>();//向ITEM中添加所有内容，在点击时传值给下一个活动
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(TAG, "select_orders: " + id);
        Cursor c = db.rawQuery("select o_ordername,o_price,o_paystage,o_mopay,o_address,o_phonenum,o_id ,o_stage,o_content,o_cname,o_time,o_address_name,o_address_phonenum,o_addresstype from orders where o_id='" + id + "'", null);

        int a = c.getColumnCount();

        while (c.moveToNext()) {


            for (int i = 0; i < a; i++) {
                orders.add(c.getString(0));
                Log.d(TAG, "select_orders: mess中的orderinfo" + c.getString(0));
                orders.add(c.getString(1));
                orders.add(c.getString(2));
                orders.add(c.getString(3));
                orders.add(c.getString(4));
                orders.add(c.getString(5));
                orders.add(c.getString(6));
                orders.add(c.getString(7));
                orders.add(c.getString(8));
                orders.add(c.getString(9));
                orders.add(c.getString(10));
                orders.add(c.getString(11));
                orders.add(c.getString(12));
                orders.add(c.getString(13));
            }

        }
        c.close();


        return orders;
    }

    public ArrayList<OrderListItem> select_ordersstage(HuifqDbHelper dbHelper, String stage, int power) {
        ArrayList<OrderListItem> orders = new ArrayList<OrderListItem>();//向ITEM中添加所有内容，在点击时传值给下一个活动
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String sql = "";
            if (stage.equals("1")) {
                stage = "1' or o_stage='2' or o_stage='3' or o_stage='4' or o_stage='9";
            }
            if (power == 1) {
                sql = "select o_ordername,o_price,o_paystage,o_mopay,o_address_province,o_phonenum,o_id ,o_stage,o_content,o_cname,o_address_name,o_address_phonenum,o_time,o_addresstype,o_address_city,o_address_area,o_address_detil,o_waretype from orders where o_stage='" + stage + "' order by o_id desc ";
                Log.d(TAG, "select_ordersstage: " + sql);
            }
            if (power == 0) {
                sql = "select o_ordername,o_price,o_paystage,o_mopay,o_address_province,o_phonenum,o_id ,o_stage,o_content,o_cname,o_address_name,o_address_phonenum,o_time,o_addresstype,o_address_city,o_address_area,o_address_detil,o_waretype from orders where o_stage='" + stage + "' and o_uid ='" + selectId(dbHelper) + "' order by o_id desc ";
            }

            Cursor c = db.rawQuery(sql, null);
            Log.d(TAG, "select_ordersstage: " + c.getColumnCount());
            Log.d(TAG, "select_ordersstage: " + c.getCount());
            int a = c.getColumnCount();
            while (c.moveToNext()) {

                OrderListItem od = new OrderListItem();
                for (int i = 0; i < a; i++) {
                    od.setName(c.getString(9));
                    Log.d(TAG, "select_ordersstage: " + c.getString(0));
                    od.setTrade_name(c.getString(0));
                    od.setPrice(c.getString(1));
                    od.setPaystage(c.getString(2));
                    od.setMopay(c.getString(3));
                    od.setAddress_province(c.getString(4));
                    od.setPhonenum(c.getString(5));
                    od.setId(c.getInt(6));
                    od.setStage(c.getString(7));
                    od.setContent(c.getString(8));
                    od.setAddress_name(c.getString(10));
                    od.setAddress_phoennum(c.getString(11));
                    od.setTime(c.getString(12));
                    od.setAddresstype(c.getString(13));
                    od.setAddress_city(c.getString(14));
                    od.setAddress_area(c.getString(15));
                    od.setAddress_detil(c.getString(16));
                    od.setWare_type(c.getString(17));
                }
                orders.add(od);

            }
            c.close();
        } catch (NullPointerException e) {

        }

        return orders;
    }


    public ArrayList<ExpressItem> select_passorder(HuifqDbHelper dbHelper, String stage, int power) {
        ArrayList<ExpressItem> list = new ArrayList<ExpressItem>();//向ITEM中添加所有内容，在点击时传值给下一个活动
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String sql = "";

            if (power == 1) {
                sql = "select o_ordername,o_price,o_paystage,o_mopay,o_address_province,o_phonenum,o_id ,o_stage,o_content,o_cname,o_address_name,o_address_phonenum,o_time,o_addresstype,o_address_city,o_address_area,o_address_detil from orders where o_stage='5' and o_addresstype='0' order by o_id desc ";
                Log.d(TAG, "select_ordersstage: " + sql);
            }
            if (power == 0) {
                sql = "select o_ordername,o_price,o_paystage,o_mopay,o_address_province,o_phonenum,o_id ,o_stage,o_content,o_cname,o_address_name,o_address_phonenum,o_time,o_addresstype,o_address_city,o_address_area,o_address_detil from orders where o_stage='5' and o_addresstype='0'  and o_uid ='" + selectId(dbHelper) + "' order by o_id desc ";
            }

            Cursor c = db.rawQuery(sql, null);
            Log.d(TAG, "select_ordersstage: " + c.getColumnCount());
            Log.d(TAG, "select_ordersstage: " + c.getCount());
            int a = c.getColumnCount();
            while (c.moveToNext()) {

                ExpressItem item = new ExpressItem();
                for (int i = 0; i < a; i++) {
                    item.setCu_name(c.getString(9));
                    item.setEx_num(c.getString(6));
                    item.setCo_name(c.getString(0));
                    item.setTime(c.getString(2));
                    item.setState(c.getString(7));

                }
                list.add(item);

            }
            c.close();
        } catch (NullPointerException e) {

        }

        return list;
    }


    public int select_power(HuifqDbHelper dbHelper, String power) {
        int stage = 0;
        String r_id = reroleid(dbHelper, selectId(dbHelper));
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select " + power + " from role where id='" + r_id + "'";
        Log.d(TAG, "select_power: " + sql);
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            stage = c.getInt(0);
            Log.d(TAG, "select_power: ----------------------------" + c.getString(0));
        }
        c.close();
        return stage;
    }

    public String reroleid(HuifqDbHelper dbHelper, String u_id) {
        String roleid = "";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "select usertype from login where id='" + u_id + "'";
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            roleid = c.getString(0);
        }
        c.close();
        return roleid;
    }

    /**
     * 以下是财务中心的操作方法
     */

    public void create_with(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "insert into with (u_id,moneys,equals) values ('" + selectId(dbHelper) + "','0','1')";
    }

    public void cunqian(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String moneys = "update money set moneys='20000' where u_id='2'";
        db.execSQL(moneys);
    }

    public String select_with(HuifqDbHelper dbHelper) {
        String yue = "0";
        String stage = "0";
        Log.d(TAG, "select_with: 获取余额");
        Log.d(TAG, "select_with: 此用户ID为" + selectId(dbHelper));
        String CSQL = "insert into money (u_id,moneys,cunzai) values " + "('" + selectId(dbHelper) + "','0','1')";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "select cunzai,moneys from money where u_id='" + selectId(dbHelper) + "'";

        Cursor c = db.rawQuery(SQL, null);
        Log.d(TAG, "select_with: 下一步");
        try {
            Log.d(TAG, "select_with: 进入try");
            while (c.moveToNext()) {
                Log.d(TAG, "select_with: 进入while");
                if (c.getString(0).equals("1")) {
                    stage = c.getString(0);
                    yue = c.getString(1);
                    Log.d(TAG, "select_with: zhuangtaiwei" + yue);

                } else {
                    CSQL = "insert into money (u_id,moneys,cunzai) values " + "('" + selectId(dbHelper) + "','0','1')";

                    db.execSQL(CSQL);
                }

            }
            c.close();
            if (stage.equals("0")) {
                CSQL = "insert into money (u_id,moneys,cunzai) values " + "('" + selectId(dbHelper) + "','0','1')";
                Log.d(TAG, "select_with: " + CSQL);
                Log.d(TAG, "select_with: 此用户ID为" + selectId(dbHelper));
                db.execSQL(CSQL);
            }
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "select_with: 进入catch");


        }
        return yue;
    }

    public void update_with(HuifqDbHelper dbHelper, String o_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        float yue = Float.parseFloat(select_with(dbHelper));

        String idSQL = "select o_price from orders where o_id='" + o_id + "'";
        float price = 0;
        Cursor i = db.rawQuery(idSQL, null);
        while (i.moveToNext()) {
            price = i.getFloat(0);
            Log.d(TAG, "update_with: " + i.getFloat(0));
        }
        i.close();

        yue = yue + price;
        Log.d(TAG, "update_with:price+yue " + price + "yue" + yue);
        String SQL = "update money set moneys='" + yue + "' where u_id='" + selectId(dbHelper) + "'";
        Log.d(TAG, "update_with: " + SQL);

        db.execSQL(SQL);
    }

    public float select_price(HuifqDbHelper dbHelper, String o_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        float yue = Float.parseFloat(select_with(dbHelper));

        String idSQL = "select o_price from orders where o_id='" + o_id + "'";
        float price = 0;
        Cursor i = db.rawQuery(idSQL, null);
        while (i.moveToNext()) {
            price = i.getFloat(0);
            Log.d(TAG, "update_with: " + i.getFloat(0));
        }
        i.close();

        return price;
    }

    public void takemoeny(HuifqDbHelper dbHelper, Float takmoney) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        float yue = Float.parseFloat(select_with(dbHelper));


        yue = yue - takmoney;

        String SQL = "update money set moneys='" + yue + "' where u_id='" + selectId(dbHelper) + "'";

        db.execSQL(SQL);
    }

    public void create_supp(HuifqDbHelper dbHelper, String s_mid, String img1, String img2, String img3, String img4, String img5, String img6, String cont1, String cont2, String cont3,
                            String name1, String name2,
                            String name3,
                            String phonenum1,
                            String phonenum2,
                            String phonenum3,
                            String remarks) {
        String sql = "update message set m_mess_state='1' where m_id='" + s_mid + "'";


        String a = "','";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String key = "insert into supply (s_mid," +
                "s_cname1," +
                "s_cname2," +
                "s_canme3," +
                "s_cphone1," +
                "s_cphone2," +
                "s_cphone3," +
                "s_crelation1," +
                "s_creltaion2," +
                "s_creltaion3," +
                "s_img1," +
                "s_img2," +
                "s_img3," +
                "s_img4," +
                "s_img5," +
                "s_img6," +
                "s_remarks1)";
        String value = "VALUES('" + s_mid + a + name1 + a + name2 + a + name3 + a + phonenum1 + a + phonenum2 + a + phonenum3 + a + cont1 + a + cont2 + a + cont3 + a + img1 + a + img2 + a + img3 + a + img4 + a + img5 + a + img6 + a + remarks + "');";
        db.execSQL(key + value);
        db.execSQL(sql);

    }

    public void create_td(HuifqDbHelper dbHelper, float sum, int td_type) {


        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        Log.d(TAG, "create_td: 现在时间为" + date);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "insert into td (w_id,takemoneynum,taketime,takestage,t_type) VALUES ('" + get_wid(dbHelper) + "','" + sum + "','" + date + "','0','" + td_type + "')";
        db.execSQL(SQL);
    }

    public void create_testtd(HuifqDbHelper dbHelper, float sum, int td_type) {


        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        Log.d(TAG, "create_td: 现在时间为" + date);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "insert into td (w_id,takemoneynum,taketime,takestage,t_type) VALUES ('" + get_wid(dbHelper) + "','" + sum + "','" + date + "','0','" + td_type + "')";
        Log.d(TAG, "create_testtd: " + SQL);
        db.execSQL(SQL);
    }


    public String get_wid(HuifqDbHelper dbHelper) {
        String id = "";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "select id from money where u_id='" + selectId(dbHelper) + "'";
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            id = c.getString(0);
        }
        c.close();
        return id;
    }

    public ArrayList<Moneys> select_moneylist(HuifqDbHelper dbHelper) {
        ArrayList<Moneys> list = new ArrayList<Moneys>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "";

        SQL = "select * from td where w_id='" + get_wid(dbHelper) + "'and t_type='1' order by id desc";


        Cursor c = db.rawQuery(SQL, null);

        while (c.moveToNext()) {
            Moneys item = new Moneys();

            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setId(c.getString(0));
                item.setSum(c.getString(2));
                item.setTime(c.getString(3));
                item.setStage(c.getString(4));
                item.setType(c.getString(5));
                Log.d(TAG, "select_moneylist: i的长度为" + i);
            }
            list.add(item);
        }
        c.close();
        return list;
    }


    public ArrayList<Moneys> select_withlist(HuifqDbHelper dbHelper, String time) {
        ArrayList<Moneys> list = new ArrayList<Moneys>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "";

        SQL = "select * from td where w_id='" + get_wid(dbHelper) + "' and taketime like '" + time + "%' order by id desc";
        Log.d(TAG, "select_withlist: " + SQL);

        Cursor c = db.rawQuery(SQL, null);

        while (c.moveToNext()) {
            Moneys item = new Moneys();

            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setId(c.getString(0));
                item.setSum(c.getString(2));
                item.setTime(c.getString(3));
                item.setStage(c.getString(4));
                item.setType(c.getString(5));
            }
            list.add(item);
        }
        c.close();
        return list;
    }


    public ArrayList<HashMap<Object, Object>> sales_list(HuifqDbHelper dbHelper) {

        ArrayList<HashMap<Object, Object>> list = new ArrayList<HashMap<Object, Object>>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sSQL = "select id,names from login where u_id='" + selectId(dbHelper) + "'";
        Cursor c = db.rawQuery(sSQL, null);

        while (c.moveToNext()) {
            for (int i = 0; i < c.getColumnCount(); i++) {
                String SQL = "select count(*) from orders where o_uid='" + c.getString(0) + "' and o_stage='5'";
                Cursor d = db.rawQuery(SQL, null);
                Log.d(TAG, "sales_list: " + SQL);
                Log.d(TAG, "sales_list: ++++++++++++" + c.getString(1));

                HashMap<Object, Object> item = new HashMap<Object, Object>();
                Log.d(TAG, "sales_list: 创建item");

                while (d.moveToNext()) {
                    for (i = 0; i < d.getColumnCount(); i++) {
                        Log.d(TAG, "sales_list:--------++++++---------- " + c.getString(1));
                        Log.d(TAG, "sales_list:--------销量总数为----- " + d.getString(0));
                        item.put("name", c.getString(1));
                        item.put("sales", d.getString(0));
                    }
                }
                d.close();
                list.add(item);
            }

        }
        c.close();

        return list;
    }

    public void update_edu(HuifqDbHelper dbHelper, String phonenum, float edu) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        float nowed = Float.parseFloat(select_edu(dbHelper, phonenum));
        nowed = nowed - edu;
        String SQL = "update kehu set edu='" + nowed + "' where phonenum='" + phonenum + "'";
        db.execSQL(SQL);

    }

    public void update_tstate(HuifqDbHelper dbHelper, String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "update td set takestage='1' where id ='" + id + "'";
        db.execSQL(SQL);
    }

    public String notpass(HuifqDbHelper dbHelper, String stage) {
        if (stage.equals("1")) {
            stage = "1' or o_stage='2' or o_stage='3' or o_stage='4";
        } else if (stage.equals("6")) {
            stage = "0";

        } else if (stage.equals("4")) {
            stage = "5";
        }
        String value = "";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "select count(o_price) from orders where o_uid='" + selectId(dbHelper) + "' and o_stage='" + stage + "'";
        Log.d(TAG, "notpass:SQL代码： " + SQL);
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            value = c.getString(0);
            Log.d(TAG, "notpass: 对应value为" + value);
        }
        c.close();
        Log.d(TAG, "notpass: 最后value为" + value);
        return value;
    }

    public int select_pie(HuifqDbHelper dbHelper, String where) {
        Log.d(TAG, "select_pie: " + where);
        int value = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "select count(o_id) from orders where o_waretype ='" + where + "' and o_uid='" + selectId(dbHelper) + "'";
        Cursor c = db.rawQuery(SQL, null);
        while (c.moveToNext()) {
            value = c.getInt(0);
            Log.d(TAG, "select_pie: " + c.getInt(0));
        }
        c.close();
        return value;
    }

    public ArrayList<String> selectmonth(HuifqDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String a = null;
        ArrayList<String> list = new ArrayList<String>();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        String date = time.format(new Date());
        Calendar d = Calendar.getInstance();

        int day = d.get(Calendar.MONTH) + 1;
        int year = d.get(Calendar.YEAR);

        for (int i = 0; i <= 6; i++) {
            if (day < 10) {
                date = year + "-0" + day;
            } else {
                date = year + "-" + day;
            }

            day--;
            if (day < 1) {
                day = 12;
                year--;
            }

            Log.d(TAG, "selectmonth: m" + date);
            String sql = "select count(o_id) from orders where o_time like '" + date + "%' and o_uid='" + selectId(dbHelper) + "'";
            Cursor c = db.rawQuery(sql, null);
            while (c.moveToNext()) {
                for (int j = 0; j < c.getColumnCount(); j++) {
                    try {
                        if (c.getString(0).equals("0")) {
                            a = "0";
                        } else {
                            a = c.getString(0);
                        }
                    } catch (NullPointerException e) {
                        a = "0";
                    }

                }
            }
            c.close();
            list.add(a);
        }
        return list;
    }

    public ArrayList<With> select_withlist(HuifqDbHelper dbHelper) throws ParseException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<With> list = new ArrayList<With>();

        String time = "select o_time form ";
        String timesql = "select strftime('%Y%m',o_time),sum(o_price),o_id" +
                " from orders where o_uid='" + selectId(dbHelper) + "' and  o_stage='5' or o_stage='4' or o_stage='3' or o_stage='2' or o_stage='1' " +
                "group by strftime('%Y%m',o_time)";
        Log.d(TAG, "select_withlist: " + timesql);
        Cursor c = db.rawQuery(timesql, null);
        while (c.moveToNext()) {
            With item = new With();
            for (int i = 0; i < c.getColumnCount(); i++) {
                item.setTime(c.getString(0));
                Log.d(TAG, "select_withlist: " + c.getString(0));
                item.setSales(c.getString(1));
                Log.d(TAG, "select_withlist: " + c.getString(1));
                item.setId(c.getString(2));
            }
            list.add(item);

        }
        return list;
    }

    public void up_pingzheng(HuifqDbHelper dbHelper, String id, String url, String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "update orders set " + name + "='" + url + "' where o_id='" + id + "'";
        db.execSQL(SQL);
    }

    public void setPhoto(HuifqDbHelper dbHelper, String url) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL = "update login set state='" + url + "' where id='" + selectId(dbHelper) + "'";
        db.execSQL(SQL);
    }

    public String selectPhoto(HuifqDbHelper dbHelper) {
        String url = "0";
        try {


            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String SQL = "select state from login where id='" + selectId(dbHelper) + "'";
            try {
                Cursor c = db.rawQuery(SQL, null);
                while (c.moveToNext()) {
                    url = c.getString(0);
                }
                c.close();
            } catch (NullPointerException e) {
                url = "0";
            }
        } catch (NullPointerException e) {
            Log.d(TAG, "onCreate: 空指针异常测试点111");
        }
        return url;
    }
}
