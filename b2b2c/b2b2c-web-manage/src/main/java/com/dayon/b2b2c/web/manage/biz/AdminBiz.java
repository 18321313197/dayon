package com.dayon.b2b2c.web.manage.biz;

import org.springframework.beans.factory.annotation.Autowired;

import com.dayon.b2b2c.api.auth.service.AuthManageService;
import com.dayon.b2b2c.api.auth.service.AuthPowerService;
import com.dayon.b2b2c.api.auth.service.AuthRoleService;
import com.dayon.b2b2c.api.user.service.UserAdminService;

public class AdminBiz {
	@Autowired
	private UserAdminService userAdminService;
	@Autowired
	private AuthManageService authManageService;
	@Autowired
	private AuthPowerService authPowerService;
	@Autowired
	private AuthRoleService authRoleService;
}
