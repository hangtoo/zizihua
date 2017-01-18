package hangtoo.service.role;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hangtoo.entity.role.SysRole;
import com.hangtoo.base.service.BaseService;
import hangtoo.dao.role.SysRoleDao;

/**
 * 
 * <br>
 * <b>功能：</b>SysRoleController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
@Service("sysRoleService")
public class SysRoleService extends BaseService<SysRole> {
	private final static Logger log= Logger.getLogger(SysRoleService.class);
	

	

	@Autowired
    private SysRoleDao dao;

		
	public SysRoleDao getDao() {
		return dao;
	}

}
