package com.dayon.b2b2c.api.auth.service;
import java.util.List;

import com.dayon.b2b2c.api.auth.entity.AuthPower;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.PageDataResult;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.dto.model.DataMap;

public interface AuthPowerService {
	DataResult<AuthPower> get( Long id );

	DataResult<List<AuthPower>> find(DataMap paramMap);

	DataResult<Long> count(DataMap paramMap);

	Result doAdd(AuthPower authPower);

	Result doAdd(List<AuthPower> authPowers);

	DataResult<PageDataResult<AuthPower>> pageFind(DataMap paramMap, Integer page, Integer limit);
	
	Result doModify(AuthPower authPower);
	
	Result doRemove( Long id );
	
	DataResult<List<AuthPower>> findRolePower(Long roleId, Long platformId);
}
