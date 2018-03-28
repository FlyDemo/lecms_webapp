package edu.xawl.message.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

}
