package com.dayon.b2b2c.api.user.service;
import java.util.List;
import com.dayon.b2b2c.api.user.entity.UserAdmin;
import com.dayon.common.base.DataMap;
import com.dayon.common.base.DataResult;
import com.dayon.common.base.PageDataResult;
import com.dayon.common.base.Result;

public interface UserAdminService {
	DataResult<UserAdmin> get( Long id );

	DataResult<List<UserAdmin>> find(DataMap paramMap);

	DataResult<Long> count(DataMap paramMap);

	Result doAdd(UserAdmin userAdmin);

	Result doAdd(List<UserAdmin> userAdmins);

	DataResult<PageDataResult<UserAdmin>> pageFind(DataMap paramMap, Integer page, Integer limit);
	
	Result doModify(UserAdmin userAdmin);
	
	Result doRemove( Long id );
	
	DataResult<UserAdmin> get(String username,String password);
}
