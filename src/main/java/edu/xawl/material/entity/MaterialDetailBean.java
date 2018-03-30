package edu.xawl.material.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import edu.xawl.common.entity.BaseBean;

@Entity
@Table(name="LECMS_MATERIAL_DETAIL")
public class MaterialDetailBean extends BaseBean{
	
	/**
	 * 器材编号
	 */
	private String materialCode;
	/**
	 * 器材名称
	 */
	private String materialName;
	/**
	 * 器材是否被删除
	 */
	private boolean deleted;
	/**
	 * 器材是否正在被使用
	 */
	private boolean usered;
	/**
	 * 是否损坏
	 */
	private boolean bad;
	
	@Column(name="MATERIAL_CODE")
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	@Column(name="USERED")
	public boolean getUsered() {
		return usered;
	}
	public void setUsered(boolean usered) {
		this.usered = usered;
	}
	
	@Column(name="MATERIAL_NAME")
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	@Column(name="DELETED")
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	@Column(name="IS_BAD")
	public boolean getBad() {
		return bad;
	}
	public void setBad(boolean bad) {
		this.bad = bad;
	}
}
