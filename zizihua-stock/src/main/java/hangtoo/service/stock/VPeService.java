package hangtoo.service.stock;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hangtoo.entity.stock.VPe;
import com.hangtoo.base.service.BaseService;
import hangtoo.dao.stock.VPeDao;

/**
 * 
 * <br>
 * <b>功能：</b>VPeController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
@Service("vPeService")
public class VPeService extends BaseService<VPe> {
	private final static Logger log= Logger.getLogger(VPeService.class);
	

	

	@Autowired
    private VPeDao dao;

		
	public VPeDao getDao() {
		return dao;
	}

}
