package com.dayon.b2b2c.api.user.service;

import java.util.List;
import java.util.Map;

import com.dayon.b2b2c.api.user.entity.Admin;
import com.dayon.common.model.Result;

public interface AdminService {
	Result get(Long id);
	Result get(String username,String password);
	Result find(Map<String,Object> paramMap);
	Result count(Map<String,Object> paramMap);
	Result doAdd(Admin admin);
	Result doAdd(List<Admin> entitys);
	Result find(Map<String, Object> paramMap, Integer frist, Integer limit);
	
}
