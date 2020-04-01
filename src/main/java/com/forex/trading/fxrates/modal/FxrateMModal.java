package com.forex.trading.fxrates.modal;

/**
 *  Model to hold currency rates aganist base currency for certain months   
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FxrateMModal {

	Map<String,List<String>>rates=new HashMap<String,List<String>>();
	String base;
	String date;

	public Map<String, List<String>> getRates() {
		return rates;
	}
	public void setRates(Map<String, List<String>> rates) {
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
