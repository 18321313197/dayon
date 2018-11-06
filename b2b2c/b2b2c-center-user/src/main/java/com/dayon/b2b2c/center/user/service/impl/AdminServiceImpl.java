package com.dayon.b2b2c.center.user.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayon.b2b2c.api.user.entity.Admin;
import com.dayon.b2b2c.api.user.service.AdminService;
import com.dayon.b2b2c.center.user.mapper.AdminMapper;
import com.dayon.common.model.Result;
@Component
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminMapper adminMapper;
	@Override

	public Result get(String username, String password) {
		
		Result result=new Result();
		
		Map<String,Object> paramMap=new HashMap<>();
		paramMap.put("username", username);
		paramMap.put("password", password);
		List<Admin> admins=adminMapper.find(paramMap);
		if(admins.size()==0){
			result.setRetNum(1);//用户名或密码错码;
			return result;
		}
		result.setAttribute("admin", admins.get(0));
		result.setRetNum(0);
		return result;
	}
	@Override
	public Result doAdd(Admin admin) {
		adminMapper.add(admin);
		admin.setId(admin.getId()-1);
		adminMapper.add(admin);
		return null;
	}
	@Override
	public Result find(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Result count(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Result find(Map<String, Object> paramMap, Integer frist, Integer limit){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Result doAdd(List<Admin> entitys) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Result get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
