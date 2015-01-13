package com.czeczotka.spring.mvc;

import com.czeczotka.spring.domain.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static java.text.MessageFormat.format;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    
	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String hello(@RequestParam(required = false, defaultValue = "world") String name) {
		return format("Hello {0}!", name);
	}
    
    @RequestMapping(value = "/latest", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody News getLatestNews() {
		return new News("Latest news!", "These are the latest news!", null);
	}
}