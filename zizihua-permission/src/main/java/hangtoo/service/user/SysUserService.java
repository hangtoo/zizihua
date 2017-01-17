package hangtoo.service.user;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hangtoo.entity.user.SysUser;
import com.hangtoo.base.service.BaseService;
import hangtoo.dao.user.SysUserDao;

/**
 * 
 * <br>
 * <b>功能：</b>SysUserController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
@Service("sysUserService")
public class SysUserService extends BaseService<SysUser> {
	private final static Logger log= Logger.getLogger(SysUserService.class);
	

	

	@Autowired
    private SysUserDao dao;

		
	public SysUserDao getDao() {
		return dao;
	}
	
	public SysUser queryByEmail(String email) throws Exception{
		return getDao().queryByEmail(email);
	}

}
