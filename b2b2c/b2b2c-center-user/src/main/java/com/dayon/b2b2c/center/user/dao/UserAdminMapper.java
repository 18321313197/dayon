package com.dayon.b2b2c.center.user.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.user.entity.UserAdmin;
import com.dayon.common.base.dto.model.DataMap;

public interface UserAdminMapper {
	Boolean add(UserAdmin userAdmin);
	Boolean remove(@Param("id") Long id );
	UserAdmin get(@Param("id") Long id );
	Boolean modify(UserAdmin userAdmin);
	List<UserAdmin> find(DataMap paramMap);
	Long count(DataMap paramMap);
	List<UserAdmin> find(DataMap paramMap, RowBounds rowBounds);
}
