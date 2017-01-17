package com.hangtoo.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangtoo.auth.entity.SysRoleRel;
import com.hangtoo.auth.entity.SysRoleRel.RelType;
import com.hangtoo.auth.entity.SysUser;
import com.hangtoo.auth.mapper.SysUserMapper;
import com.hangtoo.auth.page.SysUserModel;
import com.hangtoo.base.service.BaseService;

@Service("sysUserService")
@Transactional
public class SysUserService extends BaseService<SysUser> {
	
	@Autowired
	private SysRoleRelService sysRoleRelService;

	@Override
	public void delete(Object... ids) throws Exception {
		super.delete(ids);
		for(Object id :  ids){
			sysRoleRelService.deleteByObjId((Integer)id, RelType.USER.key);
		}
	}
	/**
	 * 检查登录
	 * @param email
	 * @param pwd
	 * @return
	 */
	public SysUser queryLogin(String email,String pwd){
		SysUserModel model = new SysUserModel();
		model.setEmail(email);
		model.setPwd(pwd);
/*		SysUserDao<SysUser> mapper = getDao();
		mapper.queryById("");*/
		return getDao().queryLogin(model);
	}
	
	/**
	 * 查询邮箱总数，检查是否存在
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email){
		return getDao().getUserCountByEmail(email);
	}
	
	/**
	 * 查询用户权限
	 * @param userId
	 * @return
	 */
	public List<SysRoleRel> getUserRole(Integer userId){
		return sysRoleRelService.queryByObjId(userId,RelType.USER.key);
	}
	
	/**
	 * 添加用户权限
	 * @param userId
	 * @param roleIds
	 * @throws Exception
	 */
	public void addUserRole(Integer userId,Integer[] roleIds) throws Exception{
		if(userId == null ||  roleIds == null || roleIds.length < 1 ){ 
			return;
		}
		//清除关联关系
		sysRoleRelService.deleteByObjId(userId, RelType.USER.key);
		for(Integer roleId :roleIds ){ 
			SysRoleRel rel = new SysRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(userId);
			rel.setRelType(RelType.USER.key);
			sysRoleRelService.add(rel);
		}
	}
	
	
	@Autowired
    private SysUserMapper<SysUser> mapper;

		
	public SysUserMapper<SysUser> getDao() {
		return mapper;
	}
	/**
	 *查询全部用户
	 * @return
	 */
	public List<SysUser> queryAllList(){
		return getDao().queryAllList();
	}

}
