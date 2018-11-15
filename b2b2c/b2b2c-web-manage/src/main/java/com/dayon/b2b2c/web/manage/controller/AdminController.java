package com.dayon.b2b2c.web.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("login.json")
	@ResponseBody
	public Object login(String username,String password) {
		
		
		return "success";
	}
}
