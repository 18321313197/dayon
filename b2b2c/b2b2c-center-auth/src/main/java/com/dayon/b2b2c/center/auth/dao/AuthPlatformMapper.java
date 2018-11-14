package com.dayon.b2b2c.center.auth.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.AuthPlatform;
import com.dayon.common.base.model.DataMap;

public interface AuthPlatformMapper {
	Boolean add(AuthPlatform authPlatform);
	Boolean remove(@Param("id") Long id );
	AuthPlatform get(@Param("id") Long id );
	Boolean modify(AuthPlatform authPlatform);
	List<AuthPlatform> find(DataMap paramMap);
	Long count(DataMap paramMap);
	List<AuthPlatform> find(DataMap paramMap, RowBounds rowBounds);
}
