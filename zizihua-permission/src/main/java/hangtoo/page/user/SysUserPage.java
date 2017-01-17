package hangtoo.page.user;

import com.hangtoo.base.page.BasePage;
import java.math.BigDecimal;

/**
 * 
 * <br>
 * <b>功能：</b>SysUserController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
public class SysUserPage extends BasePage {
	

		private java.lang.Integer id;//   id主键	private java.lang.String email;//   邮箱也是登录帐号	private java.lang.String pwd;//   登录密码	private java.lang.String nickname;//   昵称	private java.lang.Integer state;//   状态 0=可用,1=禁用	private java.lang.Integer logincount;//   登录总次数	private java.util.Date logintime;//   最后登录时间	private java.lang.Integer deleted;//   删除状态 0=未删除,1=已删除	private java.util.Date createtime;//   创建时间	private java.util.Date updatetime;//   修改时间	private java.lang.Integer createby;//   创建人	private java.lang.Integer updateby;//   修改人	private java.lang.Integer superadmin;//   是否超级管理员 0= 不是，1=是	public java.lang.Integer getId() {	    return this.id;	}	public void setId(java.lang.Integer id) {	    this.id=id;	}	public java.lang.String getEmail() {	    return this.email;	}	public void setEmail(java.lang.String email) {	    this.email=email;	}	public java.lang.String getPwd() {	    return this.pwd;	}	public void setPwd(java.lang.String pwd) {	    this.pwd=pwd;	}	public java.lang.String getNickname() {	    return this.nickname;	}	public void setNickname(java.lang.String nickname) {	    this.nickname=nickname;	}	public java.lang.Integer getState() {	    return this.state;	}	public void setState(java.lang.Integer state) {	    this.state=state;	}	public java.lang.Integer getLogincount() {	    return this.logincount;	}	public void setLogincount(java.lang.Integer logincount) {	    this.logincount=logincount;	}	public java.util.Date getLogintime() {	    return this.logintime;	}	public void setLogintime(java.util.Date logintime) {	    this.logintime=logintime;	}	public java.lang.Integer getDeleted() {	    return this.deleted;	}	public void setDeleted(java.lang.Integer deleted) {	    this.deleted=deleted;	}	public java.util.Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(java.util.Date createtime) {	    this.createtime=createtime;	}	public java.util.Date getUpdatetime() {	    return this.updatetime;	}	public void setUpdatetime(java.util.Date updatetime) {	    this.updatetime=updatetime;	}	public java.lang.Integer getCreateby() {	    return this.createby;	}	public void setCreateby(java.lang.Integer createby) {	    this.createby=createby;	}	public java.lang.Integer getUpdateby() {	    return this.updateby;	}	public void setUpdateby(java.lang.Integer updateby) {	    this.updateby=updateby;	}	public java.lang.Integer getSuperadmin() {	    return this.superadmin;	}	public void setSuperadmin(java.lang.Integer superadmin) {	    this.superadmin=superadmin;	}
	
}
