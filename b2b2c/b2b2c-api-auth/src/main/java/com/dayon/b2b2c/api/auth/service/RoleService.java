package com.dayon.b2b2c.api.auth.service;

import java.util.List;
import java.util.Map;

import com.dayon.b2b2c.api.auth.entity.Role;
import com.dayon.common.model.Result;

public interface RoleService {
	Result get(Long id);

	Result find(Map<String, Object> paramMap);

	Result count(Map<String, Object> paramMap);

	Result doAdd(Role entity);

	Result doAdd(List<Role> entitys);

	Result find(Map<String, Object> paramMap, Integer frist, Integer limit);
}
