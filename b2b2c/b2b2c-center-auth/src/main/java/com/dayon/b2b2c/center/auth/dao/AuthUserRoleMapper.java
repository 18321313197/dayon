package com.dayon.b2b2c.center.auth.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.AuthUserRole;
import com.dayon.common.base.dto.model.DataMap;

public interface AuthUserRoleMapper {
	Boolean add(AuthUserRole authUserRole);
	Boolean remove(@Param("id") Long id );
	AuthUserRole get(@Param("id") Long id );
	Boolean modify(AuthUserRole authUserRole);
	List<AuthUserRole> find(DataMap paramMap);
	Long count(DataMap paramMap);
	List<AuthUserRole> find(DataMap paramMap, RowBounds rowBounds);
}
