package com.example.cxhll.huifenq.item;

import android.graphics.Bitmap;

/**
 * Created by CXHLL on 2016/11/10.
 */

public class McItem {
	public String getMcName() {
		return mcName;
	}

	public void setMcName(String mcName) {
		this.mcName = mcName;
	}

	public String getMcPrice() {
		return mcPrice;
	}

	public void setMcPrice(String mcPrice) {
		this.mcPrice = mcPrice;
	}

	public int getMcState() {
		return mcState;
	}

	public void setMcState(int mcState) {
		this.mcState = mcState;
	}

	public int getMcSale() {
		return mcSale;
	}

	public void setMcSale(int mcSale) {
		this.mcSale = mcSale;
	}

	public Bitmap getMcImg() {
		return mcImg;
	}

	public void setMcImg(Bitmap mcImg) {
		this.mcImg = mcImg;
	}

	private String mcName;
	private String mcPrice;
	private int mcState;
	private int	mcSale;
	private Bitmap mcImg;

}
