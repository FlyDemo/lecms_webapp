package edu.xawl.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.xawl.common.dao.BaseDao;
import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;
import edu.xawl.common.utils.MailUtils;
import edu.xawl.message.entity.MailContentBean;
import edu.xawl.message.entity.MessageBean;
import edu.xawl.message.enums.MessageState;
import edu.xawl.message.service.MessageService;
import edu.xawl.us.entity.UserBean;
@Service
public class MessageServiceImpl implements MessageService {

	@Resource
	private CommonService commonService;
	
	@Resource
	private BaseDao baseDao;
	
	@Override
	public void saveUserToMail(String mailContentId, String toUserId) {
		System.out.println("===================="+mailContentId+"----"+toUserId);
		MailContentBean mailContent = (MailContentBean) commonService.findById(MailContentBean.class, mailContentId);
		UserBean toUser = (UserBean) commonService.findById(UserBean.class, toUserId);
		
		String toMail = toUser.getMail();
		
		//调用消息接口发送消息
		if(null==toMail||"".equals(toMail)||mailContent.getContent()==null||"".equals(mailContent.getContent())){
			System.out.println("===============邮件发送失败！=======================");
		}else{
			UserBean from = (UserBean)commonService.findByHql(" from UserBean u where u.loginName = ? ", "admin").get(0);
			MessageBean messageBean = new MessageBean(from, toUser, mailContent.getContent(), MessageState.NO_READ, false, false);
			commonService.merge(messageBean);
			MailUtils.sendEmailFromAdmin("用户激活成功提示！", mailContent.getContent(), toMail);
		}
	}
	
	
	@Override
	public List<MessageBean> findMessageList(UserBean user) {
		/**
		 * 查询用户的消息，要保证一下几点
		 * 	1.用户置顶的   最上面显示
		 *  2.未读的其次显示
		 *  3.已读的按照顺序显示
		 *  4.删除的不显示
		 */
		String hql = " from MessageBean m where m.to=? and m.deleted=? order by m.topShow,m.messageState,m.modifyTime ";
		List<MessageBean> messageList = baseDao.findByHql(hql, user,false);
		return messageList;
	}
	

	@Override
	public PageBean<MessageBean> findMessageList(UserBean user,
			PageBean<MessageBean> pb) {
		/**
		 * 查询用户的消息，要保证一下几点
		 * 	1.用户置顶的   最上面显示
		 *  2.未读的其次显示
		 *  3.已读的按照顺序显示
		 *  4.删除的不显示
		 */
		String hql = " from MessageBean m where m.to=? and m.deleted=? order by m.topShow desc,m.messageState,m.createTime desc ";
		PageBean<MessageBean> pageBean = commonService.findByPageQuery(pb, hql, "MessageBean",user,false);
		return pageBean;
	}


	@Override
	public MessageBean showMessage(String messageId) {
		/*首先需要将这个消息变为已读*/
		MessageBean message = (MessageBean) commonService.findById(MessageBean.class, messageId);
		if(message!=null){
			message.setMessageState(MessageState.READED);
			commonService.merge(message);
		}
		return message;
	}


	@Override
	public boolean changeMessage(String messageId, String op) {
		return false;
	}
}
