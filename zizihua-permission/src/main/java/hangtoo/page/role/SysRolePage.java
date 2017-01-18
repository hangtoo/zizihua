package hangtoo.page.role;

import com.hangtoo.base.page.BasePage;
import java.math.BigDecimal;

/**
 * 
 * <br>
 * <b>功能：</b>SysRoleController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
public class SysRolePage extends BasePage {
	

		private java.lang.Integer id;//   id主键	private java.lang.String rolename;//   角色名称	private java.util.Date createtime;//   创建时间	private java.lang.Integer createby;//   创建人	private java.util.Date updatetime;//   修改时间	private java.lang.Integer updateby;//   修改人	private java.lang.Integer state;//   状态0=可用 1=禁用	private java.lang.String descr;//   角色描述	public java.lang.Integer getId() {	    return this.id;	}	public void setId(java.lang.Integer id) {	    this.id=id;	}	public java.lang.String getRolename() {	    return this.rolename;	}	public void setRolename(java.lang.String rolename) {	    this.rolename=rolename;	}	public java.util.Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(java.util.Date createtime) {	    this.createtime=createtime;	}	public java.lang.Integer getCreateby() {	    return this.createby;	}	public void setCreateby(java.lang.Integer createby) {	    this.createby=createby;	}	public java.util.Date getUpdatetime() {	    return this.updatetime;	}	public void setUpdatetime(java.util.Date updatetime) {	    this.updatetime=updatetime;	}	public java.lang.Integer getUpdateby() {	    return this.updateby;	}	public void setUpdateby(java.lang.Integer updateby) {	    this.updateby=updateby;	}	public java.lang.Integer getState() {	    return this.state;	}	public void setState(java.lang.Integer state) {	    this.state=state;	}	public java.lang.String getDescr() {	    return this.descr;	}	public void setDescr(java.lang.String descr) {	    this.descr=descr;	}
	
}
