package hangtoo.service.gold;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hangtoo.entity.gold.TGold;
import com.hangtoo.base.service.BaseService;
import hangtoo.dao.gold.TGoldDao;

/**
 * 
 * <br>
 * <b>功能：</b>TGoldController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
@Service("tGoldService")
public class TGoldService extends BaseService<TGold> {
	private final static Logger log= Logger.getLogger(TGoldService.class);
	
	public String getLastDate(){
		return getDao().getLastDate();
	}

	@Autowired
    private TGoldDao dao;

		
	public TGoldDao getDao() {
		return dao;
	}

}
