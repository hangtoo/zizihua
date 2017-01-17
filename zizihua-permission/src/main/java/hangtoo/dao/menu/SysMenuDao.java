package hangtoo.dao.menu;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hangtoo.base.dao.BaseDao;

import hangtoo.entity.menu.SysMenu;
import hangtoo.entity.user.SysUser;

/**
 * 
 * <br>
 * <b>功能：</b>SysMenuController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
public interface SysMenuDao extends BaseDao<SysMenu> {
	
	public List<SysMenu> queryMenuByUserIdAndParentId(@Param("userId")Integer userId,@Param("parentId")Integer parentId);
	public List<SysMenu> queryRootMenuByUserId(SysUser user);
	public List<SysMenu> queryRootMenu();
}
