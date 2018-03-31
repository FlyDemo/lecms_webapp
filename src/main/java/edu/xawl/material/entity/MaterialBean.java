package edu.xawl.material.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import edu.xawl.common.entity.BaseBean;
/**
 * 器材信息
 * @author Administrator
 *
 */
@Entity
@Table(name="LECMS_MATERIAL")
public class MaterialBean extends BaseBean{
	
	/**
	 * 器材名称
	 */
	private String materialName;
	/**
	 * 器材说明
	 */
	private String materialDesc;
	/**
	 * 器材分类
	 */
	private MaterialCategoryBean materialCategory;
	/**
	 * 器材单价
	 */
	private Float price;
	/**
	 * 器材图片位置
	 */
	private String materialImgPath;
	/**
	 * 器材生产厂商
	 */
	private String materialCreator;
	/**
	 * 器材保修期
	 */
	private String materialRepairTime;
	/**
	 * 器材使用注意事项
	 */
	private String tip;
	/**
	 * 器材免费借用时间
	 */
	private Long freeUseTime;
	/**
	 * 器材是否被删除
	 */
	private boolean deleted;
	/**
	 * 器材剩余量
	 */
	private Integer surPlus;
	/**
	 * 该器材实验室总总数
	 */
	private Integer total;
	/**
	 * 已经损坏数量
	 */
	private Integer badNum;
	
	@Column(name="MATERIAL_NAME")
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	@Column(name="MATERIAL_DESC")
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	
	@ManyToOne(targetEntity=MaterialCategoryBean.class)
	@JoinColumn(name="MATERIAL_CATEGORY")
	public MaterialCategoryBean getMaterialCategory() {
		return materialCategory;
	}
	public void setMaterialCategory(MaterialCategoryBean materialCategory) {
		this.materialCategory = materialCategory;
	}
	
	@Column(name="PRICE")
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	@Column(name="MATERIAL_IMG_PATH")
	public String getMaterialImgPath() {
		return materialImgPath;
	}
	public void setMaterialImgPath(String materialImgPath) {
		this.materialImgPath = materialImgPath;
	}
	
	@Column(name="MATERIAL_CREATOR")
	public String getMaterialCreator() {
		return materialCreator;
	}
	public void setMaterialCreator(String materialCreator) {
		this.materialCreator = materialCreator;
	}
	
	@Column(name="MATERIAL_REPAIR_TIME")
	public String getMaterialRepairTime() {
		return materialRepairTime;
	}
	public void setMaterialRepairTime(String materialRepairTime) {
		this.materialRepairTime = materialRepairTime;
	}
	
	@Column(name="TIP")
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	@Column(name="FREE_USE_TIME")
	public Long getFreeUseTime() {
		return freeUseTime;
	}
	public void setFreeUseTime(Long freeUseTime) {
		this.freeUseTime = freeUseTime;
	}
	
	@Column(name="DELETED")
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	/**
	 * 获取器材剩余量
	 * @return
	 */
	@Transient
	public Integer getSurPlus(){
		return surPlus;
	}
	public void setSurplus(Integer surplus){
		this.surPlus = surPlus;
	}
	
	/**
	 * 获取该器材总数量
	 * @return
	 */
	@Transient
	public Integer getTotal(){
		return total;
	}
	
	public void setTotal(Integer total){
		this.total = total;
	}
	
	/**
	 * 损坏数量
	 * @return
	 */
	@Transient
	public Integer getBadNum() {
		return badNum;
	}
	public void setBadNum(Integer badNum) {
		this.badNum = badNum;
	}
	
	
	
}
