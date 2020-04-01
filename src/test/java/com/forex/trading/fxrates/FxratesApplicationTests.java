package com.forex.trading.fxrates;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.forex.trading.fxrates.config.Fxconstants;
import com.forex.trading.fxrates.modal.FxrateModal;

@RunWith(SpringRunner.class)
@SpringBootTest
class FxratesApplicationTests {

	@Test
	public void getLatestrates() {
		
		RestTemplate rest=new RestTemplate();
		String url=String.format(Fxconstants.LATEST_RATES_URL, "EUR","GBP,USD");
	    ResponseEntity<FxrateModal> response = rest.exchange(url, HttpMethod.GET,null,FxrateModal.class);
	    assertEquals(response.getStatusCode(), (HttpStatus.OK));   
	}
}
