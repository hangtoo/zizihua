package com.hangtoo.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangtoo.auth.entity.SysRole;
import com.hangtoo.auth.entity.SysRoleRel;
import com.hangtoo.auth.entity.SysRoleRel.RelType;
import com.hangtoo.auth.mapper.SysRoleMapper;
import com.hangtoo.base.entity.BaseEntity.STATE;
import com.hangtoo.base.service.BaseService;

@Service("sysRoleService")
@Transactional
public class SysRoleService extends BaseService<SysRole> {
	
	@Autowired
	private SysRoleRelService sysRoleRelService;
	
	/**
	 * 添加角色&菜单关系
	 */
	public void addRoleMenuRel(Integer roleId,Integer[] menuIds) throws Exception{
		if(roleId == null ||  menuIds == null || menuIds.length < 1 ){ 
			return;
		}
		for(Integer menuid :menuIds ){ 
			SysRoleRel rel = new SysRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(menuid);
			rel.setRelType(RelType.MENU.key);
			sysRoleRelService.add(rel);
		}
	}
		
	/**
	 * 添加角色&菜单关系
	 */
	public void addRoleBtnRel(Integer roleId,Integer[] btnIds) throws Exception{
		if(roleId == null ||  btnIds == null || btnIds.length < 1 ){ 
			return;
		}
		for(Integer btnid : btnIds ){ 
			SysRoleRel rel = new SysRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(btnid);
			rel.setRelType(RelType.BTN.key);
			sysRoleRelService.add(rel);
		}
	}
		
	
	/**
	 * 添加
	 * @param role
	 * @param menuIds
	 * @throws Exception
	 */
	public void add(SysRole role,Integer[] menuIds,Integer[] btnIds) throws Exception {
		super.add(role);
		addRoleMenuRel(role.getId(),menuIds);
		addRoleBtnRel(role.getId(),btnIds);
	}

	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void delete(Object... ids) throws Exception {
		super.delete(ids);
		for(Object id : ids){
			//清除关联关系
			sysRoleRelService.deleteByRoleId((Integer)id);
		}
	}

	/**
	 * 修改
	 * @param role
	 * @param menuIds
	 * @throws Exception
	 */
	public void update(SysRole role,Integer[] menuIds,Integer[] btnIds) throws Exception {
		super.update(role);
		//如果角色被禁用则删除关联的用户关系
		if(STATE.DISABLE.key == role.getState()){
			sysRoleRelService.deleteByRoleId(role.getId(),RelType.USER.key);
		}
		//清除关联关系
		sysRoleRelService.deleteByRoleId(role.getId(),RelType.MENU.key);
		sysRoleRelService.deleteByRoleId(role.getId(),RelType.BTN.key);
			addRoleMenuRel(role.getId(),menuIds);
			addRoleBtnRel(role.getId(),btnIds);
		
	}

	
	/**
	 *查询全部有效的权限
	 * @return
	 */
	public List<SysRole> queryAllList(){
		return getDao().queryAllList();
	}

	

	/**
	 *查询全部有效的权限
	 * @return
	 */
	public List<SysRole> queryByUserid(Integer userid){
		return getDao().queryByUserid(userid);
	}

	@Autowired
    private SysRoleMapper<SysRole> mapper;

		
	public SysRoleMapper<SysRole> getDao() {
		return mapper;
	}

}
