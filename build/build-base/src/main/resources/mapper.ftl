package ${mapperTypeInfo.packageName};
<#list imports as import>
import ${import};
</#list>
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import ${entityTypeInfo.name};
import com.dayon.common.model.DataMap;

public interface ${mapperTypeInfo.simpleName} {
	void add(${entityTypeInfo.simpleName} ${entityTypeInfo.javaName});
	void remove(<#list idTypeInfos as idTypeInfo><#if 0<idTypeInfo_index>,</#if>@Param("${idTypeInfo.javaName}") ${idTypeInfo.simpleName} ${idTypeInfo.javaName} </#list>);
	${entityTypeInfo.simpleName} get(<#list idTypeInfos as idTypeInfo><#if 0<idTypeInfo_index>,</#if>@Param("${idTypeInfo.javaName}") ${idTypeInfo.simpleName} ${idTypeInfo.javaName} </#list>);
	void modify(${entityTypeInfo.simpleName} ${entityTypeInfo.javaName});
	List<${entityTypeInfo.simpleName}> find(DataMap paramMap);
	long count(DataMap paramMap);
	List<${entityTypeInfo.simpleName}> find(DataMap paramMap, RowBounds rowBounds);
}
