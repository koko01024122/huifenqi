package com.example.cxhll.huifenq.item;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by CXHLL on 2016/10/24.
 */

public class Orderinfo {
	private String TAG = "MessAty.class";

	public int getId() {
		Log.d(TAG, "getId~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~: " + id);
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int id;


	private String onum;

	public String getOnum() {
		return onum;
	}

	public void setOnum(String onum) {
		this.onum = onum;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTprice() {
		return tprice;
	}

	public void setTprice(String tprice) {
		this.tprice = tprice;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getRphonenum() {
		return rphonenum;
	}

	public void setRphonenum(String rphonenum) {
		this.rphonenum = rphonenum;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	private String tname;
	private String tprice;
	private String receiver;
	private String rphonenum;
	private String reason;
	private String name;
	private int state;


}
