package com.dayon.b2b2c.center.auth.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.AuthRolePower;
import com.dayon.common.base.dto.model.DataMap;

public interface AuthRolePowerMapper {
	Boolean add(AuthRolePower authRolePower);
	Boolean remove(@Param("id") Long id );
	AuthRolePower get(@Param("id") Long id );
	Boolean modify(AuthRolePower authRolePower);
	List<AuthRolePower> find(DataMap paramMap);
	Long count(DataMap paramMap);
	List<AuthRolePower> find(DataMap paramMap, RowBounds rowBounds);
}
