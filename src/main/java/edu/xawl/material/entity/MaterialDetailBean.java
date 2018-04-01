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
	private MaterialBean material;
	/**
	 * 器材状态
	 */
	private MaterialStatus status;
	
	
	
	public MaterialDetailBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaterialDetailBean(String materialCode, MaterialBean material,
			MaterialStatus status) {
		super();
		this.materialCode = materialCode;
		this.material = material;
		this.status = status;
	}
	
	@Column(name="MATERIAL_CODE")
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}	
	
	@Enumerated
	@Column(name="MATERIAL_STATUS")
	public MaterialStatus getStatus() {
		return status;
	}
	
	public void setStatus(MaterialStatus status) {
		this.status = status;
	}

	@ManyToOne(targetEntity=MaterialBean.class)
	@JoinColumn(name="MATERIAL")
	public MaterialBean getMaterial() {
		return material;
	}

	public void setMaterial(MaterialBean material) {
		this.material = material;
	}
	
	

}
