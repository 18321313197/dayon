package ${serviceTypeInfo.packageName};
<#list imports as import>
import ${import};
</#list>
import java.util.List;
import ${entityTypeInfo.name};

import com.dayon.common.base.model.DataMap;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.dto.PageDataResult;

public interface ${serviceTypeInfo.simpleName} {
	DataResult<${entityTypeInfo.simpleName}> get(<#list idTypeInfos as idTypeInfo><#if 0<idTypeInfo_index>,</#if> ${idTypeInfo.simpleName} ${idTypeInfo.javaName} </#list>);

	DataResult<List<${entityTypeInfo.simpleName}>> find(DataMap paramMap);

	DataResult<Long> count(DataMap paramMap);

	Result doAdd(${entityTypeInfo.simpleName} ${entityTypeInfo.javaName});

	Result doAdd(List<${entityTypeInfo.simpleName}> ${entityTypeInfo.javaName}s);

	PageDataResult<${entityTypeInfo.simpleName}> pageFind(DataMap paramMap, Integer page, Integer limit);
	
	Result doModify(${entityTypeInfo.simpleName} ${entityTypeInfo.javaName});
	
	Result doRemove(<#list idTypeInfos as idTypeInfo><#if 0<idTypeInfo_index>,</#if> ${idTypeInfo.simpleName} ${idTypeInfo.javaName} </#list>);
}
