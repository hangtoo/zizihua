package com.hangtoo.auth.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hangtoo.base.dao.BaseDao;

/**
 * SysRole Mapper
 * @author Administrator
 *
 */
@Component
public interface SysRoleMapper<T> extends BaseDao<T> {
	
	/**
	 *查询全部有效的权限
	 * @return
	 */
	public List<T> queryAllList();
	
	
	/**
	 *根据用户Id查询权限
	 * @return
	 */
	public List<T> queryByUserid(Integer userid);
}
