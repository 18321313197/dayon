package com.dayon.b2b2c.center.auth.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.RowBounds;
import com.dayon.b2b2c.api.auth.entity.AuthUserDisable;
import com.dayon.b2b2c.api.auth.service.AuthUserDisableService;
import com.dayon.b2b2c.center.auth.dao.AuthUserDisableMapper;
import com.dayon.common.base.model.DataMap;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.dto.PageDataResult;
import com.dayon.common.base.dto.Paging;
@Service
public class AuthUserDisableServiceImpl implements AuthUserDisableService{
	private Logger logger=LogManager.getLogger();
	@Autowired
	private AuthUserDisableMapper authUserDisableMapper;

	@Override
	public Result doAdd(AuthUserDisable authUserDisable) {
		try {
			authUserDisableMapper.add(authUserDisable);
			logger.debug("添加 AuthUserDisable 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<AuthUserDisable>> find(DataMap paramMap) {
		try {
			List<AuthUserDisable> authUserDisables=authUserDisableMapper.find(paramMap);
			return new DataResult<>("查询成功",authUserDisables);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<Long> count(DataMap paramMap) {
		try {
			Long count= authUserDisableMapper.count(paramMap);
			return new DataResult<>("查询成功",count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<PageDataResult<AuthUserDisable>> pageFind(DataMap paramMap, Integer page, Integer limit){
		try {
			RowBounds rowBounds =new RowBounds(page*limit-limit, limit);
			List<AuthUserDisable> authUserDisables=authUserDisableMapper.find(paramMap,rowBounds);
			long count=authUserDisableMapper.count(paramMap);
			PageDataResult<AuthUserDisable> pageFindResource =new PageDataResult<>();
			Paging paging=new Paging(page, limit, count);
			pageFindResource.setDatas(authUserDisables);
			pageFindResource.setPaging(paging);
			return new DataResult<>("查询成功",pageFindResource);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doAdd(List<AuthUserDisable> authUserDisables) {
		try {
			for (AuthUserDisable authUserDisable : authUserDisables) {
				authUserDisableMapper.add(authUserDisable);
			}
			logger.debug("添加 AuthUserDisables 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<AuthUserDisable> get(Long id ) {
		try {
			AuthUserDisable authUserDisable=authUserDisableMapper.get(id);
			return new DataResult<>("查询成功",authUserDisable);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doModify(AuthUserDisable authUserDisable){
		try {
			authUserDisableMapper.modify(authUserDisable);
			logger.debug("修改 AuthUserDisable 成功");
			return new Result("修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public Result doRemove(Long id ) {
		try {
			authUserDisableMapper.remove(id );
			logger.debug("删除 AuthUserDisable 成功");
			return new Result("删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
}
