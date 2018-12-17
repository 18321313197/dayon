package com.dayon.b2b2c.center.auth.service.impl;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayon.b2b2c.api.auth.entity.AuthPower;
import com.dayon.b2b2c.api.auth.service.AuthPowerService;
import com.dayon.b2b2c.center.auth.dao.AuthPowerMapper;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.PageDataResult;
import com.dayon.common.base.dto.Paging;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.dto.model.DataMap;
@Service
public class AuthPowerServiceImpl implements AuthPowerService{
	private Logger logger=LogManager.getLogger();
	@Autowired
	private AuthPowerMapper authPowerMapper;

	@Override
	public Result doAdd(AuthPower authPower) {
		try {
			authPowerMapper.add(authPower);
			logger.debug("添加 AuthPower 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<AuthPower>> find(DataMap paramMap) {
		try {
			List<AuthPower> authPowers=authPowerMapper.find(paramMap);
			return new DataResult<>("查询成功",authPowers);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<Long> count(DataMap paramMap) {
		try {
			Long count= authPowerMapper.count(paramMap);
			return new DataResult<>("查询成功",count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<PageDataResult<AuthPower>> pageFind(DataMap paramMap, Integer page, Integer limit){
		try {
			RowBounds rowBounds =new RowBounds(page*limit-limit, limit);
			List<AuthPower> authPowers=authPowerMapper.find(paramMap,rowBounds);
			long count=authPowerMapper.count(paramMap);
			PageDataResult<AuthPower> pageFindResource =new PageDataResult<>();
			Paging paging=new Paging(page, limit, count);
			pageFindResource.setDatas(authPowers);
			pageFindResource.setPaging(paging);
			return new DataResult<>("查询成功",pageFindResource);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doAdd(List<AuthPower> authPowers) {
		try {
			for (AuthPower authPower : authPowers) {
				authPowerMapper.add(authPower);
			}
			logger.debug("添加 AuthPowers 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<AuthPower> get(Long id ) {
		try {
			AuthPower authPower=authPowerMapper.get(id);
			return new DataResult<>("查询成功",authPower);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doModify(AuthPower authPower){
		try {
			authPowerMapper.modify(authPower);
			logger.debug("修改 AuthPower 成功");
			return new Result("修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public Result doRemove(Long id ) {
		try {
			authPowerMapper.remove(id );
			logger.debug("删除 AuthPower 成功");
			return new Result("删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<AuthPower>> findRolePower(Long roleId, Long platformId) {
		if(roleId==null || platformId==null) {
			return new DataResult<>(1,"角色ID和平台ID不能为空");
		}
		try {
			DataMap paramMap=new DataMap();
			paramMap.put("roleId", roleId);
			paramMap.put("platformId", platformId);
			List<AuthPower> authPowers=authPowerMapper.find(paramMap);
			return new DataResult<>("查询成功",authPowers);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}



}
