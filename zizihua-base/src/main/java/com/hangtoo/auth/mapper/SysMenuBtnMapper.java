package com.hangtoo.auth.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hangtoo.base.dao.BaseDao;

/**
 * SysMenuBtn Mapper
 * @author Administrator
 *
 */
@Component
public interface SysMenuBtnMapper<T> extends BaseDao<T> {
	
	public List<T> queryByMenuid(Integer menuid);
	
	public List<T> queryByMenuUrl(String url); 
	
	public void deleteByMenuid(Integer menuid);
	
	public List<T> getMenuBtnByUser(Integer userid); 
	
	
	
	public List<T> queryByAll();
}
