package hangtoo.entity.stock;

import com.hangtoo.base.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * 
 * <br>
 * <b>功能：</b>VPeController<br>
 * <b>作者：</b>xxxxx<br>
 */ 
public class VPe extends BaseEntity {
	
		private java.lang.Integer id;//   主键	private java.util.Date p_createtime;//   创建时间	private java.util.Date p_modifytime;//   修改时间	private java.lang.String p_creator;//   创建人	private java.lang.String p_modifier;//   修改人	private java.lang.String p_remark;//   备注	private java.lang.Object p_deleted;//   是否删除	private java.lang.String p_name;//   指数名称	private BigDecimal p_data;//   数值	private java.util.Date p_date;//   日期	private BigDecimal p_add;//   比上日增减	private BigDecimal p_rate;//   幅度%	private BigDecimal p_highdata;//   本年最高	private java.util.Date p_highdate;//   最高值日期	public java.lang.Integer getId() {	    return this.id;	}	public void setId(java.lang.Integer id) {	    this.id=id;	}	public java.util.Date getP_createtime() {	    return this.p_createtime;	}	public void setP_createtime(java.util.Date p_createtime) {	    this.p_createtime=p_createtime;	}	public java.util.Date getP_modifytime() {	    return this.p_modifytime;	}	public void setP_modifytime(java.util.Date p_modifytime) {	    this.p_modifytime=p_modifytime;	}	public java.lang.String getP_creator() {	    return this.p_creator;	}	public void setP_creator(java.lang.String p_creator) {	    this.p_creator=p_creator;	}	public java.lang.String getP_modifier() {	    return this.p_modifier;	}	public void setP_modifier(java.lang.String p_modifier) {	    this.p_modifier=p_modifier;	}	public java.lang.String getP_remark() {	    return this.p_remark;	}	public void setP_remark(java.lang.String p_remark) {	    this.p_remark=p_remark;	}	public java.lang.Object getP_deleted() {	    return this.p_deleted;	}	public void setP_deleted(java.lang.Object p_deleted) {	    this.p_deleted=p_deleted;	}	public java.lang.String getP_name() {	    return this.p_name;	}	public void setP_name(java.lang.String p_name) {	    this.p_name=p_name;	}	public BigDecimal getP_data() {	    return this.p_data;	}	public void setP_data(BigDecimal p_data) {	    this.p_data=p_data;	}	public java.util.Date getP_date() {	    return this.p_date;	}	public void setP_date(java.util.Date p_date) {	    this.p_date=p_date;	}	public BigDecimal getP_add() {	    return this.p_add;	}	public void setP_add(BigDecimal p_add) {	    this.p_add=p_add;	}	public BigDecimal getP_rate() {	    return this.p_rate;	}	public void setP_rate(BigDecimal p_rate) {	    this.p_rate=p_rate;	}	public BigDecimal getP_highdata() {	    return this.p_highdata;	}	public void setP_highdata(BigDecimal p_highdata) {	    this.p_highdata=p_highdata;	}	public java.util.Date getP_highdate() {	    return this.p_highdate;	}	public void setP_highdate(java.util.Date p_highdate) {	    this.p_highdate=p_highdate;	}
}

