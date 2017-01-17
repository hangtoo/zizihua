package com.hangtoo.auth.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangtoo.auth.entity.SysRoleRel;
import com.hangtoo.auth.mapper.SysRoleRelMapper;
import com.hangtoo.base.service.BaseService;

@Service("sysRoleRelService")
@Transactional
public class SysRoleRelService extends BaseService<SysRoleRel> {
	
	public List<SysRoleRel> queryByRoleId(Integer roleId,Integer relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("relType", relType);
		return getDao().queryByRoleId(param);
	}
	
	
	public List<SysRoleRel> queryByObjId(Integer objId,Integer relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objId", objId);
		param.put("relType", relType);
		return getDao().queryByObjId(param);
	}
	
	/**
	 * 根据关联对象id,关联类型删除 
	 * @param objId
	 * @param relType
	 */
	public void deleteByObjId(Integer objId,Integer relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objId", objId);
		param.put("relType", relType);
		getDao().deleteByObjId(param);
	}
	
	/**
	 * 根据角色id删除 
	 * @param roleId
	 */
	public void deleteByRoleId(Integer roleId){
		deleteByRoleId(roleId,null);
	}
	
	/**
	 *  根据角色id,关联类型删除 
	 * @param roleId
	 * @param relType
	 */
	public void deleteByRoleId(Integer roleId,Integer relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("relType", relType);
		getDao().deleteByRoleId(param);
	}

	@Autowired
    private SysRoleRelMapper<SysRoleRel> mapper;
		
	public SysRoleRelMapper<SysRoleRel> getDao() {
		return mapper;
	}

}