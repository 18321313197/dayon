package com.dayon.b2b2c.testuser;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dayon.b2b2c.api.user.entity.Admin;
import com.dayon.b2b2c.api.user.service.AdminService;
import com.dayon.b2b2c.center.user.service.impl.AdminServiceImpl;
import com.dayon.common.model.Result;
import com.dayon.common.other.DataUtil;

public class Main {
	private static ApplicationContext act = new ClassPathXmlApplicationContext("classpath*:spring-context.xml");

	
	public static  void main1(String srgs[]) {
		AdminService as = act.getBean("adminService",AdminServiceImpl.class);
		String[] ss=act.getBeanNamesForType(AdminService.class);
		System.out.println(ss[0]);
		System.out.println(ss[1]);
		Result result = as.get("sys_admin", "123456");
		Admin admin = result.getAttribute("admin", Admin.class);
		System.out.println(admin.getRealname());

	}

	
	public static void main(String srgs[]) {
		
		AdminService as = act.getBean("adminServiceImpl",AdminService.class);
		Date date=new Date();
		for (int i = 1; i <= 1000; i++) {
			long id=DataUtil.randomLong();
			Admin admin = new Admin();
			admin.setId(id);
			admin.setUsername("user"+i );
			admin.setPassword("123456");
			admin.setRealname("用户"+ i);
			admin.setCreateBy("sys_admin");
			admin.setCreateTime(date);
			as.doAdd(admin);
		}
	}

}
