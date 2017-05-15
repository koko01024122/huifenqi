package com.example.cxhll.huifenq.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cxhll.huifenq.item.Message;
import com.example.cxhll.huifenq.item.Orderinfo;
import com.example.cxhll.huifenq.item.Supp;

import java.util.ArrayList;

/**
 * Created by CXHLL on 2016/10/15.
 */

public class MessageListHelper extends SQLiteOpenHelper {
	//private String TAG="MesList.class";
	private String TAG = "OrderSuccAty";

	private static final String CREATE_ORDER = "create table neworder(" +
			"id integer primary key autoincrement, "
			+ "phonenum text, "
			+ "edu text, "
			+ "tradename text, "
			+ "tradeprice text, "
			+ "payday text, "
			+ "payment text, "
			+ "remarks text, "
			+ "receiver text, "
			+ "addresstype text, "
			+ "receiverphonenum text, "
			+ "addressdetil text)";
	private static final String CREATE_TABLE = "create table messlist(" +
			"id integer primary key autoincrement, "
			+ "name text,  "
			+ "reason text, "
			+ "state integer, "
			+ "onum text, "
			+ "tname text, "
			+ "tprice text, "
			+ "receiver text, "
			+ "rphonenum text, "
			+ "examine text, "//状态
			+ "phonenum text)";//客户手机号
	private static final String CREATE_SUPP = "create table supp(" +
			"id integer primary key autoincrement, "
			+ "starttime text, "
			+ "studentid text, "//等于楼上的ID
			+ "text1 text)";

	private Context mcontext;

	public MessageListHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
		mcontext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
		db.execSQL(CREATE_SUPP);
		db.execSQL(CREATE_ORDER);
		Log.d(TAG, "success数据库创建成功");
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

	}

	public void newSupp(String stuid, MessageListHelper messageListHelper) {
		SQLiteDatabase db = messageListHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("starttime", "0");
		values.put("studentid", stuid);
		db.insert("supp", null, values);
		Log.d(TAG, "newSupp: 执行+1");
		values.clear();

/**
 *

 */

	}

	public void newOrder(ArrayList<String> olist, MessageListHelper messageListHelper) {
		Log.d(TAG, "newOrder: 进入了newOrder");

		SQLiteDatabase db = messageListHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("phonenum", olist.get(0));
		/**
		 * 开始
		 */

		Log.d(TAG, "newOrder: " + olist.get(0));
		Log.d(TAG, "newOrder: " + olist.get(1));
		Log.d(TAG, "newOrder: " + olist.get(2));
		Log.d(TAG, "newOrder: " + olist.get(3));
		Log.d(TAG, "newOrder: " + olist.get(4));
		Log.d(TAG, "newOrder: " + olist.get(5));
		Log.d(TAG, "newOrder: " + olist.get(6));
		Log.d(TAG, "newOrder: " + olist.get(7));
		Log.d(TAG, "newOrder: " + olist.get(8));
		Log.d(TAG, "newOrder: " + olist.get(9));
		Log.d(TAG, "newOrder: " + olist.get(10));
		/**
		 * 结束
		 *
		 */
		values.put("edu", olist.get(1));
		values.put("tradename", olist.get(2));
		values.put("tradeprice", olist.get(3));
		values.put("payday", olist.get(4));
		values.put("payment", olist.get(5));
		values.put("remarks", olist.get(6));
		values.put("receiver", olist.get(7));
		values.put("addresstype", olist.get(8));
		values.put("receiverphonenum", olist.get(9));
		values.put("addressdetil", olist.get(10));
		db.insert("neworder", null, values);

		values.clear();
		/**
		 * 		infolist.add(String.valueOf(newPhonenum.getText()));
		 infolist.add(String.valueOf(canUseEdu.getText()));
		 infolist.add(String.valueOf(newTradeName.getText()));
		 infolist.add(String.valueOf(newTradePrice.getText()));
		 infolist.add(String.valueOf(newPayday.getText()));
		 infolist.add(String.valueOf(newPayMent.getText()));
		 infolist.add(String.valueOf(remarks.getText()));
		 infolist.add(String.valueOf(receiver.getText()));
		 infolist.add(addressType);
		 infolist.add(String.valueOf(receiverphonenum.getText()));
		 infolist.add(String.valueOf(addressdetil.getText()));

		 */
	}

	public void seletctext(MessageListHelper message) {
		Log.d(TAG, "seletctext: 你来了么");
		SQLiteDatabase db = message.getWritableDatabase();
		Cursor c = db.query("messlist", null, null, null, null, null, null);
		int cnum = c.getColumnCount();
		Log.d(TAG, "seletctext: " + cnum);
		Log.d(TAG, "seletctext: 即将进入循环");
		while (c.moveToNext()) {
			Log.d(TAG, "seletctext: 进入循环");
			for (int i = 0; i < cnum; i++) {
				Log.d(TAG, "seletctext: " + c.getString(0));
			}
		}

	}

	public ArrayList<String> select(MessageListHelper messageListHelper) {

		ContentValues values = new ContentValues();
		values.put("examine", "1");

		Log.d(TAG, "selectoi: 设置当前为已读");
		Log.d(TAG, "selectoi: 进入方法成功");
		ArrayList<String> list = new ArrayList<String>();
		Log.d(TAG, "selectoi: 创建数组成功");
		SQLiteDatabase db = messageListHelper.getWritableDatabase();
		String whereClause;
		//String[] whereAge=new String[]{id};

		Log.d(TAG, "selectoi: 创建sqlitedatabase成功");

		//db.rawQuery("select * from messlist where name='"+name+"'",null);
		String selection;//条件句
		//String[] selectionArgs=new String[]{id};
		Cursor c = db.query("messlist",
				null,
				null,
				null,
				null, null, null, null);
		Log.d(TAG, "selectoi: 查询语句执行成功");
		int columnsSize = c.getColumnCount();
		while (c.moveToNext()) {
			list.add(c.getString(1));
			Log.d(TAG, "select: " + c.getString(0));
			list.add(c.getString(1));
			list.add(c.getString(1));
			list.add(c.getString(1));
			list.add(c.getString(1));
			list.add(c.getString(1));
			list.add(c.getString(1));
			list.add(c.getString(1));
			list.add(c.getString(1));
			list.add(c.getString(1));
			list.add(c.getString(1));
			Log.d(TAG, "selectoi: list数据插入成功");

		}

		return list;

	}


	public void insert(MessageListHelper messageListHelper) {

		SQLiteDatabase db = messageListHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		ContentValues values1 = new ContentValues();
		values.put("name", "张三");
		values.put("phonenum", "18186143489");
		values.put("reason", "家长电话未接通");
		values.put("state", 0);

		values.put("onum", "201610241517003");
		values.put("tname", "戴尔笔记本电脑");
		values.put("tprice", "200");
		values.put("receiver", "刘二狗");
		values.put("rphonenum", "18202335566");
		values.put("examine", "1");
		db.insert("messlist", null, values);
		values.clear();
/*

	"id integer primary key autoincrement, "
			+"order_num text"//订单号
			+"trade_name text"//商品名
			+"trade_price real"//商品价格
			+"receiver text"//收货人姓名
			+"receiver_phonenum text"//收货人手机号
			+ "reason text, "
			+ "name text, "
			+ "state integer)";
 */


		values.put("name", "张三");
		values.put("reason", "家长电话未接通");
		values.put("phonenum", "18186143489");
		values.put("state", 1);
		values.put("onum", "201610241517004");
		values.put("tname", "联想笔记本电脑");
		values.put("tprice", "1000");
		values.put("receiver", "马六蛋");
		values.put("examine", "1");
		values.put("rphonenum", "18202322366");

		db.insert("messlist", null, values);
		values.clear();


		values.put("name", "李四");
		values.put("reason", "家长电话未接通");
		values.put("phonenum", "18186143489");
		values.put("state", 3);
		values.put("examine", "1");
		values.put("onum", "201610241517003");
		values.put("tname", "华硕笔记本电脑");
		values.put("tprice", "500");
		values.put("receiver", "刘二狗");
		values.put("rphonenum", "18202112566");

		db.insert("messlist", null, values);
		values.clear();


		values.put("name", "玩物");
		values.put("reason", "家长电话未接通");
		values.put("phonenum", "18186143489");
		values.put("state", 4);
		values.put("examine", "0");
		values.put("onum", "201610241517003");
		values.put("tname", "神舟笔记本电脑");
		values.put("tprice", "700");
		values.put("receiver", "刘二狗");
		values.put("rphonenum", "18202335566");
		db.insert("messlist", null, values);
		values.clear();
	}

	public ArrayList<Supp> selectsupp(String stuid, MessageListHelper messageListHelper) {

		Log.d(TAG, "selectsupp: 执行+2");
		ArrayList<Supp> list1 = new ArrayList<Supp>();
		SQLiteDatabase db = messageListHelper.getWritableDatabase();
		String whereClause = "studentid=?";
		String[] whereAge = new String[]{stuid};
		Cursor c = db.query("supp",
				null,
				whereClause,
				whereAge,
				null, null, null, null);
		int cnum = c.getColumnCount();
		Log.d(TAG, "selectsupp: " + cnum);
		while (c.moveToNext()) {

			Supp supp = new Supp();
			for (int i = 0; i < cnum; i++) {
				supp.setId(c.getString(0));
				supp.setStert(c.getString(1));
				supp.setStudentid(c.getString(2));
				supp.setText1(c.getString(3));
			}
			list1.add(supp);
		}
		return list1;
	}

	public ArrayList<String> selectoi(String id, MessageListHelper messageListHelper) {

		ContentValues values = new ContentValues();
		values.put("examine", "1");

		Log.d(TAG, "selectoi: 设置当前为已读");
		Log.d(TAG, "selectoi: 进入方法成功");
		ArrayList<String> list = new ArrayList<String>();
		Log.d(TAG, "selectoi: 创建数组成功");
		SQLiteDatabase db = messageListHelper.getWritableDatabase();
		String whereClause;
		String[] whereAge = new String[]{id};

		Log.d(TAG, "selectoi: 创建sqlitedatabase成功");

		//db.rawQuery("select * from messlist where name='"+name+"'",null);
		String selection;//条件句
		String[] selectionArgs = new String[]{id};
		Cursor c = db.query("messlist",
				null,
				selection = "id=?",
				selectionArgs,
				null, null, null, null);
		Log.d(TAG, "selectoi: 查询语句执行成功");
		int columnsSize = c.getColumnCount();
		while (c.moveToNext()) {
			list.add(c.getString(0));
			Log.d(TAG, "selectoi-------------------------------------------------------------------: " + c.getInt(0));
			list.add(c.getString(1));
			Log.d(TAG, "selectoi-------------------------------------------------------------------: " + c.getString(1));
			Log.d(TAG, "selectoi--------------------------!!!!!!!!-------------------------------: " + list.get(0));
			list.add(c.getString(2));
			list.add(c.getString(3));
			list.add(c.getString(4));
			list.add(c.getString(5));
			list.add(c.getString(6));
			list.add(c.getString(7));
			list.add(c.getString(8));
			list.add(c.getString(10));
			Log.d(TAG, "selectoi: list数据插入成功");

		}
		Log.d(TAG, "selectoi:即将返回数据 ");
		db.update("messlist", values, whereClause = "id=?", whereAge);
		return list;

	}


	public ArrayList<Message> select(ArrayList<Message> list1, MessageListHelper messageListHelper) {
		Orderinfo orderinfo = new Orderinfo();
		SQLiteDatabase db = messageListHelper.getWritableDatabase();
		String selection = "examine=?";
		String[] selectionArgs = new String[]{"0"};
		Cursor c = db.query("messlist",
				null,
				selection,
				selectionArgs,
				null, null, null, null);

		int columnsSize = c.getColumnCount();
		while (c.moveToNext()) {
			Message mes = new Message();
			//	HashMap<String,Object> map=new HashMap<String,Object>();
			for (int i = 0; i < columnsSize; i++) {
				mes.setId(c.getInt(0));
				orderinfo.setId(c.getInt(0));
				//Log.d(TAG, "select: "+mes.getRecode());
				mes.setReson(c.getString(2));
				orderinfo.setReason(c.getString(2));
				//	Log.d(TAG, "select: "+mes.getReson());
				mes.setName(c.getString(1));
				//Log.d(TAG, "select: "+mes.getName());
				mes.setState(c.getInt(3));
				//Log.d(TAG, "状态"+String.valueOf(mes.getState()));
			}
			list1.add(mes);

		}
		return list1;
	}

	public void save(String id, MessageListHelper messageListHelper, String text) {
		ContentValues values = new ContentValues();
		String where = "id=?";
		String[] whereAge = new String[]{id};
		SQLiteDatabase db = messageListHelper.getWritableDatabase();
		values.put("text1", text);
		db.update("supp", values, where, whereAge);
	}
}
