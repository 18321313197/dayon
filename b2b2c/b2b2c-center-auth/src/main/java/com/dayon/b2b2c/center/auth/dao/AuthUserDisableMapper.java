package com.dayon.b2b2c.center.auth.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.AuthUserDisable;
import com.dayon.common.base.model.DataMap;

public interface AuthUserDisableMapper {
	Boolean add(AuthUserDisable authUserDisable);
	Boolean remove(@Param("id") Long id );
	AuthUserDisable get(@Param("id") Long id );
	Boolean modify(AuthUserDisable authUserDisable);
	List<AuthUserDisable> find(DataMap paramMap);
	Long count(DataMap paramMap);
	List<AuthUserDisable> find(DataMap paramMap, RowBounds rowBounds);
}
