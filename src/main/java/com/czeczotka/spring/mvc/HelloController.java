package com.czeczotka.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static java.text.MessageFormat.format;

@Controller
@RequestMapping("/hello")
public class HelloController {
    
	@RequestMapping(method = RequestMethod.GET)
	public String hello(@RequestParam(required = false, defaultValue = "world") String name, ModelMap model) {
		model.addAttribute("message", format("Hello {0}!", name));
		return "hello";
	}
}