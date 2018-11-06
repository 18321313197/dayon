package com.dayon.b2b2c.center.auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.Role;

public interface RoleMapper {
	void add(Role entity);
	void remove(Long id);
	void modify(Map<String,Object> paramMap);
	Role get(Long id);
	List<Role> find(Map<String,Object> paramMap);
	List<Role> find(Map<String,Object> paramMap,RowBounds rowBounds);
	void save(Role entity);
	long count(Map<String,Object> paramMap);
}
