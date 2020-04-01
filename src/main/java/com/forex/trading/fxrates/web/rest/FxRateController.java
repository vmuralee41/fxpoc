package com.forex.trading.fxrates.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forex.trading.fxrates.exception.ErrorFxMessage;
import com.forex.trading.fxrates.exception.FxRatesException;
import com.forex.trading.fxrates.modal.FxrateMModal;
import com.forex.trading.fxrates.modal.FxrateModal;
import com.forex.trading.fxrates.service.FxratesService;

/**
 * Rest endpoints for getting different fx rates / formats for given date , base currency
 * along with the exception handling. 
 * @author muralee
 *
 */

@RestController
@RequestMapping("/fx")
public class FxRateController {

	@Autowired
	FxratesService fxservice;
	
	/**
	 * The rest endpoint the exchange rates of GBP,USD,HKD for the
	 * latest date and base currency.
	 * 
	 * @param baseccy   Base currency for the exchange rates
	 * @return  		Returns modal with the corresponding data 
	 * @throws FxRatesException
	 */
	@GetMapping("/latest/rates/base/{baseccy}")
	public FxrateModal getRates(@PathVariable("baseccy") String baseccy) throws FxRatesException
	{
		return fxservice.getLatestrates(baseccy, "GBP,USD,HKD");
	}
	
	/**
	 * The rest endpoint gives the exchange rates of GBP,USD,HKD for the
	 * given date and base currency.
	 * 
	 * @param date		Date for which the exchange rates needs to be fetched
	 * @param baseccy   Base currency for the exchange rates
	 * @return  		Returns modal with the corresponding data 
	 * @throws FxRatesException
	 */
	@GetMapping("/history/rates/date/{date}")
	public FxrateModal getHistoryRates(@PathVariable("date") String date,@RequestParam String baseccy) throws FxRatesException
	{
		return fxservice.getHistoricalrates(date, baseccy,"GBP,USD,HKD");
	}
	
	/**
	 * The rest endpoint gives the exchange rates of GBP,USD,HKD for the past 6 months from the
	 * given date and base currency.
	 * 
	 * @param date		Date for which the exchange rates needs to be fetched
	 * @param baseccy   Base currency for the exchange rates
	 * @param months	No of months for which the history data needed 
	 * @return  		Returns modal with the corresponding data 
	 * @throws FxRatesException
	 */
	@GetMapping("/history/rates/months/date/{date}")
	public FxrateMModal getHistoryRatesMonths(@PathVariable("date") String date,@RequestParam String baseccy,@RequestParam Integer months) throws FxRatesException
	{
		return fxservice.getHistoricalratesMonths(date, months, baseccy, "GBP,USD,HKD");
	}

	/**
	 * Exception handler for the implemented rest endpoints. Returns the error 
	 * object with relavent exception messages to the client.  
	 * @param ex	Exceptions thrown by the rest endpoints / srvices
	 * @return 		Custom error object for the frontend
	 * @throws Exception
	 */
	@ExceptionHandler(FxRatesException.class)
    public ResponseEntity<ErrorFxMessage> FxratesExceptionhandler(Exception ex) throws Exception 
	{
		ErrorFxMessage fxerr=new ErrorFxMessage();
		fxerr.setErrmessage(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fxerr);   
    }
	
} 
