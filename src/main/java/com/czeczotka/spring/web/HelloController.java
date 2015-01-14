package com.czeczotka.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static java.text.MessageFormat.format;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController extends AbstractController{

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = CONTENT_TYPE)
	public @ResponseBody String hello(@RequestParam(required = false, defaultValue = "world") String name) {
		return format("Hello {0}!", name);
	}
}