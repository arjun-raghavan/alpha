/**
 * 
 */
package com.perseverance.authorization.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AR350758
 * HelloWorldController.java Aug 6, 2019 5:43:56 PM
 */
@RestController
public class HelloWorldController {

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

}
