package com.dayon.b2b2c.center.auth.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.AuthManage;
import com.dayon.common.base.dto.model.DataMap;

public interface AuthManageMapper {
	Boolean add(AuthManage authManage);
	Boolean remove(@Param("id") Long id );
	AuthManage get(@Param("id") Long id );
	Boolean modify(AuthManage authManage);
	List<AuthManage> find(DataMap paramMap);
	Long count(DataMap paramMap);
	List<AuthManage> find(DataMap paramMap, RowBounds rowBounds);
}
