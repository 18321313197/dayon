package com.dayon.b2b2c.web.manage.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping("/loginPage")
	public Object loginPage() {
		return "admin/manage-login";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Object login(String username,String password) {
		return "success";
	}
}
