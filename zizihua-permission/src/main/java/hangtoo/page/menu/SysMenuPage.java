package hangtoo.page.menu;

import com.hangtoo.base.page.BasePage;
import java.math.BigDecimal;

/**
 * 
 * <br>
 * <b>功能：</b>SysMenuController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
public class SysMenuPage extends BasePage {
	

		private java.lang.Integer id;//   主键	private java.lang.String name;//   菜单名称	private java.lang.String url;//   系统url	private java.lang.Integer parentid;//    父id 关联sys_menu.id	private java.lang.Integer deleted;//   是否删除,0=未删除，1=已删除	private java.util.Date createtime;//   创建时间	private java.util.Date updatetime;//   修改时间	private java.lang.Integer rank;//   排序	private java.lang.String actions;//   注册Action 按钮|分隔	public java.lang.Integer getId() {	    return this.id;	}	public void setId(java.lang.Integer id) {	    this.id=id;	}	public java.lang.String getName() {	    return this.name;	}	public void setName(java.lang.String name) {	    this.name=name;	}	public java.lang.String getUrl() {	    return this.url;	}	public void setUrl(java.lang.String url) {	    this.url=url;	}	public java.lang.Integer getParentid() {	    return this.parentid;	}	public void setParentid(java.lang.Integer parentid) {	    this.parentid=parentid;	}	public java.lang.Integer getDeleted() {	    return this.deleted;	}	public void setDeleted(java.lang.Integer deleted) {	    this.deleted=deleted;	}	public java.util.Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(java.util.Date createtime) {	    this.createtime=createtime;	}	public java.util.Date getUpdatetime() {	    return this.updatetime;	}	public void setUpdatetime(java.util.Date updatetime) {	    this.updatetime=updatetime;	}	public java.lang.Integer getRank() {	    return this.rank;	}	public void setRank(java.lang.Integer rank) {	    this.rank=rank;	}	public java.lang.String getActions() {	    return this.actions;	}	public void setActions(java.lang.String actions) {	    this.actions=actions;	}
	
}
