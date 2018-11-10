package com.dayon.framework.center.mybatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.dayon.common.model.DataMap;

public interface EntityMapper<T> {
	void add(T entity);
	void remove(Long id);
	T get(Long id);
	void save(T entity);
	void modify(DataMap paramMap);
	long count(DataMap paramMap);
	List<T> find(DataMap paramMap);
	List<T> find(DataMap paramMap,RowBounds rowBounds);
}
