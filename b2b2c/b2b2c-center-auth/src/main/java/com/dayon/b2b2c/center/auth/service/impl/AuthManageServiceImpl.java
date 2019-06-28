package com.dayon.b2b2c.center.auth.service.impl;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayon.b2b2c.api.auth.entity.AuthManage;
import com.dayon.b2b2c.api.auth.service.AuthManageService;
import com.dayon.b2b2c.center.auth.dao.AuthManageMapper;
import com.dayon.common.base.DataMap;
import com.dayon.common.base.DataResult;
import com.dayon.common.base.PageDataResult;
import com.dayon.common.base.Paging;
import com.dayon.common.base.Result;
@Service
public class AuthManageServiceImpl implements AuthManageService{
	private Logger logger=LogManager.getLogger();
	@Autowired
	private AuthManageMapper authManageMapper;

	@Override
	public Result doAdd(AuthManage authManage) {
		try {
			authManageMapper.add(authManage);
			logger.debug("添加 AuthManage 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<AuthManage>> find(DataMap paramMap) {
		try {
			List<AuthManage> authManages=authManageMapper.find(paramMap);
			return new DataResult<>("查询成功",authManages);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<Long> count(DataMap paramMap) {
		try {
			Long count= authManageMapper.count(paramMap);
			return new DataResult<>("查询成功",count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<PageDataResult<AuthManage>> pageFind(DataMap paramMap, Integer page, Integer limit){
		try {
			RowBounds rowBounds =new RowBounds(page*limit-limit, limit);
			List<AuthManage> authManages=authManageMapper.find(paramMap,rowBounds);
			long count=authManageMapper.count(paramMap);
			PageDataResult<AuthManage> pageFindResource =new PageDataResult<>();
			Paging paging=new Paging(page, limit, count);
			pageFindResource.setDatas(authManages);
			pageFindResource.setPaging(paging);
			return new DataResult<>("查询成功",pageFindResource);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doAdd(List<AuthManage> authManages) {
		try {
			for (AuthManage authManage : authManages) {
				authManageMapper.add(authManage);
			}
			logger.debug("添加 AuthManages 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<AuthManage> get(Long id ) {
		try {
			AuthManage authManage=authManageMapper.get(id);
			return new DataResult<>("查询成功",authManage);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doModify(AuthManage authManage){
		try {
			authManageMapper.modify(authManage);
			logger.debug("修改 AuthManage 成功");
			return new Result("修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public Result doRemove(Long id ) {
		try {
			authManageMapper.remove(id );
			logger.debug("删除 AuthManage 成功");
			return new Result("删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<AuthManage>> find(Long platformId) {
		if(platformId==null) {
			return new DataResult<>(1, "平台ID不能为空");
		}
		try {
			DataMap paramMap=new DataMap();
			paramMap.put("platformId", platformId);
			List<AuthManage> authManages=authManageMapper.find(paramMap);
			return new DataResult<>("查询成功",authManages);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
}
