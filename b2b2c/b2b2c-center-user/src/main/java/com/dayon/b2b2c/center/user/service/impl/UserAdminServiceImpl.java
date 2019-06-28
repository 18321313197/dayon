package com.dayon.b2b2c.center.user.service.impl;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayon.b2b2c.api.user.entity.UserAdmin;
import com.dayon.b2b2c.api.user.service.UserAdminService;
import com.dayon.b2b2c.center.user.dao.UserAdminMapper;
import com.dayon.common.base.DataMap;
import com.dayon.common.base.DataResult;
import com.dayon.common.base.PageDataResult;
import com.dayon.common.base.Paging;
import com.dayon.common.base.Result;
@Service
public class UserAdminServiceImpl implements UserAdminService{
	private Logger logger=LogManager.getLogger();
	@Autowired
	private UserAdminMapper userAdminMapper;

	@Override
	public Result doAdd(UserAdmin userAdmin) {
		try {
			userAdminMapper.add(userAdmin);
			logger.debug("添加 UserAdmin 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<UserAdmin>> find(DataMap paramMap) {
		try {
			List<UserAdmin> userAdmins=userAdminMapper.find(paramMap);
			return new DataResult<>("查询成功",userAdmins);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<Long> count(DataMap paramMap) {
		try {
			Long count= userAdminMapper.count(paramMap);
			return new DataResult<>("查询成功",count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<PageDataResult<UserAdmin>> pageFind(DataMap paramMap, Integer page, Integer limit){
		try {
			RowBounds rowBounds =new RowBounds(page*limit-limit, limit);
			List<UserAdmin> userAdmins=userAdminMapper.find(paramMap,rowBounds);
			long count=userAdminMapper.count(paramMap);
			PageDataResult<UserAdmin> pageFindResource =new PageDataResult<>();
			Paging paging=new Paging(page, limit, count);
			pageFindResource.setDatas(userAdmins);
			pageFindResource.setPaging(paging);
			return new DataResult<>("查询成功",pageFindResource);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doAdd(List<UserAdmin> userAdmins) {
		try {
			for (UserAdmin userAdmin : userAdmins) {
				userAdminMapper.add(userAdmin);
			}
			logger.debug("添加 UserAdmins 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<UserAdmin> get(Long id ) {
		try {
			UserAdmin userAdmin=userAdminMapper.get(id);
			return new DataResult<>("查询成功",userAdmin);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doModify(UserAdmin userAdmin){
		try {
			userAdminMapper.modify(userAdmin);
			logger.debug("修改 UserAdmin 成功");
			return new Result("修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public Result doRemove(Long id ) {
		try {
			userAdminMapper.remove(id );
			logger.debug("删除 UserAdmin 成功");
			return new Result("删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<UserAdmin> get(String username, String password) {
		try {
			if(username==null || password==null) {
				return new DataResult<>(1,"用户名和密码不能为空");
			}
			DataMap paramMap=new DataMap();
			paramMap.put("username", username);
			List<UserAdmin> userAdmins=userAdminMapper.find(paramMap);
			if(userAdmins.size()==0) {
				return new DataResult<>(2,"用户不存在");
			}
			UserAdmin ua=userAdmins.get(0);
			if(!ua.getPassword().equals(password)) {
				return new DataResult<>(2,"密码错误");
			}
			return new DataResult<>("登录成功",ua);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
}
