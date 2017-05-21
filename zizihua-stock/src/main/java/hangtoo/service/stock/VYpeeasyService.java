package hangtoo.service.stock;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hangtoo.entity.stock.VYpeeasy;
import com.hangtoo.base.service.BaseService;
import hangtoo.dao.stock.VYpeeasyDao;

/**
 * 
 * <br>
 * <b>功能：</b>VYpeeasyController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
@Service("vYpeeasyService")
public class VYpeeasyService extends BaseService<VYpeeasy> {
	private final static Logger log= Logger.getLogger(VYpeeasyService.class);
	

	

	@Autowired
    private VYpeeasyDao dao;

		
	public VYpeeasyDao getDao() {
		return dao;
	}

}
