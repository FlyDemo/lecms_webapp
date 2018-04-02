package edu.xawl.message.service;

import java.util.List;

import edu.xawl.common.entity.PageBean;
import edu.xawl.message.entity.MessageBean;
import edu.xawl.us.entity.UserBean;

public interface MessageService {

	public void saveUserToMail(String contentId,String toUserId);
	
	public List<MessageBean> findMessageList(UserBean user);
	
	public MessageBean showMessage(String messageId);
	
	public boolean changeMessage(String messageId,String op);

	public PageBean<MessageBean> findMessageList(UserBean user,PageBean<MessageBean> pb);
}
