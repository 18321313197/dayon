package com.dayon.b2b2c.api.auth.service;
import java.util.List;
import com.dayon.b2b2c.api.auth.entity.AuthManage;
import com.dayon.common.base.DataMap;
import com.dayon.common.base.DataResult;
import com.dayon.common.base.PageDataResult;
import com.dayon.common.base.Result;

public interface AuthManageService {
	DataResult<AuthManage> get( Long id );

	DataResult<List<AuthManage>> find(DataMap paramMap);

	DataResult<Long> count(DataMap paramMap);

	Result doAdd(AuthManage authManage);

	Result doAdd(List<AuthManage> authManages);

	DataResult<PageDataResult<AuthManage>> pageFind(DataMap paramMap, Integer page, Integer limit);
	
	Result doModify(AuthManage authManage);
	
	Result doRemove( Long id );
	
	DataResult<List<AuthManage>> find(Long platformId);
}
