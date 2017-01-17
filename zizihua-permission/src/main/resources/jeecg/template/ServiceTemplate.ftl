package ${bussPackage}.service.${entityPackage};

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${bussPackage}.entity.${entityPackage}.${className};
import com.hangtoo.base.service.BaseService;
import ${bussPackage}.dao.${entityPackage}.${className}Dao;

/**
 * 
 * <br>
 * <b>功能：</b>${className}Controller<br>
 * <b>作者：</b>xxxxx<br>
 */ 
@Service("$!{lowerName}Service")
public class ${className}Service extends BaseService<${className}> {
	private final static Logger log= Logger.getLogger(${className}Service.class);
	

	

	@Autowired
    private ${className}Dao dao;

		
	public ${className}Dao getDao() {
		return dao;
	}

}
