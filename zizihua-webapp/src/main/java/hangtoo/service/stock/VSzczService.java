package hangtoo.service.stock;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hangtoo.entity.stock.VSzcz;
import com.hangtoo.base.service.BaseService;
import hangtoo.dao.stock.VSzczDao;

/**
 * 
 * <br>
 * <b>功能：</b>VSzczController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
@Service("vSzczService")
public class VSzczService extends BaseService<VSzcz> {
	private final static Logger log= Logger.getLogger(VSzczService.class);
	

	

	@Autowired
    private VSzczDao dao;

		
	public VSzczDao getDao() {
		return dao;
	}

}
