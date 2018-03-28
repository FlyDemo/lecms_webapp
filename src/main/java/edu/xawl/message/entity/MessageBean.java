package edu.xawl.message.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.xawl.common.entity.BaseBean;
import edu.xawl.message.enums.MessageState;
import edu.xawl.us.entity.UserBean;

/**
 * 系统中的消息
 * @author Administrator
 *
 */
@Entity
@Table(name="LECMS_MESSAGE")
public class MessageBean extends BaseBean{
	/**
	 * 消息发出人
	 */
	private UserBean from;
	/**
	 * 消息接受人
	 */
	private UserBean to;
	/**
	 * 消息内容
	 */
	private String messageContent;
	/**
	 * 消息状态
	 */
	private MessageState messageState;
	/**
	 * 消息是否置顶 显示
	 */
	private boolean topShow;
	/**
	 * 消息是否已被删除
	 */
	private boolean deleted;
	
	
	
	public MessageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public MessageBean(UserBean from, UserBean to, String messageContent,
			MessageState messageState, boolean topShow, boolean deleted) {
		super();
		this.from = from;
		this.to = to;
		this.messageContent = messageContent;
		this.messageState = messageState;
		this.topShow = topShow;
		this.deleted = deleted;
	}


	@ManyToOne(targetEntity=UserBean.class)
	@JoinColumn(name="MESSAGE_FROM")
	public UserBean getFrom() {
		return from;
	}
	public void setFrom(UserBean from) {
		this.from = from;
	}
	
	@ManyToOne(targetEntity=UserBean.class)
	@JoinColumn(name="MESSAGE_TO")
	public UserBean getTo() {
		return to;
	}
	public void setTo(UserBean to) {
		this.to = to;
	}
	
	@Column(name="MESSAGE_CONTENT")
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	@Enumerated
	@Column(name="MESSAGE_STATE")
	public MessageState getMessageState() {
		return messageState;
	}
	public void setMessageState(MessageState messageState) {
		this.messageState = messageState;
	}
	
	@Column(name="TOP_SHOW")
	public boolean getTopShow() {
		return topShow;
	}
	public void setTopShow(boolean topShow) {
		this.topShow = topShow;
	}
	
	@Column(name="DELETED")
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
