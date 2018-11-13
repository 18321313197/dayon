package com.dayon.b2b2c.api.auth.service;

import java.util.List;

import com.dayon.b2b2c.api.auth.entity.Manage;
import com.dayon.common.model.DataMap;
import com.dayon.common.model.Result;

public interface ManageService {
	Result get(Long id);

	Result find(DataMap paramMap);

	Result count(DataMap paramMap);

	Result doAdd(Manage entity);

	Result doAdd(List<Manage> entitys);

	Result find(DataMap paramMap, Integer frist, Integer limit);
}
