package hangtoo.service.stock;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hangtoo.entity.stock.TStock;
import com.hangtoo.base.service.BaseService;
import hangtoo.dao.stock.TStockDao;

/**
 * 
 * <br>
 * <b>功能：</b>TStockController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
@Service("tStockService")
public class TStockService extends BaseService<TStock> {
	private final static Logger log= Logger.getLogger(TStockService.class);
	

	public String getLastDate(){
		return getDao().getLastDate();
	}

	@Autowired
    private TStockDao dao;

		
	public TStockDao getDao() {
		return dao;
	}

}
