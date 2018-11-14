package com.dayon.b2b2c.center.auth.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.RowBounds;
import com.dayon.b2b2c.api.auth.entity.AuthPlatform;
import com.dayon.b2b2c.api.auth.service.AuthPlatformService;
import com.dayon.b2b2c.center.auth.dao.AuthPlatformMapper;
import com.dayon.common.base.model.DataMap;
import com.dayon.common.base.dto.DataResult;
import com.dayon.common.base.dto.Result;
import com.dayon.common.base.dto.PageFindResource;
import com.dayon.common.base.dto.Paging;
@Service
public class AuthPlatformServiceImpl implements AuthPlatformService{
	private Logger logger=LogManager.getLogger();
	@Autowired
	private AuthPlatformMapper authPlatformMapper;

	@Override
	public Result doAdd(AuthPlatform authPlatform) {
		try {
			authPlatformMapper.add(authPlatform);
			logger.debug("添加 AuthPlatform 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<List<AuthPlatform>> find(DataMap paramMap) {
		try {
			List<AuthPlatform> authPlatforms=authPlatformMapper.find(paramMap);
			return new DataResult<>("查询成功",authPlatforms);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<Long> count(DataMap paramMap) {
		try {
			Long count= authPlatformMapper.count(paramMap);
			return new DataResult<>("查询成功",count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public DataResult<PageFindResource<AuthPlatform>> pageFind(DataMap paramMap, Integer page, Integer limit){
		try {
			RowBounds rowBounds =new RowBounds(page*limit-limit, limit);
			List<AuthPlatform> authPlatforms=authPlatformMapper.find(paramMap,rowBounds);
			long count=authPlatformMapper.count(paramMap);
			PageFindResource<AuthPlatform> pageFindResource =new PageFindResource<>();
			Paging paging=new Paging(page, limit, count);
			pageFindResource.setDatas(authPlatforms);
			pageFindResource.setPaging(paging);
			return new DataResult<>("查询成功",pageFindResource);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doAdd(List<AuthPlatform> authPlatforms) {
		try {
			for (AuthPlatform authPlatform : authPlatforms) {
				authPlatformMapper.add(authPlatform);
			}
			logger.debug("添加 AuthPlatforms 成功");
			return new Result("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public DataResult<AuthPlatform> get(Long id ) {
		try {
			AuthPlatform authPlatform=authPlatformMapper.get(id);
			return new DataResult<>("查询成功",authPlatform);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new DataResult<>(-1,"未知异常");
		}
	}
	@Override
	public Result doModify(AuthPlatform authPlatform){
		try {
			authPlatformMapper.modify(authPlatform);
			logger.debug("修改 AuthPlatform 成功");
			return new Result("修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
	@Override
	public Result doRemove(Long id ) {
		try {
			authPlatformMapper.remove(id );
			logger.debug("删除 AuthPlatform 成功");
			return new Result("删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new Result(-1,"未知异常");
		}
	}
}
