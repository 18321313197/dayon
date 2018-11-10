package com.dayon.b2b2c.center.user.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.user.entity.Admin;
import com.dayon.common.model.DataMap;

public interface AdminMapper {
	void add(Admin entity);

	void remove(Long id);

	Admin get(Long id);

	void save(Admin entity);

	void modify(DataMap paramMap);

	List<Admin> find(DataMap paramMap);

	long count(DataMap paramMap);

	List<Admin> find(DataMap paramMap, RowBounds rowBounds);
}
