package ${serviceImplTypeInfo.packageName};
<#list imports as import>
import ${import};
</#list>
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.RowBounds;
import ${entityTypeInfo.name};
import ${serviceTypeInfo.name};
import ${mapperTypeInfo.name};
import com.dayon.common.base.model.DataMap;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.dto.PageFindResource;
import com.dayon.common.base.dto.Paging;
@Service
public class ${serviceImplTypeInfo.simpleName} implements ${serviceTypeInfo.simpleName}{
	private Logger logger=LogManager.getLogger();
	@Autowired
	private ${mapperTypeInfo.simpleName} ${mapperTypeInfo.javaName};

	@Override
	public Result doAdd(${entityTypeInfo.simpleName} ${entityTypeInfo.javaName}) {
		try {
			${mapperTypeInfo.javaName}.add(${entityTypeInfo.javaName});
			logger.debug("添加 ${entityTypeInfo.simpleName} 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<${entityTypeInfo.simpleName}>> find(DataMap paramMap) {
		try {
			List<${entityTypeInfo.simpleName}> ${entityTypeInfo.javaName}s=${mapperTypeInfo.javaName}.find(paramMap);
			return new DataResult<>("查询成功",${entityTypeInfo.javaName}s);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<Long> count(DataMap paramMap) {
		try {
			Long count= ${mapperTypeInfo.javaName}.count(paramMap);
			return new DataResult<>("查询成功",count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<PageFindResource<${entityTypeInfo.simpleName}>> pageFind(DataMap paramMap, Integer page, Integer limit){
		try {
			RowBounds rowBounds =new RowBounds(page*limit-limit, limit);
			List<${entityTypeInfo.simpleName}> ${entityTypeInfo.javaName}s=${mapperTypeInfo.javaName}.find(paramMap,rowBounds);
			long count=${mapperTypeInfo.javaName}.count(paramMap);
			PageFindResource<${entityTypeInfo.simpleName}> pageFindResource =new PageFindResource<>();
			Paging paging=new Paging(page, limit, count);
			pageFindResource.setDatas(${entityTypeInfo.javaName}s);
			pageFindResource.setPaging(paging);
			return new DataResult<>("查询成功",pageFindResource);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doAdd(List<${entityTypeInfo.simpleName}> ${entityTypeInfo.javaName}s) {
		try {
			for (${entityTypeInfo.simpleName} ${entityTypeInfo.javaName} : ${entityTypeInfo.javaName}s) {
				${mapperTypeInfo.javaName}.add(${entityTypeInfo.javaName});
			}
			logger.debug("添加 ${entityTypeInfo.simpleName}s 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<${entityTypeInfo.simpleName}> get(<#list idTypeInfos as idTypeInfo><#if 0<idTypeInfo_index>,</#if>${idTypeInfo.simpleName} ${idTypeInfo.javaName} </#list>) {
		try {
			${entityTypeInfo.simpleName} ${entityTypeInfo.javaName}=${mapperTypeInfo.javaName}.get(<#list idTypeInfos as idTypeInfo><#if 0<idTypeInfo_index>,</#if>${idTypeInfo.javaName}</#list>);
			return new DataResult<>("查询成功",${entityTypeInfo.javaName});
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doModify(${entityTypeInfo.simpleName} ${entityTypeInfo.javaName}){
		try {
			${mapperTypeInfo.javaName}.modify(${entityTypeInfo.javaName});
			logger.debug("修改 ${entityTypeInfo.simpleName} 成功");
			return new Result("修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public Result doRemove(<#list idTypeInfos as idTypeInfo><#if 0<idTypeInfo_index>,</#if>${idTypeInfo.simpleName} ${idTypeInfo.javaName} </#list>) {
		try {
			${mapperTypeInfo.javaName}.remove(<#list idTypeInfos as idTypeInfo><#if 0<idTypeInfo_index>,</#if>${idTypeInfo.javaName} </#list>);
			logger.debug("删除 ${entityTypeInfo.simpleName} 成功");
			return new Result("删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
}
