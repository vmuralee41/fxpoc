package com.forex.trading.fxrates.modal;

/**
 *  Model to hold currency rates for base currency   
 */

import java.util.HashMap;
import java.util.Map;

public class FxrateModal {

	Map<String,String>rates=new HashMap<String,String>();
	String base;
	String date;
	
	public Map<String, String> getRates() {
		return rates;
	}
	public void setRates(Map<String, String> rates) {
		this.rates = rates;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
