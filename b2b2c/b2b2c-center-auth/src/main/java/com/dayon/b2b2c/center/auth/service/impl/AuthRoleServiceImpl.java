package com.dayon.b2b2c.center.auth.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.RowBounds;
import com.dayon.b2b2c.api.auth.entity.AuthRole;
import com.dayon.b2b2c.api.auth.service.AuthRoleService;
import com.dayon.b2b2c.center.auth.dao.AuthRoleMapper;
import com.dayon.common.base.model.DataMap;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.dto.PageDataResult;
import com.dayon.common.base.dto.Paging;
@Service
public class AuthRoleServiceImpl implements AuthRoleService{
	private Logger logger=LogManager.getLogger();
	@Autowired
	private AuthRoleMapper authRoleMapper;

	@Override
	public Result doAdd(AuthRole authRole) {
		try {
			authRoleMapper.add(authRole);
			logger.debug("添加 AuthRole 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<AuthRole>> find(DataMap paramMap) {
		try {
			List<AuthRole> authRoles=authRoleMapper.find(paramMap);
			return new DataResult<>("查询成功",authRoles);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<Long> count(DataMap paramMap) {
		try {
			Long count= authRoleMapper.count(paramMap);
			return new DataResult<>("查询成功",count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<PageDataResult<AuthRole>> pageFind(DataMap paramMap, Integer page, Integer limit){
		try {
			RowBounds rowBounds =new RowBounds(page*limit-limit, limit);
			List<AuthRole> authRoles=authRoleMapper.find(paramMap,rowBounds);
			long count=authRoleMapper.count(paramMap);
			PageDataResult<AuthRole> pageFindResource =new PageDataResult<>();
			Paging paging=new Paging(page, limit, count);
			pageFindResource.setDatas(authRoles);
			pageFindResource.setPaging(paging);
			return new DataResult<>("查询成功",pageFindResource);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doAdd(List<AuthRole> authRoles) {
		try {
			for (AuthRole authRole : authRoles) {
				authRoleMapper.add(authRole);
			}
			logger.debug("添加 AuthRoles 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<AuthRole> get(Long id ) {
		try {
			AuthRole authRole=authRoleMapper.get(id);
			return new DataResult<>("查询成功",authRole);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doModify(AuthRole authRole){
		try {
			authRoleMapper.modify(authRole);
			logger.debug("修改 AuthRole 成功");
			return new Result("修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public Result doRemove(Long id ) {
		try {
			authRoleMapper.remove(id );
			logger.debug("删除 AuthRole 成功");
			return new Result("删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<AuthRole>> find(Long userId, Long platformId) {
		if(userId==null || platformId==null) {
			return new DataResult<>(1,"用户ID和平台ID不能为空");
		}
		try {
			DataMap paramMap=new DataMap();
			paramMap.put("userId", userId);
			paramMap.put("platformId", platformId);
			List<AuthRole> authRoles=authRoleMapper.find(paramMap);
			return new DataResult<>("查询成功",authRoles);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
}
