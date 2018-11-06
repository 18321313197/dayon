package com.dayon.b2b2c.center.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.user.entity.Admin;

public interface AdminMapper {
	void add(Admin entity);
	void remove(Long id);
	Admin get(Long id);
	void save(Admin entity);
	void modify(Map<String,Object> paramMap);
	List<Admin> find(Map<String,Object> paramMap);
	long count(Map<String,Object> paramMap);
	List<Admin> find(Map<String,Object> paramMap,RowBounds rowBounds);
}
