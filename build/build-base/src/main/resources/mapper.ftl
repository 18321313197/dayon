package ${package};

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import ${entityType};
import com.dayon.common.model.DataMap;

public interface ${entitySimpleType}Mapper {
	void add(${entitySimpleType} ${entityName});

	void remove(Long id);

	${entitySimpleType} get(Long id);

	void save(${entitySimpleType} entity);

	void modify(DataMap paramMap);

	List<${entitySimpleType}> find(DataMap paramMap);

	long count(DataMap paramMap);

	List<${entitySimpleType}> find(DataMap paramMap, RowBounds rowBounds);
}
