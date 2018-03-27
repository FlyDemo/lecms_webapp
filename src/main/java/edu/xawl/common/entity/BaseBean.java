package edu.xawl.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseBean implements Serializable{
	
	private static final long serialVersionUID = 4529662089517230243L;
	
	protected String id;
	protected Date createTime;
	protected Date modifyTime;
	
	@Id
	@GeneratedValue(generator = "sql_id")    
	@GenericGenerator(name = "sql_id", strategy = "uuid")   
	@Column(name="ID",length=32,nullable=false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME",updatable=false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
