package hangtoo.dao.user;


import com.hangtoo.base.dao.BaseDao;
import hangtoo.entity.user.SysUser;

/**
 * 
 * <br>
 * <b>功能：</b>SysUserController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
public interface SysUserDao extends BaseDao<SysUser> {

	SysUser queryByEmail(String email);
	
	
}
