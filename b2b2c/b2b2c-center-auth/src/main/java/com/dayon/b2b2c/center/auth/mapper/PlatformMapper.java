package com.dayon.b2b2c.center.auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.dayon.b2b2c.api.auth.entity.Platform;

public interface PlatformMapper {
	void add(Platform entity);
	void remove(Long id);
	void modify(Map<String,Object> paramMap);
	Platform get(Long id);
	List<Platform> find(Map<String,Object> paramMap);
	List<Platform> find(Map<String,Object> paramMap,RowBounds rowBounds);
	void save(Platform entity);
	long count(Map<String,Object> paramMap);
}
