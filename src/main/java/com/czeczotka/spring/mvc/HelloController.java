package com.czeczotka.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static java.text.MessageFormat.format;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {
    
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String hello(
            @RequestParam(required = false, defaultValue = "world") String name, 
            HttpServletResponse response) {
        
		String message = format("Hello {0}!", name);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		return message;
	}
}