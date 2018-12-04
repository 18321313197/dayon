package com.dayon.b2b2c.web.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayon.b2b2c.api.auth.entity.AuthManage;
import com.dayon.b2b2c.api.auth.entity.AuthPower;
import com.dayon.b2b2c.api.auth.entity.AuthRole;
import com.dayon.b2b2c.api.auth.service.AuthManageService;
import com.dayon.b2b2c.api.auth.service.AuthPowerService;
import com.dayon.b2b2c.api.auth.service.AuthRoleService;
import com.dayon.b2b2c.api.user.entity.UserAdmin;
import com.dayon.b2b2c.api.user.service.UserAdminService;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.model.XmlNode;
import com.dayon.common.base.model.XmlTag;
import com.dayon.common.util.DataUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
	//@Autowired
	private UserAdminService userAdminService;
	@Autowired
	private AuthManageService authManageService;
	//@Autowired
	private AuthPowerService authPowerService;
	//@Autowired
	private AuthRoleService authRoleService;
	
	
	@RequestMapping("/test.json")
	@ResponseBody
	public Object test(String username, String password, HttpServletRequest request) {
		authManageService.doAdd(new ArrayList<>());
		
		return null;
	}
	
	
	@RequestMapping("/login.json")
	@ResponseBody
	public Object login(String username, String password, HttpServletRequest request) {
		if (username == null || !username.matches("^\\w{4,18}$")) {
			return new Result(1, "用户名格式错误");
		}
		if (password == null || !password.matches("^\\w{4,18}$")) {
			return new Result(2, "密码格式错误");
		}
		Map<?, ?> map = (Map<?, ?>) request.getServletContext().getAttribute("cfgattr");

		String sysUsername = (String) map.get("sys_username");
		String sysPassword = (String) map.get("sys_password");
		if (username.equals(sysUsername)) {
			if (password.equals(sysPassword)) {
				return new Result("登录成功");
			} else {
				return new Result(3, "密码错误");
			}
		}
		HttpSession session=request.getSession();
		DataResult<UserAdmin> userAdminResult= userAdminService.get(username, password);
		if(userAdminResult.getRetNum()!=0) {
			return new Result(5, userAdminResult.getRetMsg());
		}
		UserAdmin userAdmin=userAdminResult.getData();
		DataResult<List<AuthRole>> authRoleResult= authRoleService.find(userAdmin.getId(), 1l);
		if(authRoleResult.getRetNum()!=0) {
			return new Result(6, authRoleResult.getRetMsg());
		}
		List<AuthRole> authRoles=authRoleResult.getData();
		Map<String,AuthPower> authPowerMap=new HashMap<>();
		for (AuthRole authRole : authRoles) {
			DataResult<List<AuthPower>> authPowerResult=authPowerService.findRolePower(authRole.getId(), 1l);
			if(authPowerResult.getRetNum()!=0) {
				return new Result(7, authPowerResult.getRetMsg());
			}
			List<AuthPower> authPowers=authPowerResult.getData();
			for (AuthPower authPower : authPowers) {
				authPowerMap.put(authPower.getServletPath(), authPower);
			}
			
		}
		DataResult<List<AuthManage>> authManageResult= authManageService.find(1l);
		if(authManageResult.getRetNum()!=0) {
			return new Result(8, authManageResult.getRetMsg());
		}
		List<AuthManage> authManages=authManageResult.getData();
		Map<Long,AuthManage> authManageMap=new HashMap<>();
		for (AuthManage authManage : authManages) {
			authManageMap.put(authManage.getId(), authManage);
		}
		
		
		XmlNode root=new XmlNode();
		root.setData(new XmlTag().setName("ul").setAttribute("class", "ul-tree"));
		
		Map<Long,XmlNode> xmlNodeMap=new HashMap<>();
		for (AuthPower authPower : authPowerMap.values()) {
			XmlNode input=new XmlNode();
			input.setData(new XmlTag().setName("input").setAttribute("type", "checkbox").setAttribute("value", authPower.getName()));
			XmlNode li=new XmlNode();
			li.setData(new XmlTag().setName("li"));
			li.addChild(input);
			
			AuthManage authManage =authManageMap.get(authPower.getManageId());
			XmlNode ul=xmlNodeMap.get(authManage.getId());
			
			if(ul==null) {
				ul=new XmlNode();
				ul.setData(new XmlTag().setName("ul"));
				xmlNodeMap.put(authManage.getId(), ul);
			}
			ul.addChild(li);
			while(authManage.getParentId()!=null && authManageMap.get(authManage.getParentId())==null) {
				 authManage=authManageMap.get(authManage.getParentId());
				 li=new XmlNode();
				 li.setData(new XmlTag().setName("li"));
				 li.addChild(ul);
				 ul=new XmlNode();
				 ul.setData(new XmlTag().setName("ul"));
				 xmlNodeMap.put(authManage.getId(), ul);
				
				 if(authManage.getParentId()==null) {
					li=new XmlNode();
					li.setData(new XmlTag().setName("li"));
					li.addChild(ul);
					root.addChild(li);
				 }
			}
				
		}
		session.setAttribute("urlTree", DataUtil.toXml(root));

		return new Result("登录成功").setDefaultAttribute(DataUtil.toXml(root));
	}

	@RequestMapping("/home")
	public Object home() {

		return "home";
	}

	@RequestMapping("/welcome")
	public Object welcome() {

		return "welcome";
	}

}
