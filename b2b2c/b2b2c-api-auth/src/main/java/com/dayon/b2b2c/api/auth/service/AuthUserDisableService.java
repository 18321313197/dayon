package com.dayon.b2b2c.api.auth.service;
import java.util.List;
import com.dayon.b2b2c.api.auth.entity.AuthUserDisable;
import com.dayon.common.base.DataMap;
import com.dayon.common.base.DataResult;
import com.dayon.common.base.PageDataResult;
import com.dayon.common.base.Result;

public interface AuthUserDisableService {
	DataResult<AuthUserDisable> get( Long id );

	DataResult<List<AuthUserDisable>> find(DataMap paramMap);

	DataResult<Long> count(DataMap paramMap);

	Result doAdd(AuthUserDisable authUserDisable);

	Result doAdd(List<AuthUserDisable> authUserDisables);

	DataResult<PageDataResult<AuthUserDisable>> pageFind(DataMap paramMap, Integer page, Integer limit);
	
	Result doModify(AuthUserDisable authUserDisable);
	
	Result doRemove( Long id );
}
