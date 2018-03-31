package edu.xawl.material.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.xawl.common.entity.BaseBean;
import edu.xawl.material.enums.MaterialStatus;

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
	private MaterialBean Material;
	/**
	 * 器材是否被删除
	 */
//	private boolean deleted;
	/**
	 * 器材是否正在被使用
	 */
//	private boolean usered;
	/**
	 * 是否损坏
	 */
//	private boolean bad;
	
//	上面两个字段用一个枚举   器材状态代替
	private MaterialStatus status;
	
	public MaterialDetailBean(String materialCode, MaterialBean material,
			MaterialStatus status) {
		super();
		this.materialCode = materialCode;
		Material = material;
		this.status = status;
	}
	
	@Column(name="MATERIAL_CODE")
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	@ManyToOne(targetEntity=MaterialBean.class)
	@JoinColumn(name="MATERIAL_PUBLIC")
	public MaterialBean getMaterial() {
		return Material;
	}
	public void setMaterial(MaterialBean material) {
		Material = material;
	}
	
	@Enumerated
	@Column(name="MATERIAL_STATUS")
	public MaterialStatus getStatus() {
		return status;
	}
	public void setStatus(MaterialStatus status) {
		this.status = status;
	}
	
	
}
