package com.czeczotka.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static java.text.MessageFormat.format;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/hello")
public class HelloController {
    
	@RequestMapping(method = RequestMethod.GET)
	public String hello(
            @RequestParam(required = false, defaultValue = "world") String name, 
            ModelMap model,
            HttpServletResponse response) {
        
		model.addAttribute("message", format("Hello {0}!", name));
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
		return "hello";
	}
}