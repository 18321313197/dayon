package com.dayon.b2b2c.center.auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.Manage;

public interface ManageMapper {
	void add(Manage entity);
	void remove(Long id);
	void modify(Map<String,Object> paramMap);
	
	Manage get(Long id);
	List<Manage> find(Map<String,Object> paramMap);
	List<Manage> find(Map<String,Object> paramMap,RowBounds rowBounds);
	void save(Manage entity);
	long count(Map<String,Object> paramMap);
	
}
