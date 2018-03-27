package edu.xawl.work.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import edu.xawl.common.entity.BaseBean;

@Entity
@Table(name="LECMS_INSTITUTION")
public class InstitutionBean extends BaseBean{

	/**
	 * 制度名称
	 */
	
	private String name;
	
	/**
	 * 制度内容
	 */
	private String content;

	/**
	 * 是否删除
	 */
	
	private boolean deleted;

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="DELETED")
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
