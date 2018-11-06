package com.dayon.b2b2c.center.auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.UserDisable;

public interface UserDisableMapper {
	void add(UserDisable entity);
	void remove(Long id);
	void modify(Map<String,Object> paramMap);
	UserDisable get(Long id);
	List<UserDisable> find(Map<String,Object> paramMap);
	List<UserDisable> find(Map<String,Object> paramMap,RowBounds rowBounds);
	void save(UserDisable entity);
	long count(Map<String,Object> paramMap);
}
