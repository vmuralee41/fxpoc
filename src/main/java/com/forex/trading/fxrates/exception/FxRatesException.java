package com.forex.trading.fxrates.exception;

public class FxRatesException extends Exception{

	private static final long serialVersionUID = 1L;

	public FxRatesException(Throwable e) {
		super(e);
	}
	
	public FxRatesException(String msg) {
		super(msg);
	}

	public FxRatesException(String msg,Throwable e) {
		super(msg,e);
	}
	
}
