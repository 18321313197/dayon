package com.dayon.common.base.dao;

import java.util.List;

import com.dayon.common.base.model.DataMap;
import com.dayon.common.base.po.Entity;

public interface Dao<T extends Entity> {
	T get(Long id);

	List<T> find(DataMap param);

	boolean modify(T entity);

	boolean remove(long id);

	boolean add(T entity);
}
