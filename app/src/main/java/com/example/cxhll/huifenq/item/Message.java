package com.example.cxhll.huifenq.item;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by CXHLL on 2016/10/17.
 */

public class Message {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getO_id() {
		return o_id;
	}

	public void setO_id(int o_id) {
		this.o_id = o_id;
	}

	public String getM_time() {
		return m_time;
	}

	public void setM_time(String m_time) {
		this.m_time = m_time;
	}

	private String m_time;
	private int o_id;
	private int id;
	private int recode;
	private String phonenum;
	private String name;
	private String reson;
	private int state;
	public int getState() {
		return state;
	}

	public void setState(int state) {




		this.state = state;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}



	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {

		this.reson = reson;
	}


	public int getRecode() {
		return recode;
	}

	public void setRecode(int recode) {

		this.recode = recode;
	}



}
