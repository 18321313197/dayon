package com.dayon.b2b2c.center.auth.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.AuthPower;
import com.dayon.common.base.model.DataMap;

public interface AuthPowerMapper {
	Boolean add(AuthPower authPower);
	Boolean remove(@Param("id") Long id );
	AuthPower get(@Param("id") Long id );
	Boolean modify(AuthPower authPower);
	List<AuthPower> find(DataMap paramMap);
	Long count(DataMap paramMap);
	List<AuthPower> find(DataMap paramMap, RowBounds rowBounds);
	
	List<AuthPower> findRolePower(@Param("roleId")Long roleId, @Param("platformId")Long platformId);
	
}
