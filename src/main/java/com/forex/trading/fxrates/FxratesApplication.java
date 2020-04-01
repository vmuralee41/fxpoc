package com.forex.trading.fxrates;

import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;

/**
 * This starts the  application of fxrate exposes rest services.     
 * Implemented basic security with credentials admin/admin. It uses swagger
 * to scan the exposed rest endpoints with documentation.  
 * @param  url  an absolute URL giving the base location of the image
 * @param  name the location of the image, relative to the url argument
 * @return      the image at the specified URL
 * @see         Image
 */

@SpringBootApplication
public class FxratesApplication {

	public static void main(String[] args) throws ParseException {

		SpringApplication.run(FxratesApplication.class, args);
	}

}

