package com.hangtoo.auth.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hangtoo.auth.page.SysUserModel;
import com.hangtoo.base.dao.BaseDao;

/**
 * SysUser Mapper
 * @author Administrator
 *
 */
@Component
public interface SysUserMapper<T> extends BaseDao<T> {
	
	/**
	 * 检查登录
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(SysUserModel model);
	
	
	/**
	 * 查询邮箱总数，检查是否存在
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email);


	public List<T> queryAllList();
}
