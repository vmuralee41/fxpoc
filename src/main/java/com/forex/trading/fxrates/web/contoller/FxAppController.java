package com.forex.trading.fxrates.web.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * 
 * @author muralee
 *
 */
@Controller
public class FxAppController {
	
	@GetMapping("/")
	public String fxhome() {
		
		return "redirect:/fxdetails";
	}

}
