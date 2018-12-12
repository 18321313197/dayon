package com.dayon.b2b2c.center.auth.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.RowBounds;
import com.dayon.b2b2c.api.auth.entity.AuthUserRole;
import com.dayon.b2b2c.api.auth.service.AuthUserRoleService;
import com.dayon.b2b2c.center.auth.dao.AuthUserRoleMapper;
import com.dayon.common.base.model.DataMap;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.dto.PageDataResult;
import com.dayon.common.base.dto.Paging;
@Service
public class AuthUserRoleServiceImpl implements AuthUserRoleService{
	private Logger logger=LogManager.getLogger();
	@Autowired
	private AuthUserRoleMapper authUserRoleMapper;

	@Override
	public Result doAdd(AuthUserRole authUserRole) {
		try {
			authUserRoleMapper.add(authUserRole);
			logger.debug("添加 AuthUserRole 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<AuthUserRole>> find(DataMap paramMap) {
		try {
			List<AuthUserRole> authUserRoles=authUserRoleMapper.find(paramMap);
			return new DataResult<>("查询成功",authUserRoles);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<Long> count(DataMap paramMap) {
		try {
			Long count= authUserRoleMapper.count(paramMap);
			return new DataResult<>("查询成功",count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<PageDataResult<AuthUserRole>> pageFind(DataMap paramMap, Integer page, Integer limit){
		try {
			RowBounds rowBounds =new RowBounds(page*limit-limit, limit);
			List<AuthUserRole> authUserRoles=authUserRoleMapper.find(paramMap,rowBounds);
			long count=authUserRoleMapper.count(paramMap);
			PageDataResult<AuthUserRole> pageFindResource =new PageDataResult<>();
			Paging paging=new Paging(page, limit, count);
			pageFindResource.setDatas(authUserRoles);
			pageFindResource.setPaging(paging);
			return new DataResult<>("查询成功",pageFindResource);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doAdd(List<AuthUserRole> authUserRoles) {
		try {
			for (AuthUserRole authUserRole : authUserRoles) {
				authUserRoleMapper.add(authUserRole);
			}
			logger.debug("添加 AuthUserRoles 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<AuthUserRole> get(Long id ) {
		try {
			AuthUserRole authUserRole=authUserRoleMapper.get(id);
			return new DataResult<>("查询成功",authUserRole);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doModify(AuthUserRole authUserRole){
		try {
			authUserRoleMapper.modify(authUserRole);
			logger.debug("修改 AuthUserRole 成功");
			return new Result("修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public Result doRemove(Long id ) {
		try {
			authUserRoleMapper.remove(id );
			logger.debug("删除 AuthUserRole 成功");
			return new Result("删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
}
