package com.dayon.b2b2c.center.auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.UserRole;

public interface UserRoleMapper {
	void add(UserRole entity);
	void remove(Long id);
	void modify(Map<String,Object> paramMap);
	UserRole get(Long id);
	List<UserRole> find(Map<String,Object> paramMap);
	List<UserRole> find(Map<String,Object> paramMap,RowBounds rowBounds);
	void save(UserRole entity);
	long count(Map<String,Object> paramMap);
}
