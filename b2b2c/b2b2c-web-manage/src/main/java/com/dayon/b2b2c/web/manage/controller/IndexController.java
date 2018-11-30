package com.dayon.b2b2c.web.manage.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {
	@RequestMapping
	public Object index() {
		return "index";
	}
}
