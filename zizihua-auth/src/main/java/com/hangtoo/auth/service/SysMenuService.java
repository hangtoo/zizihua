package com.hangtoo.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangtoo.auth.entity.SysMenu;
import com.hangtoo.auth.entity.SysMenuBtn;
import com.hangtoo.auth.entity.SysRoleRel.RelType;
import com.hangtoo.auth.mapper.SysMenuMapper;
import com.hangtoo.base.service.BaseService;

@Service("sysMenuService")
@Transactional
public class SysMenuService extends BaseService<SysMenu> {

	@Autowired
	private SysRoleRelService sysRoleRelService;
	
	@Autowired
	private SysMenuBtnService sysMenuBtnService;
	
	@Autowired
    private SysMenuMapper<SysMenu> mapper;
	
	/**
	 * 保存菜单btn
	 * @param btns
	 * @throws Exception 
	 */
	public void saveBtns(Integer menuid,List<SysMenuBtn> btns) throws Exception{
		if(btns == null || btns.isEmpty()){
			return;
		}
		for (SysMenuBtn btn : btns) {
			if(btn.getId()!= null && "1".equals(btn.getDeleteFlag())){
				sysMenuBtnService.delete(btn.getId());
				continue;
			}
			btn.setMenuid(menuid);
			if(btn.getId() == null){
				sysMenuBtnService.add(btn);
			}else{
				sysMenuBtnService.update(btn);
			}
		}
	}

	public void add(SysMenu menu) throws Exception {
		super.add(menu);
		saveBtns(menu.getId(),menu.getBtns());
	}

	public void update(SysMenu menu) throws Exception {
		super.update(menu);
		saveBtns(menu.getId(),menu.getBtns());
	}

	/**
	 * 查询所有系统菜单列表
	 * @return
	 */
	public List<SysMenu> queryByAll(){
		return mapper.queryByAll();
	}
	
	/**
	 * 获取顶级菜单
	 * @return
	 */
	public List<SysMenu> getRootMenu(Integer menuId){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("menuId", menuId);
		return mapper.getRootMenu(map);
	}
	
	/**
	 * 获取子菜单
	 * @return
	 */
	public List<SysMenu> getChildMenu(){
		return mapper.getChildMenu();
	}
	
	/**
	 * 根据用户id查询父菜单
	 * @param roleId
	 * @return
	 */
	public List<SysMenu> getRootMenuByUser(Integer userId){
		return getDao().getRootMenuByUser(userId);
	}
	
	/**
	 * 根据用户id查询子菜单
	 * @param roleId
	 * @return
	 */
	public List<SysMenu> getChildMenuByUser(Integer userId){
		return getDao().getChildMenuByUser(userId);
	}
	
	/**
	 * 根据权限id查询菜单
	 * @param roleId
	 * @return
	 */
	public List<SysMenu> getMenuByRoleId(Integer roleId){
		return getDao().getMenuByRoleId(roleId);
	}
	
	@Override
	public void delete(Object... ids) throws Exception {
		super.delete(ids);
		//删除关联关系
		for(Object id : ids){
			sysRoleRelService.deleteByObjId((Integer)id, RelType.MENU.key);
			sysMenuBtnService.deleteByMenuid((Integer)id);
		}
	}
	
	public SysMenuMapper<SysMenu> getDao() {
		return mapper;
	}

}
