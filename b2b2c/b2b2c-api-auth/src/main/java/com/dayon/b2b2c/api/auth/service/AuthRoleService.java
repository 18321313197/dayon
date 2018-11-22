package com.dayon.b2b2c.api.auth.service;
import java.util.List;
import com.dayon.b2b2c.api.auth.entity.AuthRole;

import com.dayon.common.base.model.DataMap;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.dto.PageFindResource;

public interface AuthRoleService {
	DataResult<AuthRole> get( Long id );

	DataResult<List<AuthRole>> find(DataMap paramMap);

	DataResult<Long> count(DataMap paramMap);

	Result doAdd(AuthRole authRole);

	Result doAdd(List<AuthRole> authRoles);

	DataResult<PageFindResource<AuthRole>> pageFind(DataMap paramMap, Integer page, Integer limit);
	
	Result doModify(AuthRole authRole);
	
	Result doRemove( Long id );
	
	DataResult<List<AuthRole>> find(Long userId,Long platformId);
}
