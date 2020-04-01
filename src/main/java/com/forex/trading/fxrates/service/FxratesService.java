package com.forex.trading.fxrates.service;

import com.forex.trading.fxrates.exception.FxRatesException;
import com.forex.trading.fxrates.modal.FxrateMModal;
import com.forex.trading.fxrates.modal.FxrateModal;

/**
 * Services to get the exchange rates from the given fx rates api for the
 * given date and base currency for the implemented rest endpoints.
 * @author muralee
 *
 */
public interface FxratesService {

	/**
	 * The function gets the exchange rates of GBP,USD,HKD for the
	 * latest date and base currency from the provided api.
	 *
	 * @param baseccy   Base currency for the exchange rates
	 * @param symbols   Currencies for which exchange rates to be fetched
	 * @return  		Returns modal with the corresponding data 
	 * @throws FxRatesException 
	 */
	public FxrateModal getLatestrates(String baseccy,String symbols) throws FxRatesException;
	/**
	 * The function gets the exchange rates of GBP,USD,HKD for the
	 * given date and base currency.
	 * @param date		Date for which exchange rates needed.  
	 * @param baseccy   Base currency for the exchange rates
	 * @param symbols   Currencies for which exchange rates to be fetched
	 * @return  		Returns modal with the corresponding data 
	 * @throws FxRatesException
	 */
	public FxrateModal getHistoricalrates(String date, String baseccy, String symbols) throws FxRatesException;
	/**
	 * The rest endpoint gives the exchange rates of GBP,USD,HKD for the past 6 months from the
	 * given date and base currency.
	 * 
	 * @param date		Date for which the exchange rates needs to be fetched
	 * @param baseccy   Base currency for the exchange rates
	 * @param months	No of months for which the history data needed 
	 * @param symbols   Currencies for which exchange rates to be fetched
	 * @return  		Returns modal with the corresponding data 
	 * @throws FxRatesException
	 */
	public FxrateMModal getHistoricalratesMonths(String date, Integer months, String baseccy, String symbols)
			throws FxRatesException;

}
