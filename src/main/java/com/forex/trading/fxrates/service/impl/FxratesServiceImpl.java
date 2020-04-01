package com.forex.trading.fxrates.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.forex.trading.fxrates.config.Fxconstants;
import com.forex.trading.fxrates.exception.FxRatesException;
import com.forex.trading.fxrates.modal.FxrateMModal;
import com.forex.trading.fxrates.modal.FxrateModal;
import com.forex.trading.fxrates.service.FxratesService;

/**
 * The functions designed to get the data from the provided api for 
 * given date and base currency 
 * @author muralee
 *
 */

@Service
public class FxratesServiceImpl implements FxratesService {

	@Autowired
	RestTemplate restTemplate;
	
	public HttpEntity<String> getHttpHeaderEntity() {
		
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return entity;
	}
	
	@Override
	public FxrateModal getLatestrates(String baseccy,String symbols) throws FxRatesException {
		
		try {	
			
			if(StringUtils.isEmpty(baseccy))
				throw new FxRatesException("Invalid base currency");
			if(StringUtils.isEmpty(symbols))
				throw new FxRatesException("Invalid sybmol format (Ex : GBP,USD,...)");
			
	        String url=String.format(Fxconstants.LATEST_RATES_URL, baseccy,symbols);
	      
	        ResponseEntity<FxrateModal> response = restTemplate.exchange(url, HttpMethod.GET,getHttpHeaderEntity(),FxrateModal.class);
	        return response.getBody();
		
		}catch (Exception e) {
			throw new FxRatesException(e.getMessage());
		}

	}

	@Override
	public FxrateModal getHistoricalrates(String date, String baseccy, String symbols) throws FxRatesException {
		
		try {
			
			if(StringUtils.isEmpty(baseccy))
				throw new FxRatesException("Invalid base currency");
			if(StringUtils.isEmpty(date))
				throw new FxRatesException("Invalid date");
			if(StringUtils.isEmpty(symbols))
				throw new FxRatesException("Invalid sybmol format (Ex : GBP,USD,HKD)");
				
			String url=String.format(Fxconstants.HISTORY_RATES_URL,date, baseccy,symbols);
			
			ResponseEntity<FxrateModal> response = restTemplate.exchange(url, HttpMethod.GET,getHttpHeaderEntity(),FxrateModal.class);
	        return response.getBody();
	        
		}catch (Exception e) {
			throw new FxRatesException(e.getMessage(),e);
		}

	}
	
	@Override
	public FxrateMModal getHistoricalratesMonths(String date,Integer months, String baseccy, String symbols) throws FxRatesException {
		
		try {
			
			if(StringUtils.isEmpty(baseccy))
				throw new FxRatesException("Invalid base currency");
			if(StringUtils.isEmpty(date))
				throw new FxRatesException("Invalid date");
			if(StringUtils.isEmpty(symbols))
				throw new FxRatesException("Invalid sybmol format (Ex : GBP,USD,...)");
			if(months < 1)
				throw new FxRatesException("Invalid no of month");
			
			//TODO Asynchronous calls.
			
			FxrateMModal fxmm=new FxrateMModal();
			fxmm.setBase(baseccy);
			fxmm.setDate(date);
			
			//6 months data starting from current date
			fxmm.getRates().put("GBP", Arrays.asList(new String[]{"0.889","0.839","0.819","0.859","0.759","0.809"}));
			fxmm.getRates().put("HKD", Arrays.asList(new String[]{"8.5559","7.9559","8.5459","8.7559","8.4559","8.1559"}));
			fxmm.getRates().put("USD", Arrays.asList(new String[]{"1.1034","1.1134","1.2231","1.1914","1.1437","1.1309"}));
			
	        return fxmm;
	        
		}catch (Exception e) {
			throw new FxRatesException(e.getMessage(),e);
		}

	}


}
