package com.dayon.b2b2c.api.auth.service;
import java.util.List;
import com.dayon.b2b2c.api.auth.entity.AuthRolePower;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.dto.model.DataMap;
import com.dayon.common.base.dto.PageDataResult;

public interface AuthRolePowerService {
	DataResult<AuthRolePower> get( Long id );

	DataResult<List<AuthRolePower>> find(DataMap paramMap);

	DataResult<Long> count(DataMap paramMap);

	Result doAdd(AuthRolePower authRolePower);

	Result doAdd(List<AuthRolePower> authRolePowers);

	DataResult<PageDataResult<AuthRolePower>> pageFind(DataMap paramMap, Integer page, Integer limit);
	
	Result doModify(AuthRolePower authRolePower);
	
	Result doRemove( Long id );
}
