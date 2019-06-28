package com.dayon.b2b2c.api.auth.service;
import java.util.List;
import com.dayon.b2b2c.api.auth.entity.AuthUserRole;
import com.dayon.common.base.DataMap;
import com.dayon.common.base.DataResult;
import com.dayon.common.base.PageDataResult;
import com.dayon.common.base.Result;

public interface AuthUserRoleService {
	DataResult<AuthUserRole> get( Long id );

	DataResult<List<AuthUserRole>> find(DataMap paramMap);

	DataResult<Long> count(DataMap paramMap);

	Result doAdd(AuthUserRole authUserRole);

	Result doAdd(List<AuthUserRole> authUserRoles);

	DataResult<PageDataResult<AuthUserRole>> pageFind(DataMap paramMap, Integer page, Integer limit);
	
	Result doModify(AuthUserRole authUserRole);
	
	Result doRemove( Long id );
	
	
}
