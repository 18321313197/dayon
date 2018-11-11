package com.dayon.b2b2c.center.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.user.entity.Admin;
import com.dayon.common.model.DataMap;

public interface AdminMapper {
	void add(Admin entity);

	void remove(@Param("id") Long id);

	Admin get(@Param("id") Long id);

	void modify(Admin entity);

	void update(DataMap paramMap);

	List<Admin> find(DataMap paramMap);

	long count(DataMap paramMap);

	List<Admin> find(DataMap paramMap, RowBounds rowBounds);
}
