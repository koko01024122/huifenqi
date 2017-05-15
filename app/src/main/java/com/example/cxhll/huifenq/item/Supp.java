package com.example.cxhll.huifenq.item;

import android.graphics.Bitmap;

/**
 * Created by CXHLL on 2016/10/29.
 */

public class Supp {
	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	String studentid;
	public String getStert() {
		return stert;
	}

	public void setStert(String stert) {
		this.stert = stert;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	String stert;
	String name;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String id;
	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public Bitmap getSuppimg1() {
		return Suppimg1;
	}

	public void setSuppimg1(Bitmap suppimg1) {
		Suppimg1 = suppimg1;
	}

	public Bitmap getSuppimg2() {
		return Suppimg2;
	}

	public void setSuppimg2(Bitmap suppimg2) {
		Suppimg2 = suppimg2;
	}

	public Bitmap getSuppimg3() {
		return Suppimg3;
	}

	public void setSuppimg3(Bitmap suppimg3) {
		Suppimg3 = suppimg3;
	}

	String text1;
	Bitmap Suppimg1;
	Bitmap Suppimg2;
	Bitmap Suppimg3;


}
