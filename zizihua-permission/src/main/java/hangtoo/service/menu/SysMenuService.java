package hangtoo.service.menu;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangtoo.base.service.BaseService;

import hangtoo.dao.menu.SysMenuDao;
import hangtoo.entity.menu.SysMenu;
import hangtoo.entity.user.SysUser;

/**
 * 
 * <br>
 * <b>功能：</b>SysMenuController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
@Service("sysMenuService")
public class SysMenuService extends BaseService<SysMenu> {
	private final static Logger log= Logger.getLogger(SysMenuService.class);
	

	

	@Autowired
    private SysMenuDao dao;

		
	public SysMenuDao getDao() {
		return dao;
	}
	
	public List<SysMenu> queryMenuByUserAndParentId(Integer userId,Integer parentId){
		return getDao().queryMenuByUserIdAndParentId(userId,parentId);
	}
	
	public List<SysMenu> queryRootMenuByUserId(SysUser user){
		return getDao().queryRootMenuByUserId(user);
	}
	
	public List<SysMenu> queryRootMenu(){
		return getDao().queryRootMenu();
	}
	

}
