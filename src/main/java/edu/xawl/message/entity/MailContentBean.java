package edu.xawl.message.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.xawl.common.entity.BaseBean;
import edu.xawl.message.enums.MailContentType;
import edu.xawl.us.entity.UserBean;

@Entity
@Table(name="LECMS_MAIL_CONTENT")
public class MailContentBean extends BaseBean{

	private static final long serialVersionUID = -7153615818921649348L;
	/**
	 * 邮件内容类型，例如删除用户邮件，添加用户邮件
	 */
	private MailContentType contentType;
	/**
	 * 类型的中文名称
	 */
	private String contentName;
	/**
	 * 这种类型的邮件内容
	 */
	private String content;
	/**
	 * 操作者，维护内容的人
	 */
	private UserBean modifier;
	
	@Enumerated
	@Column(name="MAIL_CONTENT_TYPE")
	public MailContentType getContentType() {
		return contentType;
	}
	public void setContentType(MailContentType contentType) {
		this.contentType = contentType;
	}
	
	@Column(name="CONTENT_NAME")
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	
	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@ManyToOne(targetEntity=UserBean.class)
	@JoinColumn(name="MODIFIER")
	public UserBean getModifier() {
		return modifier;
	}
	public void setModifier(UserBean modifier) {
		this.modifier = modifier;
	}
}
