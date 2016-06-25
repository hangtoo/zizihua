package com.hangtoo.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangtoo.auth.entity.SysMenuBtn;
import com.hangtoo.auth.mapper.SysMenuBtnMapper;
import com.hangtoo.base.service.BaseService;

@Service("sysMenuBtnService")
@Transactional
public class SysMenuBtnService extends BaseService<SysMenuBtn> {
	
	public List<SysMenuBtn> queryByAll(){
		return getDao().queryByAll();
	}
	
	public List<SysMenuBtn> queryByMenuid(Integer menuid){
		return getDao().queryByMenuid(menuid);
	}
	
	public List<SysMenuBtn> queryByMenuUrl(String url){
		return getDao().queryByMenuUrl(url);
	}
	
	public void deleteByMenuid(Integer menuid){
		getDao().deleteByMenuid(menuid);
	}
	
	public List<SysMenuBtn> getMenuBtnByUser(Integer userid){
		return getDao().getMenuBtnByUser(userid);
	}

	@Autowired
    private SysMenuBtnMapper<SysMenuBtn> mapper;

		
	public SysMenuBtnMapper<SysMenuBtn> getDao() {
		return mapper;
	}

}
