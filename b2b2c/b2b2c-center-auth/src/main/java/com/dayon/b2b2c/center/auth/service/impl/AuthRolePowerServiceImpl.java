package com.dayon.b2b2c.center.auth.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.RowBounds;
import com.dayon.b2b2c.api.auth.entity.AuthRolePower;
import com.dayon.b2b2c.api.auth.service.AuthRolePowerService;
import com.dayon.b2b2c.center.auth.dao.AuthRolePowerMapper;
import com.dayon.common.base.DataMap;
import com.dayon.common.base.DataResult;
import com.dayon.common.base.PageDataResult;
import com.dayon.common.base.Paging;
import com.dayon.common.base.Result;
@Service
public class AuthRolePowerServiceImpl implements AuthRolePowerService{
	private Logger logger=LogManager.getLogger();
	@Autowired
	private AuthRolePowerMapper authRolePowerMapper;

	@Override
	public Result doAdd(AuthRolePower authRolePower) {
		try {
			authRolePowerMapper.add(authRolePower);
			logger.debug("添加 AuthRolePower 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<AuthRolePower>> find(DataMap paramMap) {
		try {
			List<AuthRolePower> authRolePowers=authRolePowerMapper.find(paramMap);
			return new DataResult<>("查询成功",authRolePowers);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<Long> count(DataMap paramMap) {
		try {
			Long count= authRolePowerMapper.count(paramMap);
			return new DataResult<>("查询成功",count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<PageDataResult<AuthRolePower>> pageFind(DataMap paramMap, Integer page, Integer limit){
		try {
			RowBounds rowBounds =new RowBounds(page*limit-limit, limit);
			List<AuthRolePower> authRolePowers=authRolePowerMapper.find(paramMap,rowBounds);
			long count=authRolePowerMapper.count(paramMap);
			PageDataResult<AuthRolePower> pageFindResource =new PageDataResult<>();
			Paging paging=new Paging(page, limit, count);
			pageFindResource.setDatas(authRolePowers);
			pageFindResource.setPaging(paging);
			return new DataResult<>("查询成功",pageFindResource);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doAdd(List<AuthRolePower> authRolePowers) {
		try {
			for (AuthRolePower authRolePower : authRolePowers) {
				authRolePowerMapper.add(authRolePower);
			}
			logger.debug("添加 AuthRolePowers 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<AuthRolePower> get(Long id ) {
		try {
			AuthRolePower authRolePower=authRolePowerMapper.get(id);
			return new DataResult<>("查询成功",authRolePower);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doModify(AuthRolePower authRolePower){
		try {
			authRolePowerMapper.modify(authRolePower);
			logger.debug("修改 AuthRolePower 成功");
			return new Result("修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public Result doRemove(Long id ) {
		try {
			authRolePowerMapper.remove(id );
			logger.debug("删除 AuthRolePower 成功");
			return new Result("删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
}
