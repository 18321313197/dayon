package com.dayon.b2b2c.api.auth.service;
import java.util.List;
import com.dayon.b2b2c.api.auth.entity.AuthPlatform;

import com.dayon.common.base.model.DataMap;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.dto.PageDataResult;

public interface AuthPlatformService {
	DataResult<AuthPlatform> get( Long id );

	DataResult<List<AuthPlatform>> find(DataMap paramMap);

	DataResult<Long> count(DataMap paramMap);

	Result doAdd(AuthPlatform authPlatform);

	Result doAdd(List<AuthPlatform> authPlatforms);

	DataResult<PageDataResult<AuthPlatform>> pageFind(DataMap paramMap, Integer page, Integer limit);
	
	Result doModify(AuthPlatform authPlatform);
	
	Result doRemove( Long id );
}
