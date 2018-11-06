package com.dayon.b2b2c.api.auth.service;

import java.util.List;
import java.util.Map;

import com.dayon.b2b2c.api.auth.entity.UserDisable;
import com.dayon.common.model.Result;

public interface UserDisableService {
	Result get(Long id);

	Result find(Map<String, Object> paramMap);

	Result count(Map<String, Object> paramMap);

	Result doAdd(UserDisable entity);

	Result doAdd(List<UserDisable> entitys);

	Result find(Map<String, Object> paramMap, Integer frist, Integer limit);
}
