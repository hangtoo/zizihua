package com.hangtoo.auth.mapper;

import java.util.List;

import com.hangtoo.auth.entity.SysRoleRel;
import com.hangtoo.base.dao.BaseDao;

/**
 * SysRoleRel Mapper
 * @author Administrator
 *
 */
public interface SysRoleRelMapper<T> extends BaseDao<T> {
	
	public void deleteByRoleId(java.util.Map<String, Object> param);
	
	public void deleteByObjId(java.util.Map<String, Object> param);
	
	
	public List<SysRoleRel> queryByRoleId(java.util.Map<String, Object> param);
	
	
	public List<SysRoleRel> queryByObjId(java.util.Map<String, Object> param);
	
	
}
