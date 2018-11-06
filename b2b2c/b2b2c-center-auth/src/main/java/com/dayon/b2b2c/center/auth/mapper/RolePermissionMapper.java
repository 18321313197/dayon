package com.dayon.b2b2c.center.auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.RolePermission;

public interface RolePermissionMapper {
	void add(RolePermission entity);
	void remove(Long id);
	void modify(Map<String,Object> paramMap);
	RolePermission get(Long id);
	List<RolePermission> find(Map<String,Object> paramMap);
	List<RolePermission> find(Map<String,Object> paramMap,RowBounds rowBounds);
	void save(RolePermission entity);
	long count(Map<String,Object> paramMap);
}
