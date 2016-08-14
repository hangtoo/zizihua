package hangtoo.dao.gold;


import com.hangtoo.base.dao.BaseDao;
import hangtoo.entity.gold.TGold;

/**
 * 
 * <br>
 * <b>功能：</b>TGoldController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
public interface TGoldDao extends BaseDao<TGold> {
	
	public String getLastDate();
}
