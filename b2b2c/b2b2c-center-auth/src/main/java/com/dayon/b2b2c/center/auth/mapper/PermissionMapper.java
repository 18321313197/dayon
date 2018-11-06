package com.dayon.b2b2c.center.auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.Permission;

public interface PermissionMapper {
	void add(Permission entity);
	void remove(Long id);
	void modify(Map<String,Object> paramMap);
	Permission get(Long id);
	List<Permission> find(Map<String,Object> paramMap);
	List<Permission> find(Map<String,Object> paramMap,RowBounds rowBounds);
	void save(Permission entity);
	long count(Map<String,Object> paramMap);
}
