package com.dayon.b2b2c.center.auth.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.AuthRole;
import com.dayon.common.base.DataMap;

public interface AuthRoleMapper {
	Boolean add(AuthRole authRole);
	Boolean remove(@Param("id") Long id );
	AuthRole get(@Param("id") Long id );
	Boolean modify(AuthRole authRole);
	List<AuthRole> find(DataMap paramMap);
	Long count(DataMap paramMap);
	List<AuthRole> find(DataMap paramMap, RowBounds rowBounds);
}
