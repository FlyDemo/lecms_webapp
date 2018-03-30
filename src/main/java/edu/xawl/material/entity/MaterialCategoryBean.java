package edu.xawl.material.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import edu.xawl.common.entity.BaseBean;
/**
 * 器材分类
 * @author Administrator
 *
 */
@Entity
@Table(name="LECMS_METERIAL_CATEGORY")
public class MaterialCategoryBean extends BaseBean {
	/**
	 * 分类编号
	 */
	private String categoryCode;
	/**
	 * 分类名称
	 */
	private String categoryName;
	/**
	 * 分类排序
	 */
	private Integer categorySortNum;
	/**
	 * 是否删除
	 */
	private boolean deleted;
	
	@Column(name="CATEGORY_CODE")
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	@Column(name="CATEGORY_NAME")
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Column(name="CATEGORY_SORT_NUM")
	public Integer getCategorySortNum() {
		return categorySortNum;
	}
	public void setCategorySortNum(Integer categorySortNum) {
		this.categorySortNum = categorySortNum;
	}
	
	@Column(name="DELETED")
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
